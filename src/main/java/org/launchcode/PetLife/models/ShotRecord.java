package org.launchcode.PetLife.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class ShotRecord extends AbstractEntityNameDate {

    @Size(min = 2, max = 30, message = "Name must be between 2 to 30 characters.")
    private String name;

    private String dateReceived = "no date info";


    public ShotRecord(@Size(min = 2, max = 30, message = "Name must be between 2 to 30 characters.") String name,
                      String dateReceived) {
        this.name = name;
        this.dateReceived = dateReceived;
    }

    public ShotRecord() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }


}
