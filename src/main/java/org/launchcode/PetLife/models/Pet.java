package org.launchcode.PetLife.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Pet extends AbstractEntity {

    @NotBlank(message = "Name is required.")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters.")
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
