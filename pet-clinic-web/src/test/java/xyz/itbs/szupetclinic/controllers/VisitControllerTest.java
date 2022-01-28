package xyz.itbs.szupetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.itbs.szupetclinic.model.Owner;
import xyz.itbs.szupetclinic.model.Pet;
import xyz.itbs.szupetclinic.model.Visit;
import xyz.itbs.szupetclinic.services.OwnerService;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    public static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";

    @Mock
    OwnerService ownerService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;
    Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).firstName("Foo").lastName("Bar").pets(
                Collections.singleton(Pet.builder().id(1L).visits(new HashSet<>()).build())).build();
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();

    }


    @Test
    void initNewVisitForm() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(owner);
        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
    }

    @Test
    void processNewVisitForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        mockMvc.perform(post("/owners/1/pets/1/visits/new")
                        .param("description","Foo"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/owners/1"))
                .andExpect(view().name("redirect:/owners/1"));

        verify(ownerService).save(any());
    }

    @Test
    void processNewVisitFormNotValid() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        mockMvc.perform(post("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
        verify(ownerService).findById(anyLong());
        verifyNoMoreInteractions(ownerService);
    }
}