package xyz.itbs.szupetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.itbs.szupetclinic.model.PetType;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeServiceMapTest {

    Long id = 1L;
    PetTypeServiceMap petTypeServiceMap;

    @BeforeEach
    void setUp() {
        petTypeServiceMap = new PetTypeServiceMap();
        petTypeServiceMap.save(PetType.builder().id(id).build());
    }

    @Test
    void findAll() {
        assertEquals(1,petTypeServiceMap.findAll().size());
    }

    @Test
    void findById() {
        PetType petType = petTypeServiceMap.findById(id);
        assertNotNull(petType.getId());
        assertEquals(id,petType.getId());
    }

    @Test
    void saveWithId() {
        Long petTypeId = 2L;
        PetType savedPetType = petTypeServiceMap.save(PetType.builder().id(petTypeId).build());
        assertEquals(petTypeId,savedPetType.getId());
        assertEquals(2,petTypeServiceMap.findAll().size());
    }

    @Test
    void saveWithoutId() {
        Long petTypeId = 2L;
        PetType savedPetType = petTypeServiceMap.save(PetType.builder().build());
        assertEquals(petTypeId,savedPetType.getId());
        assertEquals(2,petTypeServiceMap.findAll().size());
    }

    @Test
    void delete() {
        petTypeServiceMap.delete(petTypeServiceMap.findById(id));
        assertEquals(0,petTypeServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        petTypeServiceMap.deleteById(id);
        assertEquals(0,petTypeServiceMap.findAll().size());
    }
}