package org.launchcode.PetLife.models.data;

import org.launchcode.PetLife.models.AbstractEntity;
import org.launchcode.PetLife.models.PetBio;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PetBioData{

    private static final Map<Integer, PetBio> pets = new HashMap<Integer, PetBio>();


    public static Collection<PetBio> getAll(){
        return pets.values();
    }

    public static PetBio getbyId(int id){
        return pets.get(id);
    }

    public static void add(PetBio petbio){
      pets.put(petbio.getId(), petbio);
    }

    public static void remove(int id){
        pets.remove(id);
    }

}
