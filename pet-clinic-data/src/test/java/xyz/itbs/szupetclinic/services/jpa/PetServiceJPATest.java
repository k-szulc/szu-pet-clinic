package xyz.itbs.szupetclinic.services.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.itbs.szupetclinic.model.Pet;
import xyz.itbs.szupetclinic.repositories.PetRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceJPATest {

    Long id = 1L;
    Long secondId = 2L;
    Pet pet;
    Set<Pet> petSet;

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetServiceJPA petServiceJPA;

    @BeforeEach
    void setUp() {
        petSet = new HashSet<>();
        pet = Pet.builder().id(id).build();
        petSet.add(pet);
    }

    @Test
    void findAll() {
        when(petRepository.findAll()).thenReturn(petSet);
        assertEquals(1,petServiceJPA.findAll().size());
        verify(petRepository,times(1)).findAll();
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(pet));
        Pet pet1 = petServiceJPA.findById(id);
        assertNotNull(pet1);
        assertEquals(id,pet.getId());
        verify(petRepository,times(1)).findById(anyLong());
    }

    @Test
    void findByIdNotFound(){
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());
        Pet pet1 = petServiceJPA.findById(secondId);
        assertNull(pet1);
        verify(petRepository,times(1)).findById(anyLong());
    }

    @Test
    void save() {
        when(petRepository.save(any(Pet.class))).thenReturn(pet);
        Pet savedPet = petServiceJPA.save(pet);
        assertEquals(pet.getId(),savedPet.getId());
        verify(petRepository,times(1)).save(any(Pet.class));
    }

    @Test
    void delete() {
        petServiceJPA.delete(pet);
        verify(petRepository,times(1)).delete(any(Pet.class));
    }

    @Test
    void deleteById() {
        petServiceJPA.deleteById(id);
        verify(petRepository,times(1)).deleteById(anyLong());
    }
}