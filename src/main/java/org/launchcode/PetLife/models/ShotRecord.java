package org.launchcode.PetLife.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class ShotRecord extends AbstractEntityNameDate {

    @ManyToOne
//    @JoinColumn(name = "medInfo_id")
    private MedInfo medInfo;

    public ShotRecord(MedInfo medInfo) {
        super();
        this.medInfo = medInfo;
    }
    public ShotRecord() {}

    public MedInfo getMedInfo() {
        return medInfo;
    }

    public void setMedInfo(MedInfo medInfo) {
        this.medInfo = medInfo;
    }


}
