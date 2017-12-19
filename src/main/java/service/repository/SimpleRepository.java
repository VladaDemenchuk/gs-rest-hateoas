package service.repository;

import service.domain.Attendee;
import service.domain.Event;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Repository
public class SimpleRepository {

    private final HashMap<Integer, Event> events = new HashMap<>();
    private final HashMap<Integer, Attendee> attendees = new HashMap<>();

    SimpleRepository() {
        Random random = new Random();

        for (int i = 0; i < random.nextInt(10) + 4; i++) {
            createEvent();
        }

        for (int i = 0; i < random.nextInt(40) + 20; i++) {
            createAttendee();
        }

        for (Event event : events.values()) {
            for (Attendee attendee : attendees.values()) {
                if (random.nextInt(10) < 3) {
                    attendee.getEvents().add(event);
                    event.getAttendees().add(attendee);
                }
            }
        }
    }

    public Map<Integer, Event> getEvents() {
        return events;
    }

    public List<Event> getEventsByAttendee(Integer attendeeId) {
        return events.values().stream()
                .filter(event ->
                        event.getAttendees().stream()
                                .anyMatch(attendee -> attendee.getIdentifier().equals(attendeeId)))
                .collect(Collectors.toList());
    }

    public List<Attendee> getAttendeesByEvent(Integer eventId) {
        return attendees.values().stream()
                .filter(attendee ->
                        attendee.getEvents().stream()
                                .anyMatch(event -> event.getIdentifier().equals(eventId)))
                .collect(Collectors.toList());
    }

    public Map<Integer, Attendee> getAttendees() {
        return attendees;
    }


    private void createEvent() {
        Event event = new Event();
        events.put(event.getIdentifier(), event);
    }

    private void createAttendee() {
        Attendee attendee = new Attendee();
        attendees.put(attendee.getIdentifier(), attendee);
    }

}
