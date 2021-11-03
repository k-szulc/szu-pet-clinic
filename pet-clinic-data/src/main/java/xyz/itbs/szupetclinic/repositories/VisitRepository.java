package xyz.itbs.szupetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import xyz.itbs.szupetclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
