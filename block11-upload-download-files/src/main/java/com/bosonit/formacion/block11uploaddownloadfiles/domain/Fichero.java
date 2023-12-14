package com.bosonit.formacion.block11uploaddownloadfiles.domain;

import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroInputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fichero {

    @Id
    @GeneratedValue
    private int id;
    private String nombreFichero;
    private Date fechaSubida;
    private String categoria;

    public Fichero(FicheroInputDto ficheroInputDto){
        this.nombreFichero=ficheroInputDto.getNombreFichero();
        this.fechaSubida=ficheroInputDto.getFechaSubida();
        this.categoria=ficheroInputDto.getCategoria();
    }

    public FicheroOutputDto ficheroToFicheroOutputDto(){
        return new FicheroOutputDto(
                this.id,
                this.nombreFichero,
                this.fechaSubida,
                this.categoria
        );
    }
}
