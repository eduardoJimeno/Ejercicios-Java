package com.bosonit.formacion.block16springcloud.application;

import com.bosonit.formacion.block16springcloud.controller.dto.ClienteDto;
import com.bosonit.formacion.block16springcloud.domain.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteServicio {
    ClienteDto addCliente (Cliente cliente) throws SQLException;
    ClienteDto getClienteById(int idCliente) throws SQLException;
    List<ClienteDto> getAllClientes(int pageNumber, int pageSize) throws SQLException;
    ClienteDto updateCliente(ClienteDto clienteDto) throws SQLException;
    void deleteCliente (int idCliente) throws SQLException;
    int getClientesByViaje(int idViaje) throws SQLException;
}
