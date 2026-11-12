package com.campushub.model;

import java.time.LocalDate;

public class Event {

    private int eventId;
    private Club club;
    private String eventName;
    private String category;
    private String description;
    private String venue;
    private LocalDate eventDate;
    private LocalDate registrationDeadline;
    private int maxParticipants;

    public Event() {
    }

    public Event(int eventId, Club club, String eventName,
                 String category, String description,
                 String venue, LocalDate eventDate,
                 LocalDate registrationDeadline,
                 int maxParticipants) {

        this.eventId = eventId;
        this.club = club;
        this.eventName = eventName;
        this.category = category;
        this.description = description;
        this.venue = venue;
        this.eventDate = eventDate;
        this.registrationDeadline = registrationDeadline;
        this.maxParticipants = maxParticipants;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalDate getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(LocalDate registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                '}';
    }
}