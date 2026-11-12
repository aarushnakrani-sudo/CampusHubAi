CREATE DATABASE CampusHubAI;

USE CampusHubAI;

CREATE TABLE Department (
                            department_id INT AUTO_INCREMENT PRIMARY KEY,
                            department_name VARCHAR(100) NOT NULL UNIQUE,
                            department_code VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO Department (department_name, department_code)
VALUES
    ('Computer Engineering', 'CE'),
    ('Information Technology', 'IT'),
    ('Computer Science & Engineering', 'CSE'),
    ('Mechanical Engineering', 'ME');

CREATE TABLE Student (
                         student_id INT AUTO_INCREMENT PRIMARY KEY,
                         enrollment_no VARCHAR(20) NOT NULL UNIQUE,
                         full_name VARCHAR(100) NOT NULL,
                         college_email VARCHAR(100) NOT NULL UNIQUE,
                         password_hash VARCHAR(255) NOT NULL,
                         department_id INT NOT NULL,
                         semester INT NOT NULL,
                         phone VARCHAR(15),

                         CONSTRAINT fk_student_department
                             FOREIGN KEY (department_id)
                                 REFERENCES Department(department_id)
);

INSERT INTO Student
(enrollment_no, full_name, college_email, password_hash, department_id, semester, phone)
VALUES
    ('24CE001', 'Krisha Mor', '24ce001@college.edu', '123456', 1, 2, '9876543210'),
    ('24IT015', 'Aarav Patel', '24it015@college.edu', '123456', 2, 2, '9876543211'),
    ('24CS022', 'Riya Shah', '24cs022@college.edu', '123456', 3, 2, '9876543212'),
    ('24ME010', 'Yash Mehta', '24me010@college.edu', '123456', 4, 2, '9876543213');



CREATE TABLE Admin (
                       admin_id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL
);



INSERT INTO Admin (name, email, password_hash)
VALUES
    ('System Administrator', 'admin@campushub.com', 'admin123');


CREATE TABLE Faculty (
                         faculty_id INT AUTO_INCREMENT PRIMARY KEY,
                         admin_id INT NULL,
                         department_id INT NOT NULL,
                         faculty_name VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,

                         CONSTRAINT fk_faculty_admin
                             FOREIGN KEY (admin_id)
                                 REFERENCES Admin(admin_id),

                         CONSTRAINT fk_faculty_department
                             FOREIGN KEY (department_id)
                                 REFERENCES Department(department_id)
);



INSERT INTO Faculty (admin_id, department_id, faculty_name, email)
VALUES
    (1, 1, 'Dr. Rajesh Patel', 'rajesh.patel@college.edu'),
    (1, 1, 'Prof. Meera Shah', 'meera.shah@college.edu'),
    (1, 2, 'Dr. Amit Joshi', 'amit.joshi@college.edu'),
    (1, 4, 'Prof. Neha Desai', 'neha.desai@college.edu');


CREATE TABLE Subject (
                         subject_id INT AUTO_INCREMENT PRIMARY KEY,
                         department_id INT NOT NULL,
                         subject_name VARCHAR(100) NOT NULL,
                         semester INT NOT NULL,

                         CONSTRAINT fk_subject_department
                             FOREIGN KEY (department_id)
                                 REFERENCES Department(department_id)
);




INSERT INTO Subject (department_id, subject_name, semester)
VALUES
    (1, 'Fundamentals of Electrical Engineering', 2),
    (1, 'Java II', 2),
    (1, 'Database Management System', 2),
    (1, 'Data Structures', 2),
    (1, 'Engineering Mathematics II', 2);



CREATE TABLE Club (
                      club_id INT AUTO_INCREMENT PRIMARY KEY,
                      club_name VARCHAR(100) NOT NULL UNIQUE,
                      description TEXT,
                      category VARCHAR(50) NOT NULL,
                      created_date DATE NOT NULL
);


INSERT INTO Club (club_name, description, category, created_date)
VALUES
    ('Coding Club', 'Programming, Hackathons and Coding Competitions', 'Technical', '2025-01-15'),

    ('Robotics Club', 'Robotics and Automation Projects', 'Technical', '2025-01-20'),

    ('Dance Club', 'Dance Performances and Competitions', 'Cultural', '2025-01-10'),

    ('Photography Club', 'Photography and Editing', 'Creative', '2025-01-12'),

    ('Sports Club', 'Indoor and Outdoor Sports Activities', 'Sports', '2025-01-18');




CREATE TABLE ClubMembership (
                                membership_id INT AUTO_INCREMENT PRIMARY KEY,
                                student_id INT NOT NULL,
                                club_id INT NOT NULL,
                                join_date DATE NOT NULL,

                                CONSTRAINT fk_membership_student
                                    FOREIGN KEY (student_id)
                                        REFERENCES Student(student_id)
                                        ON DELETE CASCADE,

                                CONSTRAINT fk_membership_club
                                    FOREIGN KEY (club_id)
                                        REFERENCES Club(club_id)
                                        ON DELETE CASCADE,

                                CONSTRAINT unique_student_club
                                    UNIQUE(student_id, club_id)
);



INSERT INTO ClubMembership (student_id, club_id, join_date)
VALUES
    (1, 1, '2026-07-15'),
    (1, 3, '2026-07-15'),
    (2, 1, '2026-07-16'),
    (3, 4, '2026-07-16'),
    (4, 5, '2026-07-17');


SELECT
    s.full_name,
    c.club_name,
    cm.join_date
FROM ClubMembership cm
         JOIN Student s
              ON cm.student_id = s.student_id
         JOIN Club c
              ON cm.club_id = c.club_id;




CREATE TABLE Event (
                       event_id INT AUTO_INCREMENT PRIMARY KEY,
                       club_id INT NOT NULL,
                       event_name VARCHAR(100) NOT NULL,
                       category VARCHAR(50) NOT NULL,
                       description TEXT,
                       venue VARCHAR(100) NOT NULL,
                       event_date DATE NOT NULL,
                       registration_deadline DATE NOT NULL,
                       max_participants INT NOT NULL,

                       CONSTRAINT fk_event_club
                           FOREIGN KEY (club_id)
                               REFERENCES Club(club_id)
                               ON DELETE CASCADE,

                       CONSTRAINT chk_max_participants
                           CHECK (max_participants > 0)
);



INSERT INTO Event
(club_id, event_name, category, description, venue, event_date, registration_deadline, max_participants)
VALUES

    (1,
     'Hackathon',
     'Technical',
     '24-hour coding competition',
     'Seminar Hall',
     '2026-08-20',
     '2026-08-15',
     100),

    (3,
     'Dance Fiesta',
     'Dance',
     'Annual dance competition',
     'Auditorium',
     '2026-09-10',
     '2026-09-05',
     80),

    (5,
     'Inter-Department Cricket',
     'Sports',
     'College cricket tournament',
     'College Ground',
     '2026-08-30',
     '2026-08-25',
     120),

    (4,
     'Photography Walk',
     'Creative',
     'Campus photography challenge',
     'Main Campus',
     '2026-09-15',
     '2026-09-10',
     50);



SELECT
    e.event_name,
    e.category,
    c.club_name,
    e.event_date
FROM Event e
         JOIN Club c
              ON e.club_id = c.club_id;



CREATE TABLE EventRegistration (
                                   registration_id INT AUTO_INCREMENT PRIMARY KEY,
                                   student_id INT NOT NULL,
                                   event_id INT NOT NULL,
                                   registration_date DATE NOT NULL,

                                   CONSTRAINT fk_registration_student
                                       FOREIGN KEY (student_id)
                                           REFERENCES Student(student_id)
                                           ON DELETE CASCADE,

                                   CONSTRAINT fk_registration_event
                                       FOREIGN KEY (event_id)
                                           REFERENCES Event(event_id)
                                           ON DELETE CASCADE,

                                   CONSTRAINT unique_student_event
                                       UNIQUE(student_id, event_id)
);


INSERT INTO EventRegistration
(student_id, event_id, registration_date)
VALUES
    (1, 1, '2026-07-20'),
    (2, 1, '2026-07-21'),
    (3, 2, '2026-07-22'),
    (4, 3, '2026-07-22');


SELECT
    s.full_name,
    e.event_name,
    c.club_name,
    er.registration_date
FROM EventRegistration er
         JOIN Student s
              ON er.student_id = s.student_id
         JOIN Event e
              ON er.event_id = e.event_id
         JOIN Club c
              ON e.club_id = c.club_id;


CREATE TABLE Announcement (
                              announcement_id INT AUTO_INCREMENT PRIMARY KEY,
                              admin_id INT NOT NULL,
                              title VARCHAR(150) NOT NULL,
                              description TEXT NOT NULL,
                              publish_date DATE NOT NULL,

                              CONSTRAINT fk_announcement_admin
                                  FOREIGN KEY (admin_id)
                                      REFERENCES Admin(admin_id)
                                      ON DELETE CASCADE
);



INSERT INTO Announcement
(admin_id, title, description, publish_date)
VALUES

    (1,
     'Semester Examination',
     'Semester examinations will commence from 15 September 2026.',
     '2026-08-01'),

    (1,
     'Hackathon Registration',
     'Registration for the annual Hackathon is now open.',
     '2026-08-05'),

    (1,
     'Holiday Notice',
     'College will remain closed on Independence Day.',
     '2026-08-10');



SELECT
    a.title,
    a.publish_date,
    ad.name AS posted_by
FROM Announcement a
         JOIN Admin ad
              ON a.admin_id = ad.admin_id;


CREATE TABLE Attendance (
                            attendance_id INT AUTO_INCREMENT PRIMARY KEY,
                            student_id INT NOT NULL,
                            subject_id INT NOT NULL,
                            admin_id INT NOT NULL,
                            total_classes INT NOT NULL DEFAULT 0,
                            present_classes INT NOT NULL DEFAULT 0,

                            CONSTRAINT fk_attendance_student
                                FOREIGN KEY (student_id)
                                    REFERENCES Student(student_id)
                                    ON DELETE CASCADE,

                            CONSTRAINT fk_attendance_subject
                                FOREIGN KEY (subject_id)
                                    REFERENCES Subject(subject_id)
                                    ON DELETE CASCADE,

                            CONSTRAINT fk_attendance_admin
                                FOREIGN KEY (admin_id)
                                    REFERENCES Admin(admin_id)
                                    ON DELETE CASCADE,

                            CONSTRAINT chk_total_classes
                                CHECK (total_classes >= 0),

                            CONSTRAINT chk_present_classes
                                CHECK (present_classes >= 0),

                            CONSTRAINT chk_present_not_greater
                                CHECK (present_classes <= total_classes),

                            CONSTRAINT unique_student_subject
                                UNIQUE(student_id, subject_id)
);



INSERT INTO Attendance
(student_id, subject_id, admin_id, total_classes, present_classes)
VALUES
    (1,1,1,40,36),
    (1,2,1,38,35),
    (1,3,1,42,39),
    (1,4,1,37,34),
    (1,5,1,39,37),

    (2,1,1,40,33),
    (2,2,1,38,30),
    (3,3,1,42,40),
    (4,5,1,39,35);


SELECT
    s.full_name,
    sub.subject_name,
    a.present_classes,
    a.total_classes
FROM Attendance a
         JOIN Student s
              ON a.student_id = s.student_id
         JOIN Subject sub
              ON a.subject_id = sub.subject_id;

CREATE TABLE Timetable (
                           timetable_id INT AUTO_INCREMENT PRIMARY KEY,

                           subject_id INT NOT NULL,
                           faculty_id INT NOT NULL,
                           admin_id INT NOT NULL,

                           day ENUM('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday') NOT NULL,

                           lecture_no TINYINT NOT NULL,

                           time_slot VARCHAR(30) NOT NULL,

                           room_no VARCHAR(20) NOT NULL,

                           semester INT NOT NULL,

                           CONSTRAINT fk_timetable_subject
                               FOREIGN KEY (subject_id)
                                   REFERENCES Subject(subject_id)
                                   ON DELETE CASCADE,

                           CONSTRAINT fk_timetable_faculty
                               FOREIGN KEY (faculty_id)
                                   REFERENCES Faculty(faculty_id)
                                   ON DELETE CASCADE,

                           CONSTRAINT fk_timetable_admin
                               FOREIGN KEY (admin_id)
                                   REFERENCES Admin(admin_id)
                                   ON DELETE CASCADE,

                           CONSTRAINT chk_lecture_no
                               CHECK (lecture_no BETWEEN 1 AND 4)
);


INSERT INTO Timetable
(subject_id, faculty_id, admin_id, day, lecture_no, time_slot, room_no, semester)
VALUES

    (1,1,1,'Monday',1,'08:45 AM - 09:45 AM','A-101',2),

    (2,2,1,'Monday',2,'09:45 AM - 10:45 AM','A-102',2),

    (3,1,1,'Tuesday',3,'11:30 AM - 12:30 PM','Lab-1',2),

    (4,2,1,'Wednesday',4,'12:30 PM - 01:30 PM','Lab-2',2),

    (5,1,1,'Thursday',1,'08:45 AM - 09:45 AM','A-103',2);




CREATE TABLE Complaint (
                           complaint_id INT AUTO_INCREMENT PRIMARY KEY,

                           student_id INT NOT NULL,
                           faculty_id INT NULL,
                           admin_id INT NULL,

                           title VARCHAR(150) NOT NULL,
                           description TEXT NOT NULL,

                           status ENUM('Pending','Under Review','Resolved','Rejected') NOT NULL DEFAULT 'Pending',

                           submitted_date DATE NOT NULL,
                           resolved_date DATE NULL,

                           CONSTRAINT fk_complaint_student
                               FOREIGN KEY (student_id)
                                   REFERENCES Student(student_id)
                                   ON DELETE CASCADE,

                           CONSTRAINT fk_complaint_faculty
                               FOREIGN KEY (faculty_id)
                                   REFERENCES Faculty(faculty_id)
                                   ON DELETE SET NULL,

                           CONSTRAINT fk_complaint_admin
                               FOREIGN KEY (admin_id)
                                   REFERENCES Admin(admin_id)
                                   ON DELETE SET NULL
);





INSERT INTO Complaint
(student_id, faculty_id, admin_id, title, description, status, submitted_date, resolved_date)
VALUES

    (1, 1, 1,
     'Projector Issue',
     'Projector in Lab-1 is not working.',
     'Resolved',
     '2026-07-01',
     '2026-07-03'),

    (2, NULL, NULL,
     'Washroom Maintenance',
     'Washroom requires cleaning.',
     'Pending',
     '2026-07-10',
     NULL),

    (3, 2, NULL,
     'Lecture Timing',
     'Lecture started very late.',
     'Under Review',
     '2026-07-12',
     NULL);


SELECT
    s.full_name,
    c.title,
    c.status,
    f.faculty_name,
    a.name AS handled_by
FROM Complaint c
         JOIN Student s
              ON c.student_id = s.student_id
         LEFT JOIN Faculty f
                   ON c.faculty_id = f.faculty_id
         LEFT JOIN Admin a
                   ON c.admin_id = a.admin_id;



CREATE TABLE LostFound (
                           item_id INT AUTO_INCREMENT PRIMARY KEY,

                           student_id INT NOT NULL,
                           admin_id INT NULL,

                           item_name VARCHAR(100) NOT NULL,
                           description TEXT,
                           location VARCHAR(100) NOT NULL,

                           type ENUM('Lost','Found') NOT NULL,

                           status ENUM('Active','Claimed','Closed') NOT NULL DEFAULT 'Active',

                           report_date DATE NOT NULL,

                           CONSTRAINT fk_lostfound_student
                               FOREIGN KEY (student_id)
                                   REFERENCES Student(student_id)
                                   ON DELETE CASCADE,

                           CONSTRAINT fk_lostfound_admin
                               FOREIGN KEY (admin_id)
                                   REFERENCES Admin(admin_id)
                                   ON DELETE SET NULL
);


INSERT INTO LostFound
(student_id, admin_id, item_name, description, location, type, status, report_date)
VALUES

    (1, NULL,
     'Scientific Calculator',
     'Black Casio calculator',
     'Lab-2',
     'Lost',
     'Active',
     '2026-07-15'),

    (2, 1,
     'Water Bottle',
     'Blue Milton bottle',
     'Canteen',
     'Found',
     'Claimed',
     '2026-07-14'),

    (3, NULL,
     'College ID Card',
     'ID Card with blue lanyard',
     'Library',
     'Lost',
     'Active',
     '2026-07-16');


SELECT
    s.full_name,
    lf.item_name,
    lf.type,
    lf.status,
    lf.location
FROM LostFound lf
         JOIN Student s
              ON lf.student_id = s.student_id;




CREATE TABLE NotesRequest (
                              request_id INT AUTO_INCREMENT PRIMARY KEY,

                              student_id INT NOT NULL,
                              subject_id INT NOT NULL,

                              topic VARCHAR(150) NOT NULL,
                              description TEXT,

                              status ENUM('Open','Fulfilled','Closed') NOT NULL DEFAULT 'Open',

                              request_date DATE NOT NULL,

                              CONSTRAINT fk_notesrequest_student
                                  FOREIGN KEY (student_id)
                                      REFERENCES Student(student_id)
                                      ON DELETE CASCADE,

                              CONSTRAINT fk_notesrequest_subject
                                  FOREIGN KEY (subject_id)
                                      REFERENCES Subject(subject_id)
                                      ON DELETE CASCADE
);


INSERT INTO NotesRequest
(student_id, subject_id, topic, description, status, request_date)
VALUES

    (1, 2,
     'Java Collections',
     'Need notes for Java Collections Framework.',
     'Open',
     '2026-07-15'),

    (2, 3,
     'Normalization',
     'Looking for DBMS Normalization notes.',
     'Fulfilled',
     '2026-07-14'),

    (3, 4,
     'Trees',
     'Need handwritten notes for Trees in Data Structures.',
     'Open',
     '2026-07-16');


SELECT
    s.full_name,
    sub.subject_name,
    nr.topic,
    nr.status
FROM NotesRequest nr
         JOIN Student s
              ON nr.student_id = s.student_id
         JOIN Subject sub
              ON nr.subject_id = sub.subject_id;


CREATE TABLE NotesSubmission (
                                 submission_id INT AUTO_INCREMENT PRIMARY KEY,

                                 request_id INT NOT NULL,
                                 student_id INT NOT NULL,
                                 admin_id INT NULL,

                                 file_name VARCHAR(255) NOT NULL,
                                 file_path VARCHAR(500) NOT NULL,

                                 approval_status ENUM('Pending','Approved','Rejected')
    NOT NULL DEFAULT 'Pending',

                                 upload_date DATE NOT NULL,

                                 CONSTRAINT fk_notessubmission_request
                                     FOREIGN KEY (request_id)
                                         REFERENCES NotesRequest(request_id)
                                         ON DELETE CASCADE,

                                 CONSTRAINT fk_notessubmission_student
                                     FOREIGN KEY (student_id)
                                         REFERENCES Student(student_id)
                                         ON DELETE CASCADE,

                                 CONSTRAINT fk_notessubmission_admin
                                     FOREIGN KEY (admin_id)
                                         REFERENCES Admin(admin_id)
                                         ON DELETE SET NULL
);


INSERT INTO NotesSubmission
(request_id, student_id, admin_id, file_name, file_path, approval_status, upload_date)
VALUES

    (1,2,1,
     'Java_Collections_Notes.pdf',
     'uploads/java_collections_notes.pdf',
     'Approved',
     '2026-07-16'),

    (2,1,NULL,
     'DBMS_Normalization.pdf',
     'uploads/dbms_normalization.pdf',
     'Pending',
     '2026-07-17'),

    (3,4,NULL,
     'DS_Trees.pdf',
     'uploads/ds_trees.pdf',
     'Pending',
     '2026-07-17');



SELECT
    s.full_name,
    nr.topic,
    ns.file_name,
    ns.approval_status
FROM NotesSubmission ns
         JOIN Student s
              ON ns.student_id = s.student_id
         JOIN NotesRequest nr
              ON ns.request_id = nr.request_id;





