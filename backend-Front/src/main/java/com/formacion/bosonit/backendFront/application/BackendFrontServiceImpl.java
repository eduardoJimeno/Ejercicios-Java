package com.formacion.bosonit.backendFront.application;

import com.formacion.bosonit.backendFront.controllers.dto.ClienteDto;
import com.formacion.bosonit.backendFront.controllers.dto.ViajeDto;
import com.formacion.bosonit.backendFront.domain.Ticket;
import com.formacion.bosonit.backendFront.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BackendFrontServiceImpl implements BackendFrontService {

    @Autowired
    RestTemplate restTemplate;
    @Override
    public Ticket generateTicket(int idCliente, int idViaje) {
        ClienteDto cliente = restTemplate.getForObject("http://localhost:8082/cliente/{idCliente}", ClienteDto.class);
        ViajeDto viaje = restTemplate.getForObject("http://localhost:8082/viaje/{idViaje}", ViajeDto.class);

        Ticket ticket = new Ticket();
        assert cliente != null;
        ticket.setPassengerId(cliente.getIdCliente());
        ticket.setPassengerName(cliente.getNombre());
        ticket.setPassengerLastname(cliente.getApellido());
        ticket.setPassengerEmail(cliente.getEmail());
        assert viaje != null;
        ticket.setTripOrigin(viaje.getOrigen());
        ticket.setTripDestination(viaje.getDestino());
        ticket.setDepartureDate(viaje.getFechaSalida());
        ticket.setArrivalDate(viaje.getFechaLlegada());

        //ticketRepository.save(ticket);

        return ticket;
    }
}



