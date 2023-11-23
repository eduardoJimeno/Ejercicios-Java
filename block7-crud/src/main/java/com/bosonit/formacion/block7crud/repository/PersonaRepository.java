package com.bosonit.formacion.block7crud.repository;

import com.bosonit.formacion.block7crud.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PersonaRepository extends
        JpaRepository <Persona, Integer>,
        PagingAndSortingRepository<Persona, Integer> {
    List<Persona> findByNombreLike (String nombre);
}
