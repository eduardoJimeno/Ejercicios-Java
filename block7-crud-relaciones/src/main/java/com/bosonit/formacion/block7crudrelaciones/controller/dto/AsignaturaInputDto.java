package com.bosonit.formacion.block7crudrelaciones.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaInputDto {
    private int id_asignatura;
    private String asignatura;
    private String comments;
    private Date initial_date;
    private Date finish_date;
    private Set<Integer> id_estudiantes;
}
