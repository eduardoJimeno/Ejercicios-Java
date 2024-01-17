package com.bosonit.formacion.block16springcloud.controller;

import com.bosonit.formacion.block16springcloud.application.ViajeServicio;
import com.bosonit.formacion.block16springcloud.controller.dto.ViajeDto;
import com.bosonit.formacion.block16springcloud.domain.Viaje;
import com.bosonit.formacion.block16springcloud.exceptions.LimiteViajerosException;
import com.bosonit.formacion.block16springcloud.exceptions.PasajeroNoEncontradoException;
import com.bosonit.formacion.block16springcloud.exceptions.ViajeNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    ViajeServicio viajeServicio;

    @PostMapping
    public ResponseEntity<ViajeDto> addViaje(@RequestBody Viaje viaje) throws SQLException {
        return ResponseEntity.ok(viajeServicio.addViaje(viaje));
    }

    @GetMapping("/{id_viaje}")
    public ResponseEntity<ViajeDto> getClienteById(@PathVariable int idViaje) throws SQLException {
        return ResponseEntity.ok().body(viajeServicio.getViajeById(idViaje));
    }

    @GetMapping
    public Iterable<ViajeDto> getAllViajes(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) throws SQLException {

        return viajeServicio.getAllViajes(pageNumber, pageSize);
    }


    @DeleteMapping("/{id_viaje}")
    public void deleteClienteById(@PathVariable int idViaje) throws SQLException {
        viajeServicio.deleteViaje(idViaje);
    }

    @PutMapping("/{id_viaje}")
    public ResponseEntity<ViajeDto> updateViaje(
            @PathVariable int id_viaje,
            @RequestBody(required = false) ViajeDto viajeDto) throws SQLException {

        return ResponseEntity.ok(viajeServicio.updateViaje(viajeDto));

    }

    @PostMapping("/addPasajeros/{idViaje}/{idCliente}")
    public ResponseEntity<?> addClientToTrip(@PathVariable int idViaje, @PathVariable int idCliente) {
        try {

            return ResponseEntity.ok().body(viajeServicio.addClienteToViaje(idViaje, idCliente));

        } catch (LimiteViajerosException e) {
            return new ResponseEntity<>("Alcanzado límite de pasajeros", HttpStatus.BAD_REQUEST);
        } catch (ViajeNoEncontradoException e) {
            return new ResponseEntity<>("Viaje no encontrado", HttpStatus.NOT_FOUND);
        } catch (PasajeroNoEncontradoException e) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/status/{idViaje}/{viajeStatus}")
    public ResponseEntity<String> updateTripStatus(
            @PathVariable int idViaje,
            @PathVariable Boolean viajeStatus) throws Exception {
        viajeServicio.updateViajeStatus(idViaje, viajeStatus);
        return ResponseEntity.ok("Status actualizado correctamente.");
    }

    @GetMapping("/verify/{idViaje}")
    public ResponseEntity<String> verifyTripAvailability(@PathVariable int idViaje) {
        try {
            Boolean tripStatus = viajeServicio.getDisponibilidad(idViaje);
            return ResponseEntity.ok("Disponibilidad: " + tripStatus);
        } catch (ViajeNoEncontradoException e) {
            return new ResponseEntity<>("Viaje no encontrado", HttpStatus.NOT_FOUND);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
