package org.launchcode.PetLife.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
//@Table(name="pet_bio")
public class PetBio extends AbstractEntity{

    //@ManyToOne
    @JoinColumn(name = "petbio_id")


    private int id;
    private static int nextId = 1;
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    //@Column (name="pet_name")
    private String petName;

//   // @Column (name="pet_age")
     private Integer petAge;
//
//    //@Column (name="pet_birthdate")
//    private Integer petBirthDate;
//
//   // @Column (name="pet_species")
//    private String petSpecies;
//
//    //@Column (name="pet_breed")
//    private String petBreed;
//
//    //@Column (name="pet_gender")
//    private String petGender;
//
//    //@Column (name="pet_weight")
//    private Integer petWeight;
//
//    //@Column (name="pet_color")
//    private String petColor;

    public PetBio(){

    }

    public PetBio(String petName, Integer petAge) {
        this.petName = petName;
        this.petAge = petAge;
        this.id = nextId;
        nextId++;

    }
    //    public PetBio(String petName, Integer petAge, Integer petBirthDate, String petSpecies, String petBreed, String petGender, Integer petWeight, String petColor) {
//        super();
//        this.petName = petName;
//        this.petAge = petAge;
//        this.petBirthDate = petBirthDate;
//        this.petSpecies = petSpecies;
//        this.petBreed = petBreed;
//        this.petGender = petGender;
//        this.petWeight = petWeight;
//        this.petColor = petColor;
//    }


    @Override
    public int getId() {
        return id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Integer getPetAge() {
        return petAge;
    }

    public void setPetAge(Integer petAge) {
        this.petAge = petAge;
    }
//
//    public Integer getPetBirthDate() {
//        return petBirthDate;
//    }
//
//    public void setPetBirthDate(Integer petBirthDate) {
//        this.petBirthDate = petBirthDate;
//    }
//
//    public String getPetSpecies() {
//        return petSpecies;
//    }
//
//    public void setPetSpecies(String petSpecies) {
//        this.petSpecies = petSpecies;
//    }
//
//    public String getPetBreed() {
//        return petBreed;
//    }
//
//    public void setPetBreed(String petBreed) {
//        this.petBreed = petBreed;
//    }
//
//    public String getPetGender() {
//        return petGender;
//    }
//
//    public void setPetGender(String petGender) {
//        this.petGender = petGender;
//    }
//
//    public Integer getPetWeight() {
//        return petWeight;
//    }
//
//    public void setPetWeight(Integer petWeight) {
//        this.petWeight = petWeight;
//    }
//
//    public String getPetColor() {
//        return petColor;
//    }
//
//    public void setPetColor(String petColor) {
//        this.petColor = petColor;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetBio)) return false;
        if (!super.equals(o)) return false;
        PetBio petBio = (PetBio) o;
        return getId() == petBio.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }

    @Override
    public String toString() {
        return "PetBio{" +
                "petName='" + petName + '\'' +
//                ", petAge=" + petAge +
//                ", petBirthDate=" + petBirthDate +
//                ", petSpecies='" + petSpecies + '\'' +
//                ", petBreed='" + petBreed + '\'' +
//                ", petGender='" + petGender + '\'' +
//                ", petWeight=" + petWeight +
//                ", petColor='" + petColor + '\'' +
                '}';
    }
}
