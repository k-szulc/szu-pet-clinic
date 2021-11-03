package xyz.itbs.szupetclinic.services.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import xyz.itbs.szupetclinic.model.Pet;
import xyz.itbs.szupetclinic.repositories.PetRepository;
import xyz.itbs.szupetclinic.services.PetService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
public class PetServiceJPA implements PetService {

    private final PetRepository petRepository;

    public PetServiceJPA(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> petSet = new HashSet<>();
        petRepository.findAll().forEach(petSet::add);
        return petSet;
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }
}
