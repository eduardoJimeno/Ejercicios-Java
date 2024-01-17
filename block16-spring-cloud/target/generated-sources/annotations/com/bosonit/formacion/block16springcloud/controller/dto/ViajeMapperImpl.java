package com.bosonit.formacion.block16springcloud.controller.dto;

import com.bosonit.formacion.block16springcloud.domain.Cliente;
import com.bosonit.formacion.block16springcloud.domain.Viaje;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-17T11:19:49+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ViajeMapperImpl implements ViajeMapper {

    @Override
    public ViajeDto viajeToViajeDto(Viaje viaje) {
        if ( viaje == null ) {
            return null;
        }

        ViajeDto viajeDto = new ViajeDto();

        viajeDto.setIdViaje( viaje.getIdViaje() );
        viajeDto.setOrigen( viaje.getOrigen() );
        viajeDto.setDestino( viaje.getDestino() );
        viajeDto.setFechaSalida( viaje.getFechaSalida() );
        viajeDto.setFechaLlegada( viaje.getFechaLlegada() );
        viajeDto.setStatus( viaje.isStatus() );
        List<Cliente> list = viaje.getPasajeros();
        if ( list != null ) {
            viajeDto.setPasajeros( new ArrayList<Cliente>( list ) );
        }

        return viajeDto;
    }

    @Override
    public Viaje viajeDtoToViaje(ViajeDto viajeDto) {
        if ( viajeDto == null ) {
            return null;
        }

        Viaje viaje = new Viaje();

        viaje.setIdViaje( viajeDto.getIdViaje() );
        viaje.setOrigen( viajeDto.getOrigen() );
        viaje.setDestino( viajeDto.getDestino() );
        if ( viajeDto.getFechaSalida() != null ) {
            viaje.setFechaSalida( new Date( viajeDto.getFechaSalida().getTime() ) );
        }
        if ( viajeDto.getFechaLlegada() != null ) {
            viaje.setFechaLlegada( new Date( viajeDto.getFechaLlegada().getTime() ) );
        }
        viaje.setStatus( viajeDto.isStatus() );
        List<Cliente> list = viajeDto.getPasajeros();
        if ( list != null ) {
            viaje.setPasajeros( new ArrayList<Cliente>( list ) );
        }

        return viaje;
    }
}
