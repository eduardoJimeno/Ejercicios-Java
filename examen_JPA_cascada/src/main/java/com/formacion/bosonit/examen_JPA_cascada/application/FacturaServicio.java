package com.formacion.bosonit.examen_JPA_cascada.application;

import com.formacion.bosonit.examen_JPA_cascada.controller.dto.FacturaInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.FacturaOutputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.LineaInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.LineaOutputDto;

public interface FacturaServicio {
    FacturaOutputDto addFactura(FacturaInputDto facturaInputDto);
    Iterable<FacturaOutputDto> getAllFacturas(int pageNumber, int pageSize);
    void deleteFacturaById (int id);
    LineaOutputDto addLineaToFactura(int idFactura, LineaInputDto lineaInputDto);
}
