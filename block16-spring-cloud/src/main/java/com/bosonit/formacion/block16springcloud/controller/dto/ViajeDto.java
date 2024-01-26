package com.bosonit.formacion.block16springcloud.controller.dto;

import com.bosonit.formacion.block16springcloud.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViajeDto {
    private int idViaje;
    private String origen;
    private String destino;
    private Date fechaSalida;
    private Date fechaLlegada;
    private boolean status;
    private List<Cliente> pasajeros;
}
