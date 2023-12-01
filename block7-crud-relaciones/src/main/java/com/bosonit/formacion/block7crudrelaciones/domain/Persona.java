package com.bosonit.formacion.block7crudrelaciones.domain;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaRolOutputDto;
import com.bosonit.formacion.block7crudrelaciones.repository.EstudianteRepository;
import com.bosonit.formacion.block7crudrelaciones.repository.ProfesorRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @GeneratedValue
    private int id_persona;
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
    public PersonaRolOutputDto personaToPersonaRolOutputDto() {
            return new PersonaRolOutputDto(
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
                    this.termination_date,
                    null
            );
    }

}
