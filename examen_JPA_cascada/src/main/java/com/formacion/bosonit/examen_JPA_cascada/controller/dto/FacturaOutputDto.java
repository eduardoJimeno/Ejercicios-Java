package com.formacion.bosonit.examen_JPA_cascada.controller.dto;

import com.formacion.bosonit.examen_JPA_cascada.domain.Linea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaOutputDto {
    private int idFactura;
    private int idCliente;
    private Double importeFactura;
    private List<LineaOutputDto> lineasFactura;
}
