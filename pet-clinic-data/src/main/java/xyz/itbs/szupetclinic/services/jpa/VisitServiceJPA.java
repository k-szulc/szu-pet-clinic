package xyz.itbs.szupetclinic.services.jpa;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import xyz.itbs.szupetclinic.model.Visit;
import xyz.itbs.szupetclinic.repositories.VisitRepository;
import xyz.itbs.szupetclinic.services.VisitService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
public class VisitServiceJPA implements VisitService {

    private final VisitRepository visitRepository;

    public VisitServiceJPA(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visitSet = new HashSet<>();
        visitRepository.findAll().forEach(visitSet::add);
        return visitSet;
    }

    @Override
    public Visit findById(Long aLong) {
        return visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        visitRepository.deleteById(aLong);
    }
}
