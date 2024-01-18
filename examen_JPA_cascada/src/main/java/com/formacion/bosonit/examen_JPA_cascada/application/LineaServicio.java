package com.formacion.bosonit.examen_JPA_cascada.application;

import com.formacion.bosonit.examen_JPA_cascada.controller.dto.LineaInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.LineaOutputDto;

public interface LineaServicio {
    LineaOutputDto addLinea(LineaInputDto lineaInputDto);
}
