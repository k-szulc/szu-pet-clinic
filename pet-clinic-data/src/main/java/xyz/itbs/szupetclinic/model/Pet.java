package xyz.itbs.szupetclinic.model;

import javax.persistence.*;
import java.time.LocalDate;


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

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petName='" + petName + '\'' +
                ", petType=" + petType +
                ", owner=" + owner.getFirstName() + " " + owner.getLastName() +
                ", birthDate=" + birthDate +
                "} " + super.toString();
    }
}
