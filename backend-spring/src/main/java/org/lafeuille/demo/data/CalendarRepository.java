package org.lafeuille.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CalendarRepository extends JpaRepository<Calendar, UUID> {
}
