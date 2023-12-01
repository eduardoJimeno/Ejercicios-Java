package com.bosonit.formacion.block7crudrelaciones.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorInputDto {
    private int id_profesor;
    private String comments;
    private String branch;
    private int id_persona;
}
