package com.formacion.bosonit.examen_JPA_cascada.application;

import com.formacion.bosonit.examen_JPA_cascada.controller.dto.ClienteInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.ClienteOutputDto;
import com.formacion.bosonit.examen_JPA_cascada.domain.Cliente;
import com.formacion.bosonit.examen_JPA_cascada.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicioImpl implements ClienteServicio {
    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public ClienteOutputDto addCliente(ClienteInputDto clienteInputDto) {
        return clienteRepository.save(new Cliente(clienteInputDto)).clienteToClienteOutputDto();
    }

}
