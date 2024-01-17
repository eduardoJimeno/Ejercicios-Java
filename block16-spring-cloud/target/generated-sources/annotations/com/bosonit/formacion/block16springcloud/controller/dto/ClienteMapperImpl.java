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
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDto clienteToClienteDto(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setIdCliente( cliente.getIdCliente() );
        clienteDto.setNombre( cliente.getNombre() );
        clienteDto.setApellido( cliente.getApellido() );
        clienteDto.setEdad( cliente.getEdad() );
        clienteDto.setEmail( cliente.getEmail() );
        clienteDto.setTelefono( cliente.getTelefono() );
        clienteDto.setViaje( viajeToViajeDto( cliente.getViaje() ) );

        return clienteDto;
    }

    @Override
    public Cliente clienteDtoToCliente(ClienteDto clienteDto) {
        if ( clienteDto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setIdCliente( clienteDto.getIdCliente() );
        cliente.setNombre( clienteDto.getNombre() );
        cliente.setApellido( clienteDto.getApellido() );
        cliente.setEdad( clienteDto.getEdad() );
        cliente.setEmail( clienteDto.getEmail() );
        cliente.setTelefono( clienteDto.getTelefono() );
        cliente.setViaje( viajeDtoToViaje( clienteDto.getViaje() ) );

        return cliente;
    }

    protected ViajeDto viajeToViajeDto(Viaje viaje) {
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

    protected Viaje viajeDtoToViaje(ViajeDto viajeDto) {
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
