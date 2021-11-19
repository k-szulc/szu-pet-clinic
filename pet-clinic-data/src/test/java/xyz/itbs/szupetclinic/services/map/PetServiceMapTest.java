package xyz.itbs.szupetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.itbs.szupetclinic.model.Pet;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceMapTest {

        Long id = 1L;
        PetServiceMap petServiceMap;


    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap();
        petServiceMap.save(Pet.builder().id(id).build());
    }

    @Test
    void findAll() {
        assertEquals(1,petServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Pet pet = petServiceMap.findById(id);
        assertNotNull(pet.getId());
        assertEquals(id,pet.getId());
    }

    @Test
    void saveWithId() {
        Long petId = 2L;
        Pet savedPet = petServiceMap.save(Pet.builder().id(petId).build());
        assertEquals(petId,savedPet.getId());
        assertEquals(2,petServiceMap.findAll().size());
    }

    @Test
    void saveWithoutId() {
        Long petId = 2L;
        Pet savedPet = petServiceMap.save(Pet.builder().build());
        assertNotNull(savedPet);
        assertEquals(petId,savedPet.getId());
        assertEquals(2,petServiceMap.findAll().size());
    }

    @Test
    void delete() {
        petServiceMap.delete(petServiceMap.findById(id));
        assertEquals(0,petServiceMap.findAll().size());

    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(id);
        assertEquals(0,petServiceMap.findAll().size());

    }
}