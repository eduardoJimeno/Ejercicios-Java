package com.bosonit.formacion.block7crudrelaciones.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaRolOutputDto  extends PersonaOutputDto{
    String rol;

    public PersonaRolOutputDto(int idPersona, String usuario, String name, String surname, String companyEmail, String personalEmail, String city, Boolean active, Date createdDate, String imagenUrl, Date terminationDate, String rol) {
        super(idPersona, usuario, name, surname, companyEmail, personalEmail, city, active, createdDate, imagenUrl, terminationDate);
        this.rol = rol;
    }
}
