package xyz.itbs.szupetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.itbs.szupetclinic.model.Owner;
import xyz.itbs.szupetclinic.model.Vet;
import xyz.itbs.szupetclinic.services.OwnerService;
import xyz.itbs.szupetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("James");
        owner1.setLastName("Gordon");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Alfred");
        owner2.setLastName("Pennyworth");
        ownerService.save(owner2);

        System.out.println("SzuPetClinic :: DataLoader :: Loaded Owners :: " + ownerService.findAll());

        Vet vet1 = new Vet();
        vet1.setFirstName("Pamela");
        vet1.setLastName("Isley");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Selina");
        vet2.setLastName("Kyle");
        vetService.save(vet2);

        System.out.println("SzuPetClinic :: DataLoader :: Loaded Vets :: " + vetService.findAll());

    }
}
