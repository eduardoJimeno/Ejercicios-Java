package com.bosonit.formacion.block16springcloud.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
    private int idCliente;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private int telefono;
    private ViajeDto viaje;

}
