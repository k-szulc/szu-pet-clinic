package xyz.itbs.szupetclinic.services;

import xyz.itbs.szupetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLastNme(String lastName);

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
