package org.lafeuille.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Event extends AbstractPersistable<UUID> {

	@ManyToOne
	private Calendar calendar;
}
