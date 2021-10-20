package xyz.itbs.szupetclinic.services.map;

import xyz.itbs.szupetclinic.model.Vet;
import xyz.itbs.szupetclinic.services.VetService;

import java.util.Set;

public class VetServiceMap extends AbstractServiceMap<Vet, Long> implements VetService {

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
