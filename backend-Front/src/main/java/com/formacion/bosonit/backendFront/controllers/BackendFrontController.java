package com.formacion.bosonit.backendFront.controllers;

import com.formacion.bosonit.backendFront.application.BackendFrontService;
import com.formacion.bosonit.backendFront.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generateTicket")
public class BackendFrontController {

    @Autowired
    private BackendFrontService backendFrontService;

    @GetMapping("/{idCliente}/{idViaje}")
    public ResponseEntity<Ticket> generateTicket(
            @PathVariable int idCliente,
            @PathVariable int idViaje)
    {
        Ticket ticket = backendFrontService.generateTicket(idCliente, idViaje);
        return ResponseEntity.ok(ticket);
    }

}
