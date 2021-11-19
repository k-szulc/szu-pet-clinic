package xyz.itbs.szupetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.itbs.szupetclinic.model.Speciality;

import static org.junit.jupiter.api.Assertions.*;

class SpecialityServiceMapTest {

    Long id = 1L;
    SpecialityServiceMap specialityServiceMap;

    @BeforeEach
    void setUp() {
        specialityServiceMap = new SpecialityServiceMap();
        specialityServiceMap.save(Speciality.builder().id(id).build());
    }

    @Test
    void findAll() {
        assertEquals(1,specialityServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Speciality speciality = specialityServiceMap.findById(id);
        assertNotNull(speciality.getId());
        assertEquals(id,speciality.getId());
    }

    @Test
    void saveWithId() {
        Long specId = 2L;
        Speciality speciality = specialityServiceMap.save(Speciality.builder().id(specId).build());
        assertEquals(specId,speciality.getId());
        assertEquals(2,specialityServiceMap.findAll().size());
    }

    @Test
    void saveWithoutId() {
        Long specId = 2L;
        Speciality speciality = specialityServiceMap.save(Speciality.builder().build());
        assertEquals(specId,speciality.getId());
        assertEquals(2,specialityServiceMap.findAll().size());
    }

    @Test
    void delete() {
        specialityServiceMap.delete(specialityServiceMap.findById(id));
        assertEquals(0,specialityServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        specialityServiceMap.deleteById(id);
        assertEquals(0,specialityServiceMap.findAll().size());
    }
}