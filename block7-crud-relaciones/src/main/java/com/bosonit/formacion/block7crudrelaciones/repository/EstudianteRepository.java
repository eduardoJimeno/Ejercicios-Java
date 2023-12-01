package com.bosonit.formacion.block7crudrelaciones.repository;

import com.bosonit.formacion.block7crudrelaciones.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    @Query("SELECT COUNT(e) > 0 FROM Estudiante e WHERE e.persona.id_persona = :idPersona")
    boolean existsByPersonaId(@Param("idPersona") int idPersona);
}
