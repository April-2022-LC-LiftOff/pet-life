package org.launchcode.PetLife.models;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@MappedSuperclass
public class AbstractEntityNameDate extends AbstractEntity{

    @Size(min = 2, max = 30, message = "Name must be between 2 to 30 characters.")
    private String name;

    private String date;

    public AbstractEntityNameDate(@Size(min = 2, max = 30, message = "Name must be between 2 to 30 characters.") String name, String date) {
        this.name = name;
        this.date = date;
    }

    public AbstractEntityNameDate() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
