package xyz.itbs.szupetclinic.services.jpa;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import xyz.itbs.szupetclinic.model.Speciality;
import xyz.itbs.szupetclinic.repositories.SpecialityRepository;
import xyz.itbs.szupetclinic.services.SpecialityService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
public class SpecialityServiceJPA implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialityServiceJPA(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialitySet = new HashSet<>();
        specialityRepository.findAll().forEach(specialitySet::add);
        return specialitySet;
    }

    @Override
    public Speciality findById(Long aLong) {
        return specialityRepository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        specialityRepository.deleteById(aLong);
    }
}
