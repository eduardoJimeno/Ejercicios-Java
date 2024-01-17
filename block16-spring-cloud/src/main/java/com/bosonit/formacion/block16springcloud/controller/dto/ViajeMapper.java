package com.bosonit.formacion.block16springcloud.controller.dto;

import com.bosonit.formacion.block16springcloud.domain.Viaje;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ViajeMapper {
    ViajeDto viajeToViajeDto(Viaje viaje);
    Viaje viajeDtoToViaje(ViajeDto viajeDto);
}
