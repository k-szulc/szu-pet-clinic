package xyz.itbs.szupetclinic.services;

import xyz.itbs.szupetclinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);

}
