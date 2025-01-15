package org.lafeuille.demo.web;

import org.lafeuille.demo.data.Event;
import org.lafeuille.demo.services.CalendarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("{id}/events")
    Page<Event> readCalendarEvents(@PathVariable UUID id, Pageable pageable) {
        return calendarService.getCalendarEvents(id, pageable);
    }
}
