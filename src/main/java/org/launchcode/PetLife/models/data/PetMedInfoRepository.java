package org.launchcode.PetLife.models.data;


import org.launchcode.PetLife.models.MedInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetMedInfoRepository extends CrudRepository<MedInfo, Integer> {
}
