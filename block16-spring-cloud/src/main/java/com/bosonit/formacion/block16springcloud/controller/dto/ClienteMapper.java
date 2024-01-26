package com.bosonit.formacion.block16springcloud.controller.dto;

import com.bosonit.formacion.block16springcloud.domain.Cliente;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDto clienteToClienteDto(Cliente cliente);
    Cliente clienteDtoToCliente(ClienteDto clienteDto);
}
