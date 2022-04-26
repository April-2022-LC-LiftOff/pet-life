package org.launchcode.PetLife.models;

import javax.persistence.*;

@Entity
public class ShotRecord extends AbstractEntity {

    private String name;

    private String dateReceived;


    public ShotRecord(String name, String dateReceived) {
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
