package org.lafeuille.demo.data

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CalendarRepository : JpaRepository<Calendar, UUID>
