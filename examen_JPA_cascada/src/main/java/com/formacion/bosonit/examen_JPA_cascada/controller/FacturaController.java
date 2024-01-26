package com.formacion.bosonit.examen_JPA_cascada.controller;

import com.formacion.bosonit.examen_JPA_cascada.application.FacturaServicio;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.FacturaOutputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.LineaInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.LineaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    FacturaServicio facturaServicio;

    @GetMapping
    public Iterable<FacturaOutputDto> getAllFacturas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return facturaServicio.getAllFacturas(pageNumber, pageSize);
    }

    @DeleteMapping("/{idFactura}")
    public void deletePersonaById(@PathVariable int idFactura) {
        facturaServicio.deleteFacturaById(idFactura);
    }

    @PostMapping("/linea/{idFactura}")
    public ResponseEntity<LineaOutputDto> addLineaToFactura(
            @PathVariable int idFactura,
            @RequestBody LineaInputDto lineaInputDto) {
        LineaOutputDto nuevaLinea = facturaServicio.addLineaToFactura(idFactura, lineaInputDto);
        return new ResponseEntity<>(nuevaLinea, HttpStatus.CREATED);
    }
}
