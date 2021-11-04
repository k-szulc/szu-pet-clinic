package xyz.itbs.szupetclinic.services.map;

import org.springframework.stereotype.Service;
import xyz.itbs.szupetclinic.model.Visit;
import xyz.itbs.szupetclinic.services.VisitService;

import java.util.Set;

@Service
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
