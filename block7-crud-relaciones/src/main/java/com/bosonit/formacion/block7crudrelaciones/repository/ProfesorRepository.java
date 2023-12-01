package com.bosonit.formacion.block7crudrelaciones.repository;

import com.bosonit.formacion.block7crudrelaciones.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
    @Query("SELECT COUNT(p) > 0 FROM Profesor p WHERE p.persona.id_persona = :idPersona")
    boolean existsByPersonaId(@Param("idPersona") int idPersona);
}
