package xyz.itbs.szupetclinic.services.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.itbs.szupetclinic.model.Owner;
import xyz.itbs.szupetclinic.repositories.OwnerRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJPATest {

    Long id = 1L;
    String lastName = "Wayne";
    Set<Owner> ownerSet;
    Owner owner;

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerServiceJPA ownerServiceJPA;

    @BeforeEach
    void setUp() {
        ownerSet = new HashSet<>();
        owner = Owner.builder().id(id).lastName(lastName).build();
        ownerSet.add(owner);
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(ownerSet);
        assertEquals(1,ownerServiceJPA.findAll().size());
        verify(ownerRepository,times(1)).findAll();

    }

    @Test
    void findById() {
        when(ownerRepository.findById(id)).thenReturn(Optional.of(owner));
        Owner owner = ownerServiceJPA.findById(id);
        assertNotNull(owner);
        assertEquals(id,owner.getId());
        verify(ownerRepository,times(1)).findById(id);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner1 = ownerServiceJPA.findById(3L);
        assertNull(owner1);
        verify(ownerRepository,times(1)).findById(anyLong());
    }

    @Test
    void save() {
        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);
        Owner savedOwner = ownerServiceJPA.save(owner);
        assertEquals(owner.getId(),savedOwner.getId());
        assertEquals(owner.getLastName(), savedOwner.getLastName());
        verify(ownerRepository,times(1)).save(any(Owner.class));

    }

    @Test
    void delete() {
        ownerServiceJPA.delete(owner);
        verify(ownerRepository,times(1)).delete(any(Owner.class));
    }

    @Test
    void deleteById() {
        ownerServiceJPA.deleteById(id);
        verify(ownerRepository,times(1)).deleteById(id);

    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(lastName)).
                thenReturn(owner);
        Owner owner1 = ownerServiceJPA.findByLastName(lastName);
        assertNotNull(owner1);
        assertEquals(lastName,owner1.getLastName());
        verify(ownerRepository,times(1)).findByLastName(lastName);
    }
    @Test
    void findByLastNameNotFound() {
        when(ownerRepository.findByLastName(anyString())).
                thenReturn(null);
        Owner owner1 = ownerServiceJPA.findByLastName("foo");
        assertNull(owner1);
        verify(ownerRepository,times(1)).findByLastName(anyString());
    }
}