package xyz.itbs.szupetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import xyz.itbs.szupetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
