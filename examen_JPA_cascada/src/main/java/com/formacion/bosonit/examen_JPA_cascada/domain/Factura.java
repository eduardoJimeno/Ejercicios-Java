package com.formacion.bosonit.examen_JPA_cascada.domain;

import com.formacion.bosonit.examen_JPA_cascada.controller.dto.FacturaInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.FacturaOutputDto;
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
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFactura;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    private Double importeFactura;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Linea> lineasFactura = new ArrayList<>();

    public Factura(FacturaInputDto facturaInputDto) {
        this.importeFactura = facturaInputDto.getImporteFactura();
    }

    public FacturaOutputDto facturaToFacturaOutputDto(){
        return new FacturaOutputDto(
                this.idFactura,
                this.cliente.getIdCliente(),
                this.importeFactura,
                this.lineasFactura
                );
    }

    public void addLinea(Linea linea) {
        linea.setFactura(this);
        this.lineasFactura.add(linea);
    }
    public void addCliente(Cliente cliente) {
        this.cliente = cliente;
        cliente.getFacturas().add(this);
    }


}
