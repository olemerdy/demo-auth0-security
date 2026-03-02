package org.lafeuille.demo.services

import org.lafeuille.demo.data.Event
import org.lafeuille.demo.data.EventRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CalendarService(
    private val eventRepository: EventRepository,
) {
    fun getCalendarEvents(
        calendarId: UUID,
        pageable: Pageable,
    ): Page<Event> = eventRepository.findAllByCalendarId(calendarId, pageable)
}
