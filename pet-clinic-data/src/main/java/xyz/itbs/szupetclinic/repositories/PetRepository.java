package xyz.itbs.szupetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import xyz.itbs.szupetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
