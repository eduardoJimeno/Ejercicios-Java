package com.bosonit.formacion.block7crudrelaciones.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteInputDto {
    private int id_estudiante;
    private int num_hours_week;
    private String comments;
    private String branch;
    private int id_persona;
    private int id_profesor;
}
