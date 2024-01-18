package com.formacion.bosonit.examen_JPA_cascada.application;

import com.formacion.bosonit.examen_JPA_cascada.controller.dto.ClienteInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.FacturaInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.FacturaOutputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.LineaInputDto;
import com.formacion.bosonit.examen_JPA_cascada.domain.Cliente;
import com.formacion.bosonit.examen_JPA_cascada.domain.Factura;
import com.formacion.bosonit.examen_JPA_cascada.domain.Linea;
import com.formacion.bosonit.examen_JPA_cascada.repository.ClienteRepository;
import com.formacion.bosonit.examen_JPA_cascada.repository.FacturaRepsitory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaServicioImpl implements FacturaServicio{

    @Autowired
    private FacturaRepsitory facturaRepsitory;
    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public FacturaOutputDto addFactura(FacturaInputDto facturaInputDto) {
        return null;
    }

    @Transactional
    public void addFacturaWithLinesOnStartup() {
        ClienteInputDto clienteInputDto = new ClienteInputDto("Eduardo");
        Cliente cliente = clienteRepository.save(new Cliente(clienteInputDto));

        FacturaInputDto facturaInputDto = new FacturaInputDto(cliente.getIdCliente(), 100.0);
        Factura factura = new Factura(facturaInputDto);

        LineaInputDto linea1InputDto = new LineaInputDto(factura.getIdFactura(), "Producto1", 2.0, 50.0);
        Linea linea1 = new Linea(linea1InputDto);

        LineaInputDto linea2InputDto = new LineaInputDto(factura.getIdFactura(), "Producto2", 3.0, 30.0);
        Linea linea2 = new Linea(linea2InputDto);

        factura.addLinea(linea1);
        factura.addLinea(linea2);

        factura.addCliente(cliente);

        facturaRepsitory.save(factura);
    }
}
