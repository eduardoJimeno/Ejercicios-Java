package com.bosonit.formacion.block7crud.domain;

import com.bosonit.formacion.block7crud.contoller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crud.contoller.dto.PersonaOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @GeneratedValue
    int id;

    String nombre;
    int edad;
    String poblacion;

    public Persona (PersonaInputDto personaInputDto){
        this.id = personaInputDto.getId();
        this.nombre = personaInputDto.getNombre();
        this.poblacion = personaInputDto.getPoblacion();
        this.edad = personaInputDto.getEdad();
    }

    public PersonaOutputDto personaToPersonaOutputDto(){
        return new PersonaOutputDto(
                this.id,
                this.nombre,
                this.poblacion,
                this.edad
        );
    }
}
