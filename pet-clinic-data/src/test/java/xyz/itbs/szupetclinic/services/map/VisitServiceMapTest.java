package xyz.itbs.szupetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.itbs.szupetclinic.model.Owner;
import xyz.itbs.szupetclinic.model.Pet;
import xyz.itbs.szupetclinic.model.Visit;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class VisitServiceMapTest {

    Long id = 1L;
    Long visitId = 2L;
    VisitServiceMap visitServiceMap;

    @BeforeEach
    void setUp() {
        Owner owner = Owner.builder().id(id).build();
        Pet pet = Pet.builder().id(id).owner(owner).visits(new HashSet<>()).build();
        visitServiceMap = new VisitServiceMap();
        Visit visit = Visit.builder().id(id).pet(pet).build();
        visitServiceMap.save(visit);
    }

    @Test
    void findAll() {
        assertEquals(1,visitServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Visit visit = visitServiceMap.findById(id);
        assertNotNull(visit.getId());
        assertEquals(id,visit.getId());
    }

    @Test
    void saveWithoutId() {
        Owner owner = Owner.builder().id(visitId).build();
        Pet pet = Pet.builder().id(visitId).owner(owner).visits(new HashSet<>()).build();
        Visit visit = Visit.builder().pet(pet).build();
        visitServiceMap.save(visit);
        assertNotNull(visit);
        assertEquals(visitId, visit.getId());
        assertEquals(2,visitServiceMap.findAll().size());
        assertEquals(visit,pet.getVisits().stream().findFirst().orElse(null));
    }

    @Test
    void saveWithId() {
        Owner owner = Owner.builder().id(visitId).build();
        Pet pet = Pet.builder().id(visitId).owner(owner).visits(new HashSet<>()).build();
        Visit visit = Visit.builder().id(visitId).pet(pet).build();
        visitServiceMap.save(visit);
        assertNotNull(visit);
        assertEquals(visitId, visit.getId());
        assertEquals(2,visitServiceMap.findAll().size());
        assertEquals(visit,pet.getVisits().stream().findFirst().orElse(null));
    }

    @Test
    void saveWithoutPetException() {
        Visit visit = Visit.builder().id(visitId).build();
        String errorMsg = "Invalid Visit - Owner or Pet cannot be null !";
        Exception exception = assertThrows(RuntimeException.class,()->{visitServiceMap.save(visit);});
        assertEquals(errorMsg, exception.getMessage());
        assertEquals(1,visitServiceMap.findAll().size());
    }

    @Test
    void saveWithPetWithoutOwnerException() {
        Pet pet = Pet.builder().id(visitId).visits(new HashSet<>()).build();
        Visit visit = Visit.builder().id(visitId).pet(pet).build();
        String errorMsg = "Invalid Visit - Owner or Pet cannot be null !";
        Exception exception = assertThrows(RuntimeException.class,()->{visitServiceMap.save(visit);});
        assertEquals(errorMsg, exception.getMessage());
        assertEquals(1,visitServiceMap.findAll().size());
    }

    @Test
    void saveWithPetWithoutIdWithOwnerException() {
        Owner owner = Owner.builder().id(visitId).build();
        Pet pet = Pet.builder().owner(owner).visits(new HashSet<>()).build();
        Visit visit = Visit.builder().id(visitId).pet(pet).build();
        String errorMsg = "Invalid Visit - Owner or Pet cannot be null !";
        Exception exception = assertThrows(RuntimeException.class,()->{visitServiceMap.save(visit);});
        assertEquals(errorMsg, exception.getMessage());
        assertEquals(1,visitServiceMap.findAll().size());
    }

    @Test
    void saveWithPetWithOwnerWithoutIdException() {
        Owner owner = Owner.builder().build();
        Pet pet = Pet.builder().id(visitId).owner(owner).visits(new HashSet<>()).build();
        Visit visit = Visit.builder().id(visitId).pet(pet).build();
        String errorMsg = "Invalid Visit - Owner or Pet cannot be null !";
        Exception exception = assertThrows(RuntimeException.class,()->{visitServiceMap.save(visit);});
        assertEquals(errorMsg, exception.getMessage());
        assertEquals(1,visitServiceMap.findAll().size());
    }

    @Test
    void delete() {
        visitServiceMap.delete(visitServiceMap.findById(id));
        assertEquals(0,visitServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        visitServiceMap.deleteById(id);
        assertEquals(0,visitServiceMap.findAll().size());
    }
}