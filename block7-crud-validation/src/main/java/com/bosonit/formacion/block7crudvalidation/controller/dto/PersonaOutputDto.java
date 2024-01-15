package com.bosonit.formacion.block7crudvalidation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaOutputDto implements Serializable {
    int id_persona;
    String usuario;
    String name;
    String surname;
    String companyEmail;
    String personalEmail;
    String city;
    Boolean admin;
    Boolean active;
    Date createdDate;
    String imagenUrl;
    Date terminationDate;
}
