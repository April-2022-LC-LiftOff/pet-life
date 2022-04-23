package org.launchcode.PetLife.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pet_bio")
public class PetBio extends AbstractEntity{
    private String petName;
    private Integer petAge;
    private Integer petBirthDate;
    private String petSpecies;
    private String petBreed;
    private String petGender;


}
