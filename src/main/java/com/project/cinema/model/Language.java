package com.project.cinema.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Language extends AbstractEntity {

    @Column(length = 20, unique = true, nullable = false)
    private String name;

    @Override
    public void update(AbstractEntity other) {
        if (other instanceof Language l) {

            if (l.name != null && !l.name.equals(name)) {
                name = l.name;
            }
        }
    }
}
