package com.bosonit.formacion.block7crudrelaciones.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteFullOutputDto extends EstudianteSimpleOutputDto{

   PersonaOutputDto personaOutputDto;

   public EstudianteFullOutputDto(int idEstudiante, int numHoursWeek, String comments, String branch, int idPersona, String name, String surname, String companyEmail, String personalEmail, String city, Boolean active, Date createdDate, String imagenUrl, Date terminationDate) {
   }
}
