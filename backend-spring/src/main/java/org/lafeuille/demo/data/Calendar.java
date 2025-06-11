package org.lafeuille.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Entity
public class Calendar extends AbstractPersistable<UUID> {

    @OneToMany(mappedBy = "calendar")
    private Collection<Event> events = new HashSet<>();
}
