package org.launchcode.PetLife.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Pet> pets = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addPet(Pet pet) {this.pets.add(pet);}

    public List<Pet> getPets() {
        return pets;
    }

    //    public List<Pet> myPets(List<Pet> pets) {
//        List<Pet> myPets = new ArrayList<Pet>();
//        for (Pet pet : pets) {
//            if (pet.getUser().getId() == this.id) {
//                myPets.add(pet);
//            }
//        }
//        return myPets;
//    }
}
