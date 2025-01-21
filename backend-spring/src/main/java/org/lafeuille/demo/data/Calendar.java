package org.lafeuille.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Collection;
import java.util.UUID;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Calendar extends AbstractPersistable<UUID> {

  @OneToMany(mappedBy = "calendar")
  private Collection<Event> events;
}
