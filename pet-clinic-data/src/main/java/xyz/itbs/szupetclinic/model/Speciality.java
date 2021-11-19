package xyz.itbs.szupetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@Table(name="specialities")
public class Speciality extends BaseEntity{

    @Column(name="description")
    private String description;

    @Builder
    public Speciality(Long id, String description) {
        super(id);
        this.description = description;
    }
}
