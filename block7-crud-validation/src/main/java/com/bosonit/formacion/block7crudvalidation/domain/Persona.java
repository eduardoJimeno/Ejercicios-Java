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
    int idPersona;
    String usuario;
    String password;
    String name;
    String surname;
    @Column(unique = true)
    String companyEmail;
    String personalEmail;
    String city;
    @Column(nullable = false)
    Boolean admin;
    Boolean active;
    Date createdDate;
    String imagenUrl;
    Date terminationDate;

    public Persona(PersonaInputDto personaInputDto){
        this.usuario=personaInputDto.getUsuario();
        this.password=personaInputDto.getPassword();
        this.name=personaInputDto.getName();
        this.surname=personaInputDto.getSurname();
        this.companyEmail=personaInputDto.getCompanyEmail();
        this.personalEmail=personaInputDto.getPersonalEmail();
        this.city=personaInputDto.getCity();
        this.admin=personaInputDto.getAdmin();
        this.active=personaInputDto.getActive();
        this.createdDate=personaInputDto.getCreatedDate();
        this.imagenUrl=personaInputDto.getImagenUrl();
        this.terminationDate=personaInputDto.getTerminationDate();
    }

    public PersonaOutputDto personaToPersonaOutputDto(){
        return new PersonaOutputDto(
            this.idPersona,
            this.usuario,
            this.name,
            this.surname,
            this.companyEmail,
            this.personalEmail,
            this.city,
            this.admin,
            this.active,
            this.createdDate,
            this.imagenUrl,
            this.terminationDate
        );
    }

}
