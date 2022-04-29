package org.launchcode.PetLife.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@MappedSuperclass
public abstract class  AbstractEntity {

        @Id
        @GeneratedValue
        private int id;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity entity = (AbstractEntity) o;
        return getId() == entity.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
