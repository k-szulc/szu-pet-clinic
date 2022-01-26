package xyz.itbs.szupetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.itbs.szupetclinic.model.Owner;
import xyz.itbs.szupetclinic.model.Pet;
import xyz.itbs.szupetclinic.model.PetType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    Long id = 1L;
    String lastName = "Wayne";
    OwnerServiceMap ownerServiceMap;


    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(),new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(id).lastName(lastName).build());


    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1,ownerSet.size());

    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(id);
        assertNotNull(owner.getId());
        assertEquals(1L,owner.getId());
    }

    @Test
    void saveWithId() {
        Long ownerId = 2L;
        Owner savedOwner = ownerServiceMap.save(Owner.builder().id(ownerId).build());
        assertEquals(ownerId,savedOwner.getId());
    }

    @Test
    void saveWithoutId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void saveWithPetWithId() {
        PetType savedPetType = PetType.builder().id(id).build();
        Pet savedPet = Pet.builder().petType(savedPetType).id(id).build();
        Set<Pet> pets = new HashSet<>();
        pets.add(savedPet);
        Owner savedOwner = ownerServiceMap.save(Owner.builder().id(2L).pets(pets).build());
        assertNotNull(savedOwner);
        assertEquals(2L,savedOwner.getId());
        assertNotNull(savedPet);
        assertEquals(id,savedPet.getId());
        assertNotNull(savedPetType);
        assertEquals(id,savedPetType.getId());
        assertEquals(2,ownerServiceMap.findAll().size());
    }

    @Test
    void saveWithPetWithoutIds() {
        PetType savedPetType = PetType.builder().build();
        Pet savedPet = Pet.builder().petType(savedPetType).build();
        Set<Pet> pets = new HashSet<>();
        pets.add(savedPet);
        Owner savedOwner = ownerServiceMap.save(Owner.builder().pets(pets).build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertNotNull(savedPetType);
        assertNotNull(savedPetType.getId());
        assertEquals(2,ownerServiceMap.findAll().size());
    }

    @Test
    void saveWithPetWithoutTypeException() {
        Pet savedPet = Pet.builder().build();
        Set<Pet> pets = new HashSet<>();
        pets.add(savedPet);
        String errorMsg = "Pet Type is required";
        Exception exception = assertThrows
                (RuntimeException.class,()-> ownerServiceMap.save(Owner.builder().pets(pets).build()));
        assertEquals(errorMsg,exception.getMessage());
        assertEquals(1,ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(id));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(id);
        assertEquals(0, ownerServiceMap.findAll().size());

    }

    @Test
    void findByLastName() {
        Owner namedOwner = ownerServiceMap.findByLastName(lastName);
        assertEquals(id,namedOwner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner namedOwner = ownerServiceMap.findByLastName("test");
        assertNull(namedOwner);
    }

    @Test
    void findAllByLastNameLike(){
        ownerServiceMap.save(Owner.builder().id(2L).lastName("Gordon").build());
        ownerServiceMap.save(Owner.builder().id(3L).lastName("Pennyworth").build());
        List<Owner> oOwners = ownerServiceMap.findAllByLastNameLike("o");
        assertEquals(2,oOwners.size());

    }

    @Test
    void findAllByLastNameLikeCase(){
        ownerServiceMap.save(Owner.builder().id(2L).lastName("Gordon").build());
        ownerServiceMap.save(Owner.builder().id(3L).lastName("Pennyworth").build());
        List<Owner> oOwners = ownerServiceMap.findAllByLastNameLike("O");
        assertEquals(2,oOwners.size());

    }

    @Test
    void findAllByLastNameLikeNotFound(){

        ownerServiceMap.save(Owner.builder().id(2L).lastName("Gordon").build());
        ownerServiceMap.save(Owner.builder().id(3L).lastName("Pennyworth").build());
        List<Owner> oOwners = ownerServiceMap.findAllByLastNameLike("b");
        assertEquals(0,oOwners.size());
    }

}