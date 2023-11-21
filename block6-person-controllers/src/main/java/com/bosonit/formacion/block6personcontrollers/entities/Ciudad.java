package com.bosonit.formacion.block6personcontrollers.entities;

public class Ciudad {
    private String nombre;
    private int numeroHabitantes;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroHabitantes() {
        return numeroHabitantes;
    }

    public void setNumeroHabitantes(int numeroHabitantes) {
        this.numeroHabitantes = numeroHabitantes;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "nombre='" + nombre + '\'' +
                ", numeroHabitantes=" + numeroHabitantes +
                '}';
    }
}
