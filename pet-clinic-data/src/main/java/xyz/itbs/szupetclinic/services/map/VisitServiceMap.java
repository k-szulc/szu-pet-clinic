package xyz.itbs.szupetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import xyz.itbs.szupetclinic.model.Visit;
import xyz.itbs.szupetclinic.services.VisitService;

import java.util.Set;

@Service
@Profile("map")
public class VisitServiceMap extends AbstractServiceMap<Visit, Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit object) {

        if(object.getPet() == null || object.getPet().getId() == null || object.getPet().getOwner() == null
                || object.getPet().getOwner().getId() == null){
            throw new RuntimeException("Invalid Visit - Owner or Pet cannot be null !");
        }

        return super.save(object);

    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
