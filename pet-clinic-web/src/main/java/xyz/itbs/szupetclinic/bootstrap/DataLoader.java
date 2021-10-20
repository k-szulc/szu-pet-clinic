package xyz.itbs.szupetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.itbs.szupetclinic.model.Owner;
import xyz.itbs.szupetclinic.model.Vet;
import xyz.itbs.szupetclinic.services.OwnerService;
import xyz.itbs.szupetclinic.services.VetService;
import xyz.itbs.szupetclinic.services.map.OwnerServiceMap;
import xyz.itbs.szupetclinic.services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;


    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();

    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("James");
        owner1.setLastName("Gordon");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Alfred");
        owner2.setFirstName("Pennyworth");
        ownerService.save(owner2);

        System.out.println("SzuPetClinic :: DataLoader :: Loaded Owners :: " + ownerService.findAll());
        System.out.println();
        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Pamela");
        vet1.setLastName("Isley");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Selina");
        vet2.setLastName("Kyle");
        vetService.save(vet2);

        System.out.println("SzuPetClinic :: DataLoader :: Loaded Vets :: " + vetService.findAll());

    }
}
