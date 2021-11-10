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
@ToString(callSuper = true)
@Entity
@Table(name="types")
public class PetType extends BaseEntity {

    @Column(name="name")
    private String name;

}
