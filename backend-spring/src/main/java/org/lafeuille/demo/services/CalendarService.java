package org.lafeuille.demo.services;

import lombok.RequiredArgsConstructor;
import org.lafeuille.demo.data.Event;
import org.lafeuille.demo.data.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final EventRepository eventRepository;

    public Page<Event> getCalendarEvents(UUID calendarId, Pageable pageable) {
        return eventRepository.findAllByCalendarId(calendarId, pageable);
    }
}
