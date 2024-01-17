package com.bosonit.formacion.block16springcloud.application;

import com.bosonit.formacion.block16springcloud.controller.dto.ViajeDto;
import com.bosonit.formacion.block16springcloud.domain.Viaje;

import java.sql.SQLException;
import java.util.List;

public interface ViajeServicio {
    ViajeDto addViaje (Viaje viaje) throws SQLException;
    ViajeDto getViajeById(int idViaje) throws SQLException;
    List<ViajeDto> getAllViajes(int pageNumber, int pageSize) throws SQLException;
    ViajeDto updateViaje(ViajeDto viajeDto) throws SQLException;
    void deleteViaje (int idViaje) throws SQLException;
    ViajeDto addClienteToViaje (int idViaje, int idCliente) throws SQLException;
    void updateViajeStatus(int idViaje, Boolean viajeStatus) throws SQLException;
    Boolean getDisponibilidad(int idViaje) throws SQLException;
}
