package com.formacion.bosonit.examen_JPA_cascada.repository;

import com.formacion.bosonit.examen_JPA_cascada.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepsitory extends JpaRepository<Factura, Integer> {
}
