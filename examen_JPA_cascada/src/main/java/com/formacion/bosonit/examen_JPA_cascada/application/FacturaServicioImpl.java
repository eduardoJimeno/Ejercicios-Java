package com.formacion.bosonit.examen_JPA_cascada.application;

import com.formacion.bosonit.examen_JPA_cascada.controller.dto.*;
import com.formacion.bosonit.examen_JPA_cascada.domain.Cliente;
import com.formacion.bosonit.examen_JPA_cascada.domain.Factura;
import com.formacion.bosonit.examen_JPA_cascada.domain.Linea;
import com.formacion.bosonit.examen_JPA_cascada.exceptions.FacturaNotFoundException;
import com.formacion.bosonit.examen_JPA_cascada.repository.ClienteRepository;
import com.formacion.bosonit.examen_JPA_cascada.repository.FacturaRepsitory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Iterable<FacturaOutputDto> getAllFacturas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return facturaRepsitory.findAll(pageRequest).getContent()
                .stream()
                .map(factura -> {
                    FacturaOutputDto facturaOutputDto = factura.facturaToFacturaOutputDto();
                    facturaOutputDto.setLineasFactura(
                            factura.getLineasFactura()
                                    .stream()
                                    .map(Linea::lineaToLineaOutputDto)
                                    .collect(Collectors.toList())
                    );
                    return facturaOutputDto;
                })
                .toList();
    }

    @Override
    public void deleteFacturaById(int id) {
        Optional<Factura> optionalFactura = facturaRepsitory.findById(id);

        if (optionalFactura.isPresent()) {
            Factura factura = optionalFactura.get();
            facturaRepsitory.delete(factura);
        } else {
            throw new FacturaNotFoundException("Factura no encontrada con ID: " + id);
        }
    }

    @Override
    @Transactional
    public LineaOutputDto addLineaToFactura(int idFactura, LineaInputDto lineaInputDto) {
        Optional<Factura> optionalFactura = facturaRepsitory.findById(idFactura);

        if (optionalFactura.isPresent()) {
            Factura factura = optionalFactura.get();
            Linea nuevaLinea = new Linea(lineaInputDto);
            nuevaLinea.setFactura(factura);
            factura.addLinea(nuevaLinea);

            facturaRepsitory.save(factura);

            return nuevaLinea.lineaToLineaOutputDto();
        } else {
            throw new FacturaNotFoundException("Factura no encontrada con ID: " + idFactura);
        }
    }

    @Transactional
    public void addFacturaWithLinesOnStartup() {
        ClienteInputDto clienteInputDto = new ClienteInputDto("Eduardo");
        Cliente cliente = clienteRepository.save(new Cliente(clienteInputDto));

        FacturaInputDto facturaInputDto = new FacturaInputDto(cliente.getIdCliente(), 190.0);
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
