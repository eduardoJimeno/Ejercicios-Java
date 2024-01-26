package com.formacion.bosonit.examen_JPA_cascada.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteOutputDto {
    private int idCliente;
    private String nombre;
}
