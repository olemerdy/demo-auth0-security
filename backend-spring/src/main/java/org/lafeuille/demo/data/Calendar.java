package org.lafeuille.demo.data;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity
public class Calendar extends AbstractPersistable<UUID> {
}
