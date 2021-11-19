package xyz.itbs.szupetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = false, exclude = {"owner"})
@Entity
@Table(name="pets")
public class Pet extends BaseEntity{

    @Column(name="name")
    private String petName;

    @ManyToOne
    @JoinColumn(name="type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private Owner owner;

    @Column(name="birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private Set<Visit> visits = new HashSet<>();

    @Builder
    public Pet(Long id, String petName, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.petName = petName;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
        this.visits = visits;
    }
}
