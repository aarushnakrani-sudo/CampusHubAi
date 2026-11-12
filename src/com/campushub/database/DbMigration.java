package com.campushub.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbMigration {

    public static void runMigrations() {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.err.println("\n[MIGRATION ERROR] Could not get database connection to run migrations.");
            return;
        }

        try (Statement stmt = conn.createStatement()) {
            System.out.println("Running database migrations...");

            // 1. Update existing statuses for NotesRequest to match new ENUM values before altering column
            try {
                stmt.executeUpdate("UPDATE NotesRequest SET status = 'Pending' WHERE status = 'Open'");
                stmt.executeUpdate("UPDATE NotesRequest SET status = 'Approved' WHERE status = 'Fulfilled'");
                stmt.executeUpdate("UPDATE NotesRequest SET status = 'Rejected' WHERE status = 'Closed'");
            } catch (SQLException e) {
                // If it fails because columns don't exist or status is already updated, ignore
            }

            // 2. Alter NotesRequest status column
            try {
                stmt.executeUpdate("ALTER TABLE NotesRequest MODIFY COLUMN status ENUM('Pending','Approved','Rejected') NOT NULL DEFAULT 'Pending'");
            } catch (SQLException e) {
                System.err.println("Note: NotesRequest status alter failed or was already applied: " + e.getMessage());
            }

            // 3. Add image_path to LostFound
            try {
                stmt.executeUpdate("ALTER TABLE LostFound ADD COLUMN image_path VARCHAR(500) NULL DEFAULT NULL");
                System.out.println("Column 'image_path' added to 'LostFound' table successfully.");
            } catch (SQLException e) {
                // Ignore if column already exists (Error code 1060 is duplicate column)
                if (e.getErrorCode() != 1060) {
                    System.err.println("Note: LostFound image_path alter: " + e.getMessage());
                }
            }

            // 4. Drop trigger if exists
            try {
                stmt.executeUpdate("DROP TRIGGER IF EXISTS after_complaint_resolved");
            } catch (SQLException e) {
                System.err.println("Note: Dropping trigger failed: " + e.getMessage());
            }

            // 5. Create Trigger for Complaint status to Resolved
            try {
                String triggerSql = 
                    "CREATE TRIGGER after_complaint_resolved " +
                    "BEFORE UPDATE ON Complaint " +
                    "FOR EACH ROW " +
                    "BEGIN " +
                    "    IF NEW.status = 'Resolved' AND OLD.status <> 'Resolved' THEN " +
                    "        SET NEW.resolved_date = CURDATE(); " +
                    "    END IF; " +
                    "END;";
                stmt.executeUpdate(triggerSql);
                System.out.println("Trigger 'after_complaint_resolved' created successfully.");
            } catch (SQLException e) {
                System.err.println("Trigger creation failed: " + e.getMessage());
            }

            // 6. Drop Stored Function
            try {
                stmt.executeUpdate("DROP FUNCTION IF EXISTS GetAttendancePercentage");
            } catch (SQLException e) {
                System.err.println("Note: Dropping function failed: " + e.getMessage());
            }

            // 7. Create Stored Function for Attendance Percentage
            try {
                String funcSql = 
                    "CREATE FUNCTION GetAttendancePercentage(present_classes INT, total_classes INT) " +
                    "RETURNS DECIMAL(5,2) " +
                    "DETERMINISTIC " +
                    "BEGIN " +
                    "    IF total_classes = 0 THEN " +
                    "        RETURN 0.00; " +
                    "    END IF; " +
                    "    RETURN (present_classes * 100.0) / total_classes; " +
                    "END;";
                stmt.executeUpdate(funcSql);
                System.out.println("Stored Function 'GetAttendancePercentage' created successfully.");
            } catch (SQLException e) {
                System.err.println("Stored Function creation failed: " + e.getMessage());
            }

            // 8. Drop Stored Procedure
            try {
                stmt.executeUpdate("DROP PROCEDURE IF EXISTS GetAttendanceShortageReport");
            } catch (SQLException e) {
                System.err.println("Note: Dropping procedure failed: " + e.getMessage());
            }

            // 9. Create Stored Procedure with Cursor for Shortage Report
            try {
                String procSql = 
                    "CREATE PROCEDURE GetAttendanceShortageReport() " +
                    "BEGIN " +
                    "    DECLARE done INT DEFAULT FALSE; " +
                    "    DECLARE s_id INT; " +
                    "    DECLARE enroll VARCHAR(20); " +
                    "    DECLARE s_name VARCHAR(100); " +
                    "    DECLARE tot_pres INT; " +
                    "    DECLARE tot_class INT; " +
                    "    DECLARE att_pct DECIMAL(5,2); " +
                    "    DECLARE cur CURSOR FOR " +
                    "        SELECT s.student_id, s.enrollment_no, s.full_name, " +
                    "               IFNULL(SUM(a.present_classes), 0) AS tot_p, " +
                    "               IFNULL(SUM(a.total_classes), 0) AS tot_c " +
                    "        FROM Student s " +
                    "        LEFT JOIN Attendance a ON s.student_id = a.student_id " +
                    "        GROUP BY s.student_id, s.enrollment_no, s.full_name; " +
                    "    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE; " +
                    "    CREATE TEMPORARY TABLE IF NOT EXISTS ShortageReport ( " +
                    "        student_id INT, " +
                    "        enrollment_no VARCHAR(20), " +
                    "        full_name VARCHAR(100), " +
                    "        attendance_percentage DECIMAL(5,2) " +
                    "    ); " +
                    "    TRUNCATE TABLE ShortageReport; " +
                    "    OPEN cur; " +
                    "    read_loop: LOOP " +
                    "        FETCH cur INTO s_id, enroll, s_name, tot_pres, tot_class; " +
                    "        IF done THEN " +
                    "            LEAVE read_loop; " +
                    "        END IF; " +
                    "        SET att_pct = GetAttendancePercentage(tot_pres, tot_class); " +
                    "        IF att_pct < 75.00 OR tot_class = 0 THEN " +
                    "            INSERT INTO ShortageReport VALUES (s_id, enroll, s_name, att_pct); " +
                    "        END IF; " +
                    "    END LOOP; " +
                    "    CLOSE cur; " +
                    "    SELECT * FROM ShortageReport; " +
                    "    DROP TEMPORARY TABLE ShortageReport; " +
                    "END;";
                stmt.executeUpdate(procSql);
                System.out.println("Stored Procedure 'GetAttendanceShortageReport' created successfully.");
            } catch (SQLException e) {
                System.err.println("Stored Procedure creation failed: " + e.getMessage());
            }

            System.out.println("Database migrations completed successfully!\n");

        } catch (SQLException e) {
            System.err.println("[MIGRATION ERROR] Database migration encountered an issue: " + e.getMessage());
        }
    }
}
