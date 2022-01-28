package xyz.itbs.szupetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import xyz.itbs.szupetclinic.model.Owner;
import xyz.itbs.szupetclinic.model.Pet;
import xyz.itbs.szupetclinic.model.Visit;
import xyz.itbs.szupetclinic.services.OwnerService;
import xyz.itbs.szupetclinic.services.VisitService;

import javax.validation.Valid;

@Controller
public class VisitController {

    private final OwnerService ownerService;
    private final VisitService visitService;

    public VisitController(OwnerService ownerService, VisitService visitService) {
        this.ownerService = ownerService;
        this.visitService = visitService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("ownerId") Long ownerId, @PathVariable("petId") Long petId,
                                  Model model) {
        Owner owner = ownerService.findById(ownerId);
        Pet pet = owner.getPet(petId);
        model.addAttribute("pet", pet);
        model.addAttribute("owner", owner);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        return visit;
    }


    @GetMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") Long petId, Model model) {
        return "pets/createOrUpdateVisitForm";
    }


    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@ModelAttribute Owner owner, @PathVariable Long petId, @Valid Visit visit,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        }
        else {
            owner.addVisit(petId, visit);
            ownerService.save(owner);
//            visitService.save(visit);
            return "redirect:/owners/" + owner.getId();
        }
    }

}

