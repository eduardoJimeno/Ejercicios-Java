package com.formacion.bosonit.examen_JPA_cascada.application;

import com.formacion.bosonit.examen_JPA_cascada.controller.dto.ClienteInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.ClienteOutputDto;

public interface ClienteServicio {
    ClienteOutputDto addCliente(ClienteInputDto clienteInputDto);
}
