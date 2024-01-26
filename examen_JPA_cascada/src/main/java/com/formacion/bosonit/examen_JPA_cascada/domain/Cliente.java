package com.formacion.bosonit.examen_JPA_cascada.domain;

import com.formacion.bosonit.examen_JPA_cascada.controller.dto.ClienteInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.ClienteOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idCliente;

    @Column(nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Factura> facturas = new ArrayList<>();
    public Cliente(ClienteInputDto clienteInputDto){
        this.nombre=clienteInputDto.getNombre();
    }

    public ClienteOutputDto clienteToClienteOutputDto(){
        return new ClienteOutputDto(
                this.idCliente,
                this.nombre
        );
    }
}
