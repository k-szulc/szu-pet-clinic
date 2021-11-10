package xyz.itbs.szupetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(callSuper = true)
@Table(name="specialities")
public class Speciality extends BaseEntity{

    @Column(name="description")
    private String description;

}
