package com.formacion.bosonit.backendFront.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private int idCliente;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String telefono;
}
