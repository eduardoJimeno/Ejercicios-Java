package com.bosonit.formacion.block16springcloud.controller;

import com.bosonit.formacion.block16springcloud.application.ClienteServicio;
import com.bosonit.formacion.block16springcloud.controller.dto.ClienteDto;
import com.bosonit.formacion.block16springcloud.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteServicio clienteServicio;

    @PostMapping
    public ResponseEntity<ClienteDto> addPersona(@RequestBody Cliente cliente) throws SQLException {
        return ResponseEntity.ok(clienteServicio.addCliente(cliente));
    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable int id_cliente) throws SQLException {
        return ResponseEntity.ok().body(clienteServicio.getClienteById(id_cliente));
    }

    @GetMapping
    public Iterable<ClienteDto> getAllClientes(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) throws SQLException {

        return clienteServicio.getAllClientes(pageNumber, pageSize);
    }


    @DeleteMapping("/{id_cliente}")
    public void deleteClienteById(@PathVariable int id_cliente) throws SQLException {
        clienteServicio.deleteCliente(id_cliente);
    }

    @PutMapping("/{id_cliente}")
    public ResponseEntity<ClienteDto> updateCliente(
            @PathVariable int id_cliente,
            @RequestBody(required = false) ClienteDto clienteDto) throws SQLException {

        return ResponseEntity.ok(clienteServicio.updateCliente(clienteDto));

    }

}
