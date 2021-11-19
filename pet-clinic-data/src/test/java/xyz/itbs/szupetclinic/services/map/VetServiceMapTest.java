package xyz.itbs.szupetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.itbs.szupetclinic.model.Speciality;
import xyz.itbs.szupetclinic.model.Vet;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VetServiceMapTest {

    Long id = 1L;
    VetServiceMap vetServiceMap;

    @BeforeEach
    void setUp() {
        vetServiceMap = new VetServiceMap(new SpecialityServiceMap());
        vetServiceMap.save(Vet.builder().id(id).build());
    }

    @Test
    void findAll() {
        assertEquals(1,vetServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Vet vet = vetServiceMap.findById(id);
        assertNotNull(vet.getId());
        assertEquals(id,vet.getId());
    }

    @Test
    void saveWithoutId() {
        Long vetId = 2L;
        Vet savedVet = vetServiceMap.save(Vet.builder().build());
        assertNotNull(savedVet);
        assertEquals(vetId,savedVet.getId());
        assertEquals(2,vetServiceMap.findAll().size());
    }

    @Test
    void saveWithId() {
        Long vetId = 2L;
        Vet savedVet = vetServiceMap.save(Vet.builder().id(vetId).build());
        assertNotNull(savedVet);
        assertEquals(vetId,savedVet.getId());
        assertEquals(2,vetServiceMap.findAll().size());
    }

    @Test
    void saveWithSpecWithoutId() {
        Speciality spec = Speciality.builder().build();
        Set<Speciality> specialitySet = new HashSet<>();
        specialitySet.add(spec);
        Vet savedVet = vetServiceMap.save(Vet.builder().specialities(specialitySet).build());
        assertNotNull(savedVet);
        assertEquals(2L,savedVet.getId());
        assertNotNull(spec);
        assertEquals(id,spec.getId());
        assertEquals(2,vetServiceMap.findAll().size());
    }

    @Test
    void saveWithSpecWithId() {
        Speciality spec = Speciality.builder().id(id).build();
        Set<Speciality> specialitySet = new HashSet<>();
        specialitySet.add(spec);
        Vet savedVet = vetServiceMap.save(Vet.builder().specialities(specialitySet).build());
        assertNotNull(savedVet);
        assertEquals(2L,savedVet.getId());
        assertNotNull(spec);
        assertEquals(id,spec.getId());
        assertEquals(2,vetServiceMap.findAll().size());
    }

    @Test
    void delete() {
        vetServiceMap.delete(vetServiceMap.findById(id));
        assertEquals(0,vetServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        vetServiceMap.deleteById(id);
        assertEquals(0,vetServiceMap.findAll().size());
    }
}