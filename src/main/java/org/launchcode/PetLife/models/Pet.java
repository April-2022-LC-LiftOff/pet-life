package org.launchcode.PetLife.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Pet extends AbstractEntity {

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private PetMedInfo petMedInfo;

    public Pet(String name) {
        this.name = name;
    }

    public Pet() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetMedInfo getPetMedInfo() {
        return petMedInfo;
    }

    public void setPetMedInfo(PetMedInfo petMedInfo) {
        this.petMedInfo = petMedInfo;
    }
}
