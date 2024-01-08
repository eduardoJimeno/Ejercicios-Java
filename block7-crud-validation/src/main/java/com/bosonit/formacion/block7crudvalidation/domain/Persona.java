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
    int id_persona;
    String usuario;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;

    public Persona(PersonaInputDto personaInputDto){
        this.id_persona=personaInputDto.getId_persona();
        this.usuario=personaInputDto.getUsuario();
        this.password=personaInputDto.getPassword();
        this.name=personaInputDto.getName();
        this.surname=personaInputDto.getSurname();
        this.company_email=personaInputDto.getCompany_email();
        this.personal_email=personaInputDto.getPersonal_email();
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
            this.company_email,
            this.personal_email,
            this.city,
            this.active,
            this.created_date,
            this.imagen_url,
            this.termination_date
        );
    }

}
