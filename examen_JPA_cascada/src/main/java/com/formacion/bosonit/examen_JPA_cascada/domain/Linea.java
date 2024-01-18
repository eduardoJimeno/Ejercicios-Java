package com.formacion.bosonit.examen_JPA_cascada.domain;

import com.formacion.bosonit.examen_JPA_cascada.controller.dto.LineaOutputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dto.LineaInputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Linea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idLinea;

    @ManyToOne
    @JoinColumn(name = "id_factura")
    private Factura factura;

    @Column(nullable = false)
    private String nombreProducto;
    private Double cantidad;
    private Double precio;

    public Linea(LineaInputDto lineaInputDto){
        this.nombreProducto = lineaInputDto.getNombreProducto();
        this.cantidad = lineaInputDto.getCantidad();
        this.precio = lineaInputDto.getPrecio();
    }

    public LineaOutputDto lineaToLineaOutputDto(){
        return new LineaOutputDto(
                this.idLinea,
                this.factura.getIdFactura(),
                this.nombreProducto,
                this.cantidad,
                this.precio
        );
    }
}
