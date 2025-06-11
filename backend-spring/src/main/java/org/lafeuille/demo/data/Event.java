package org.lafeuille.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity
public class Event extends AbstractPersistable<UUID> {

    @Nullable
    @ManyToOne
    private Calendar calendar;
}
