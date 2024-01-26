package com.formacion.bosonit.backendFront.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeDto {
    private int idViaje;
    private String origen;
    private String destino;
    private Date fechaSalida;
    private Date fechaLlegada;
    private String status;
}
