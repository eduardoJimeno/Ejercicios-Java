package com.bosonit.formacion.block7crud.contoller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaInputDto {
    int id;
    String nombre;
    String poblacion;
    int edad;
}
