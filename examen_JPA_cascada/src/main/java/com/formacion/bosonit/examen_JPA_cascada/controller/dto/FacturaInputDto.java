package com.formacion.bosonit.examen_JPA_cascada.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaInputDto {
    private int idCliente;
    private Double importeFactura;

}