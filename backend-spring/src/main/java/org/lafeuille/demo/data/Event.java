package org.lafeuille.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.util.UUID;

@Entity
public class Event extends AbstractAuditable<String, UUID> {

    @ManyToOne
    private Calendar calendar;
}
