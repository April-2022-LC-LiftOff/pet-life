package org.launchcode.PetLife.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class PastSurgery extends AbstractEntityNameDate {

    @ManyToOne
    private MedInfo medInfo;

    public PastSurgery(MedInfo medInfo) {
        super();
        this.medInfo = medInfo;
    }

    public PastSurgery() {
    }

    public MedInfo getMedInfo() {
        return medInfo;
    }

    public void setMedInfo(MedInfo medInfo) {
        this.medInfo = medInfo;
    }
}
