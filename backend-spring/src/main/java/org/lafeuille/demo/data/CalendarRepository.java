package org.lafeuille.demo.data;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, UUID> {}
