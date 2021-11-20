package xyz.itbs.szupetclinic.services.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.itbs.szupetclinic.model.Speciality;
import xyz.itbs.szupetclinic.repositories.SpecialityRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SpecialityServiceJPATest {


    Long id = 1L;
    Speciality speciality;
    Set<Speciality> specialitySet;

    @Mock
    SpecialityRepository specialityRepository;

    @InjectMocks
    SpecialityServiceJPA specialityServiceJPA;

    @BeforeEach
    void setUp() {
        specialitySet = new HashSet<>();
        speciality = Speciality.builder().id(id).build();
        specialitySet.add(speciality);
    }

    @Test
    void findAll() {
        when(specialityRepository.findAll()).thenReturn(specialitySet);
        assertEquals(1, specialityServiceJPA.findAll().size());
        verify(specialityRepository,times(1)).findAll();
    }

    @Test
    void findById() {
        when(specialityRepository.findById(anyLong())).thenReturn(Optional.of(speciality));
        Speciality speciality1 = specialityServiceJPA.findById(id);
        assertNotNull(speciality1);
        assertEquals(id,speciality1.getId());
        verify(specialityRepository,times(1)).findById(anyLong());
    }

    @Test
    void findByIdNotFound(){
        when(specialityRepository.findById(anyLong())).thenReturn(Optional.empty());
        Speciality speciality1 = specialityServiceJPA.findById(2L);
        assertNull(speciality1);
        verify(specialityRepository,times(1)).findById(anyLong());
    }

    @Test
    void save() {
        when(specialityRepository.save(any(Speciality.class))).thenReturn(speciality);
        Speciality savedSpeciality = specialityServiceJPA.save(speciality);
        assertEquals(speciality.getId(),savedSpeciality.getId());
        verify(specialityRepository,times(1)).save(any(Speciality.class));
    }

    @Test
    void delete() {
        specialityServiceJPA.delete(speciality);
        verify(specialityRepository,times(1)).delete(any(Speciality.class));
    }

    @Test
    void deleteById() {
        specialityServiceJPA.deleteById(id);
        verify(specialityRepository,times(1)).deleteById(anyLong());
    }
}