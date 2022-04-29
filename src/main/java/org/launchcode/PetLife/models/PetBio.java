package org.launchcode.PetLife.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;


@Entity
//@Table(name="pet_bio")
public class PetBio<petGender> extends AbstractEntity{

    //@ManyToOne
//    @JoinColumn(name = "petbio_id")


    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 50, message = "Name must be between 3 and 50 characters")
      private String petName;

 @Size(min = 1, max = 50, message = " Age must be between 1 and 50 characters")
    @NotNull(message = "Age is required")
     private Integer petAge;

 @Size(min = 1, max = 50, message = " Species must be between 1 and 50 characters")
@NotNull(message = "Species is required")
    private String petSpecies;
    
@Size(min = 1, max = 50, message = " Breed must be between 1 and 50 characters")
@NotNull(message = "Breed is required or input unknown")
    private String petBreed;

    private String petGender;

 @Size(min = 1, max = 50, message = " Weight must be between 1 and 50 characters")
@NotNull(message = "Weight is required")
    private Integer petWeight;

@NotNull(message = "Color of pet is required")
 @Size(min = 1, max = 50, message = " Color must be between 1 and 50 characters")
    private String petColor;


 @Size(min = 1, max = 200, message = " must be between 1 and 200 characters")
    private String petBehavior;

    public PetBio(String petName, Integer petAge,String petSpecies, String petBreed, String petGender, Integer petWeight, String petColor, String petBehavior) {
        this.petName = petName;
        this.petAge = petAge;

        this.petSpecies = petSpecies;
        this.petBreed = petBreed;
        this.petGender = petGender;
        this.petWeight = petWeight;
        this.petColor = petColor;
        this.petBehavior = petBehavior;
    }

    public PetBio(){

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


    public String getPetSpecies() {
        return petSpecies;
    }

    public void setPetSpecies(String petSpecies) {
        this.petSpecies = petSpecies;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }


    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public Integer getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(Integer petWeight) {
        this.petWeight = petWeight;
    }

    public String getPetColor() {
        return petColor;
    }

    public void setPetColor(String petColor) {
        this.petColor = petColor;
    }

    public String getPetBehavior() {
        return petBehavior;
    }

    public void setPetBehavior(String petBehavior) {
        this.petBehavior = petBehavior;
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
