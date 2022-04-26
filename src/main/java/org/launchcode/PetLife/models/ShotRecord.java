package org.launchcode.PetLife.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class ShotRecord {
    @Id
    @GeneratedValue
    private int id;
    private static int nextId = 1;

    private String shotName;

    private Date dateReceived;

    public ShotRecord(String shotName, Date dateReceived) {
        super();
        this.shotName = shotName;
        this.dateReceived = dateReceived;
    }

    public ShotRecord() {
        this.id = nextId;
        nextId++;
    }

    public String getShotName() {
        return shotName;
    }

    public void setShotName(String shotName) {
        this.shotName = shotName;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }
}
