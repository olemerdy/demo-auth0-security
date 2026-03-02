package org.lafeuille.demo.data

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface EventRepository : JpaRepository<Event, UUID> {
    fun findAllByCalendarId(
        calendarId: UUID,
        pageable: Pageable,
    ): Page<Event>
}
