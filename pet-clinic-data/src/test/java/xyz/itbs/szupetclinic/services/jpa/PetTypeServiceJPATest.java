package xyz.itbs.szupetclinic.services.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.itbs.szupetclinic.model.PetType;
import xyz.itbs.szupetclinic.repositories.PetTypeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetTypeServiceJPATest {

    Long id = 1L;
    PetType petType;
    Set<PetType> petTypeSet;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeServiceJPA petTypeServiceJPA;

    @BeforeEach
    void setUp() {
        petTypeSet = new HashSet<>();
        petType = PetType.builder().id(id).build();
        petTypeSet.add(petType);
    }

    @Test
    void findAll() {
        when(petTypeRepository.findAll()).thenReturn(petTypeSet);
        assertEquals(1,petTypeServiceJPA.findAll().size());
        verify(petTypeRepository,times(1)).findAll();
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(petType));
        PetType petType1 = petTypeServiceJPA.findById(id);
        assertNotNull(petType1);
        assertEquals(id,petType1.getId());
        verify(petTypeRepository,times(1)).findById(anyLong());
    }

    @Test
    void findByIdNotFound(){
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());
        PetType petType1 = petTypeServiceJPA.findById(2L);
        assertNull(petType1);
        verify(petTypeRepository,times(1)).findById(anyLong());
    }

    @Test
    void save() {
        when(petTypeRepository.save(any(PetType.class))).thenReturn(petType);
        PetType savedPetType = petTypeServiceJPA.save(petType);
        assertEquals(petType.getId(),savedPetType.getId());
        verify(petTypeRepository,times(1)).save(any(PetType.class));
    }

    @Test
    void delete() {
        petTypeServiceJPA.delete(petType);
        verify(petTypeRepository,times(1)).delete(any(PetType.class));
    }

    @Test
    void deleteById() {
        petTypeServiceJPA.deleteById(id);
        verify(petTypeRepository,times(1)).deleteById(anyLong());
    }
}