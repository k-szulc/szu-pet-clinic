package xyz.itbs.szupetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.itbs.szupetclinic.model.*;
import xyz.itbs.szupetclinic.services.*;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }

    }

    private void loadData() {
        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);
        PetType fish = new PetType();
        fish.setName("Fish");
        PetType savedFishPetType = petTypeService.save(fish);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("James");
        owner1.setLastName("Gordon");
        owner1.setAddress("GCPD Dept.");
        owner1.setCity("Gotham");
        owner1.setTelephone("911");

        Pet jamesPet = new Pet();
        jamesPet.setPetName("Joker");
        jamesPet.setPetType(savedCatPetType);
        jamesPet.setBirthDate(LocalDate.now());
        jamesPet.setOwner(owner1);
        owner1.getPets().add(jamesPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Alfred");
        owner2.setLastName("Pennyworth");
        owner2.setAddress("Wayne Manor");
        owner2.setCity("Gotham");
        owner2.setTelephone("12345678");

        Pet alfredPet = new Pet();
        alfredPet.setPetName("Ace");
        alfredPet.setPetType(savedDogPetType);
        alfredPet.setBirthDate(LocalDate.now());
        alfredPet.setOwner(owner2);
        owner2.getPets().add(alfredPet);
        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("Duke");
        owner3.setLastName("Thomas");
        owner3.setAddress("Narrows");
        owner3.setCity("Gotham");
        owner3.setTelephone("23232323");

        Pet dukePet = new Pet();
        dukePet.setPetName("Signal");
        dukePet.setPetType(savedFishPetType);
        dukePet.setBirthDate(LocalDate.now());
        dukePet.setOwner(owner3);
        owner3.getPets().add(dukePet);
        ownerService.save(owner3);

        System.out.println("SzuPetClinic :: DataLoader :: Loaded Owners :: " + ownerService.findAll());

        Vet vet1 = new Vet();
        vet1.setFirstName("Pamela");
        vet1.setLastName("Isley");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Selina");
        vet2.setLastName("Kyle");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("Stephanie");
        vet3.setLastName("Brown");
        vet3.getSpecialities().add(savedDentistry);
        vetService.save(vet3);

        System.out.println("SzuPetClinic :: DataLoader :: Loaded Vets :: " + vetService.findAll());
    }
}
