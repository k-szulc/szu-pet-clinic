package xyz.itbs.szupetclinic.services.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.itbs.szupetclinic.model.Visit;
import xyz.itbs.szupetclinic.repositories.VisitRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class VisitServiceJPATest {
    Long id = 1L;
    Visit visit;
    Set<Visit> visitSet;

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitServiceJPA visitServiceJPA;

    @BeforeEach
    void setUp() {
        visitSet = new HashSet<>();
        visit = Visit.builder().id(id).build();
        visitSet.add(visit);
    }

    @Test
    void findAll() {
        when(visitRepository.findAll()).thenReturn(visitSet);
        assertEquals(1, visitServiceJPA.findAll().size());
        verify(visitRepository,times(1)).findAll();
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit visit1 = visitServiceJPA.findById(id);
        assertNotNull(visit1);
        assertEquals(id,visit1.getId());
        verify(visitRepository,times(1)).findById(anyLong());
    }

    @Test
    void findByIdNotFound(){
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());
        Visit visit1 = visitServiceJPA.findById(2L);
        assertNull(visit1);
        verify(visitRepository,times(1)).findById(anyLong());
    }

    @Test
    void save() {
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit savedVisit = visitServiceJPA.save(visit);
        assertEquals(visit.getId(),savedVisit.getId());
        verify(visitRepository,times(1)).save(any(Visit.class));
    }

    @Test
    void delete() {
        visitServiceJPA.delete(visit);
        verify(visitRepository,times(1)).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        visitServiceJPA.deleteById(id);
        verify(visitRepository,times(1)).deleteById(anyLong());
    }

}