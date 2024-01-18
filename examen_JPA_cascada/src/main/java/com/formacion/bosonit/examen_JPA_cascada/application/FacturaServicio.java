package com.formacion.bosonit.examen_JPA_cascada.application;

import com.formacion.bosonit.examen_JPA_cascada.controller.dto.FacturaInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.FacturaOutputDto;

public interface FacturaServicio {
    FacturaOutputDto addFactura(FacturaInputDto facturaInputDto);
}
