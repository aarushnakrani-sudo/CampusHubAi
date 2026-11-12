package com.campushub.service;

import com.campushub.dao.AdminEventDAO;
import com.campushub.model.Event;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class AdminEventService {

    private AdminEventDAO eventDAO = new AdminEventDAO();

    public void manageEvents() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n==================================================");
            System.out.println("             EVENT MANAGEMENT");
            System.out.println("==================================================");

            List<Event> events = eventDAO.getAllEvents();

            if (events.isEmpty()) {

                System.out.println("No Events Available.");

            } else {

                for (Event event : events) {

                    int count = eventDAO.getParticipantCount(event.getEventId());
                    System.out.println("Event ID     : " + event.getEventId());
                    System.out.println("Event Name   : " + event.getEventName());
                    System.out.println("Description  : " + event.getDescription());
                    System.out.println("Venue        : " + event.getVenue());
                    System.out.println("Date         : " + event.getEventDate());
                    System.out.println("Participants : " + count);
                    System.out.println("--------------------------------------------");

                }

            }

            System.out.println("\n1. Add Event");
            System.out.println("2. Delete Event");
            System.out.println("3. Back");

            System.out.print("\nEnter Choice : ");

            int choice = com.campushub.utils.ConsoleUtils.readInt(scanner);

            switch (choice) {

                case 1:

                    System.out.print("Event Name : ");
                    String name = scanner.nextLine();

                    System.out.print("Description : ");
                    String description = scanner.nextLine();

                    System.out.print("Venue : ");
                    String venue = scanner.nextLine();

                    System.out.print("Event Date (yyyy-mm-dd) : ");
                    Date eventDate = com.campushub.utils.ConsoleUtils.readDate(scanner);

                    if (eventDAO.addEvent(name, description, venue, eventDate)) {

                        System.out.println("\nEvent Added Successfully.");

                    } else {

                        System.out.println("\nUnable to Add Event.");

                    }

                    break;

                case 2:

                    System.out.print("Enter Event ID : ");
                    int eventId = com.campushub.utils.ConsoleUtils.readInt(scanner);

                    if (eventDAO.deleteEvent(eventId)) {

                        System.out.println("\nEvent Deleted Successfully.");

                    } else {

                        System.out.println("\nEvent Not Found.");

                    }

                    break;

                case 3:
                    return;

                default:
                    System.out.println("\nInvalid Choice.");

            }

        }

    }

}