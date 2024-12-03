package org.lafeuille.demo.data;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.util.UUID;

@Entity
public class Calendar extends AbstractAuditable<String, UUID> {
}
