package com.bosonit.formacion.block6personcontrollers.service;

import com.bosonit.formacion.block6personcontrollers.entities.Ciudad;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadServicio {
    private final List<Ciudad> ciudades = new ArrayList<>();

    public Ciudad crearCiudad(String nombre, int numeroHabitantes){
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(nombre);
        ciudad.setNumeroHabitantes(numeroHabitantes);

        ciudades.add(ciudad);
        return ciudad;
    }

    public List<Ciudad> getCiudades(){
        return ciudades;
    }
}
