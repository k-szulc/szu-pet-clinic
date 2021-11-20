package xyz.itbs.szupetclinic.services.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.itbs.szupetclinic.model.Vet;
import xyz.itbs.szupetclinic.repositories.VetRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class VetServiceJPATest {


    Long id = 1L;
    Vet vet;
    Set<Vet> vetSet;

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetServiceJPA vetServiceJPA;

    @BeforeEach
    void setUp() {
        vetSet = new HashSet<>();
        vet = Vet.builder().id(id).build();
        vetSet.add(vet);
    }

    @Test
    void findAll() {
        when(vetRepository.findAll()).thenReturn(vetSet);
        assertEquals(1, vetServiceJPA.findAll().size());
        verify(vetRepository,times(1)).findAll();
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(vet));
        Vet vet1 = vetServiceJPA.findById(id);
        assertNotNull(vet1);
        assertEquals(id,vet1.getId());
        verify(vetRepository,times(1)).findById(anyLong());
    }

    @Test
    void findByIdNotFound(){
        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());
        Vet vet1 = vetServiceJPA.findById(2L);
        assertNull(vet1);
        verify(vetRepository,times(1)).findById(anyLong());
    }

    @Test
    void save() {
        when(vetRepository.save(any(Vet.class))).thenReturn(vet);
        Vet savedVet = vetServiceJPA.save(vet);
        assertEquals(vet.getId(),savedVet.getId());
        verify(vetRepository,times(1)).save(any(Vet.class));
    }

    @Test
    void delete() {
        vetServiceJPA.delete(vet);
        verify(vetRepository,times(1)).delete(any(Vet.class));
    }

    @Test
    void deleteById() {
        vetServiceJPA.deleteById(id);
        verify(vetRepository,times(1)).deleteById(anyLong());
    }

}