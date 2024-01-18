package com.formacion.bosonit.examen_JPA_cascada.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineaOutputDto {
    private int idLinea;
    private int idFactura;
    private String nombreProducto;
    private Double cantidad;
    private Double precio;

}
