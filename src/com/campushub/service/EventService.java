package com.campushub.service;

import com.campushub.dao.EventDAO;
import com.campushub.dao.EventRegistrationDAO;
import com.campushub.model.Event;
import com.campushub.model.Student;

import java.util.List;
import java.util.Scanner;

public class EventService {

    private EventDAO eventDAO = new EventDAO();
    private EventRegistrationDAO registrationDAO = new EventRegistrationDAO();

    public void showEvents(Student student) {

        List<Event> events = eventDAO.getAllEvents();

        if (events.isEmpty()) {

            System.out.println("\nNo Events Available.");
            return;

        }

        System.out.println("\n==================================================");
        System.out.println("               COLLEGE EVENTS");
        System.out.println("==================================================");

        for (Event event : events) {

            System.out.println(event.getEventId() + ". " + event.getEventName());
            System.out.println("   Date : " + event.getEventDate());
            System.out.println("   Venue: " + event.getVenue());
            System.out.println("   " + event.getDescription());
            System.out.println();

        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Event ID to Register (0 to Back): ");
        int eventId = com.campushub.utils.ConsoleUtils.readInt(scanner);

        if (eventId == 0)
            return;

        boolean eventExists = false;
        for (Event event : events) {
            if (event.getEventId() == eventId) {
                eventExists = true;
                break;
            }
        }

        if (!eventExists) {
            System.out.println("\nInvalid Event ID! Please select a valid event from the list.");
            return;
        }

        if (registrationDAO.isAlreadyRegistered(student.getStudentId(), eventId)) {

            System.out.println("\nYou are already registered for this event.");

        } else {

            if (registrationDAO.registerEvent(student.getStudentId(), eventId)) {

                System.out.println("\nEvent Registration Successful!");

            } else {

                System.out.println("\nUnable to register for the event.");

            }

        }

    }

}