package org.launchcode.PetLife.models;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;


@Entity
public class MedInfo extends AbstractEntity {

    private String currentMeds;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private ShotRecord shotRecord;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private PastSurgery pastSurgery;

    @Size(max = 500, message = "Limit up to 500 characters.")
    private String medicalNote;

    public MedInfo(String currentMeds, ShotRecord shotRecord, PastSurgery pastSurgery, String medicalNote) {
        this.currentMeds = currentMeds;
        this.shotRecord = shotRecord;
        this.pastSurgery = pastSurgery;
        this.medicalNote = medicalNote;
    }

    public MedInfo() {
    }

    public ShotRecord getShotRecord() {
        return shotRecord;
    }

    public void setShotRecord(ShotRecord shotRecord) {
        this.shotRecord = shotRecord;
    }

    public PastSurgery getPastSurgery() {
        return pastSurgery;
    }

    public void setPastSurgery(PastSurgery pastSurgery) {
        this.pastSurgery = pastSurgery;
    }

    public String getCurrentMeds() {
        return currentMeds;
    }

    public void setCurrentMeds(String currentMeds) {
        this.currentMeds = currentMeds;
    }

    public String getMedicalNote() {
        return medicalNote;
    }

    public void setMedicalNote(String medicalNote) {
        this.medicalNote = medicalNote;
    }
}
