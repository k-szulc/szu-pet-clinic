package xyz.itbs.szupetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import xyz.itbs.szupetclinic.model.Speciality;
import xyz.itbs.szupetclinic.services.SpecialityService;

import java.util.Set;

@Service
@Profile("map")
public class SpecialityServiceMap extends AbstractServiceMap<Speciality, Long> implements SpecialityService {
    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
