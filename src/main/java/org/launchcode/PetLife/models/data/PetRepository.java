package org.launchcode.PetLife.models.data;

import org.launchcode.PetLife.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {

}
