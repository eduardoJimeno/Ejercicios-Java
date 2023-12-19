package com.bosonit.formacion.block7crudvalidation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaOutputDto {
    int id_persona;
    String usuario;
    String name;
    String surname;
    String companyEmail;
    String personalEmail;
    String city;
    Boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;
}
