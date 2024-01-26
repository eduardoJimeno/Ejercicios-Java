package com.bosonit.formacion.block16springcloud.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "viaje")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idViaje;
    private String origen;
    private String destino;
    private Date fechaSalida;
    private Date fechaLlegada;
    private boolean status;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL)
    private List<Cliente> pasajeros = new ArrayList<>();

    public Viaje(String origen, String destino, Date fechaSalida, Date fechaLlegada, Boolean status){
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Viaje{" +
                "idViaje=" + idViaje +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", fechaSalida=" + fechaSalida +
                ", fechaLlegada=" + fechaLlegada +
                ", status=" + status +
                ", pasajeros=" + pasajeros +
                '}';
    }
}
