package xyz.itbs.szupetclinic.services.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import xyz.itbs.szupetclinic.model.Owner;
import xyz.itbs.szupetclinic.repositories.OwnerRepository;
import xyz.itbs.szupetclinic.services.OwnerService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("repo")
public class OwnerServiceRepository implements OwnerService{

    private final OwnerRepository ownerRepository;

    public OwnerServiceRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerRepository.findAll().forEach(ownerSet::add);
        return ownerSet;
    }

    @Override
    public Owner findById(Long aLong) {
        return ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }

    @Override
    public Owner findByLastNme(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }
}
