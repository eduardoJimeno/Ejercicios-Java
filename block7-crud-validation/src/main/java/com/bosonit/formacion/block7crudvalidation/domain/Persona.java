package com.bosonit.formacion.block7crudvalidation.domain;

import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @GeneratedValue
    int id_persona;
    String usuario;
    String password;
    String name;
    String surname;
    @Column(unique = true)
    String companyEmail;
    String personalEmail;
    String city;
    Boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;

    public Persona(PersonaInputDto personaInputDto){
        this.usuario=personaInputDto.getUsuario();
        this.password=personaInputDto.getPassword();
        this.name=personaInputDto.getName();
        this.surname=personaInputDto.getSurname();
        this.companyEmail=personaInputDto.getCompanyEmail();
        this.personalEmail=personaInputDto.getPersonalEmail();
        this.city=personaInputDto.getCity();
        this.active=personaInputDto.getActive();
        this.created_date=personaInputDto.getCreated_date();
        this.imagen_url=personaInputDto.getImagen_url();
        this.termination_date=personaInputDto.getTermination_date();
    }

    public PersonaOutputDto personaToPersonaOutputDto(){
        return new PersonaOutputDto(
            this.id_persona,
            this.usuario,
            this.name,
            this.surname,
            this.companyEmail,
            this.personalEmail,
            this.city,
            this.active,
            this.created_date,
            this.imagen_url,
            this.termination_date
        );
    }

}
