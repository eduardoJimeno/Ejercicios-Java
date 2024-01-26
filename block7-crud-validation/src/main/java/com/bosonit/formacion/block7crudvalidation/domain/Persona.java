package com.bosonit.formacion.block7crudvalidation.domain;

import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
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
    String company_email;
    String personal_email;
    String city;
    @Column(nullable = false)
    Boolean admin;
    Boolean active;
    Date createdDate;
    String imagenUrl;
    Date terminationDate;

    public Persona(PersonaInputDto personaInputDto){
        this.id_persona=personaInputDto.getId_persona();
        this.usuario=personaInputDto.getUsuario();
        this.password=personaInputDto.getPassword();
        this.name=personaInputDto.getName();
        this.surname=personaInputDto.getSurname();
        this.company_email=personaInputDto.getCompany_email();
        this.personal_email=personaInputDto.getPersonal_email();
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
            this.company_email,
            this.personal_email,
            this.city,
            this.admin,
            this.active,
            this.createdDate,
            this.imagenUrl,
            this.terminationDate
        );
    }

}
