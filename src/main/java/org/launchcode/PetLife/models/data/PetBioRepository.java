package org.launchcode.PetLife.models.data;

import org.launchcode.PetLife.models.PetBio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetBioRepository extends CrudRepository<PetBio, Integer> {
}
