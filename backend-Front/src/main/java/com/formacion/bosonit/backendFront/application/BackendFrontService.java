package com.formacion.bosonit.backendFront.application;

import com.formacion.bosonit.backendFront.domain.Ticket;

public interface BackendFrontService {
    Ticket generateTicket(int idCliente, int idViaje);
}
