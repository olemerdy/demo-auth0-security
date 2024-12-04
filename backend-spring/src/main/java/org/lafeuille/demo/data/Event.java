package org.lafeuille.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity
public class Event extends AbstractPersistable<UUID> {

    @ManyToOne
    private Calendar calendar;
}
