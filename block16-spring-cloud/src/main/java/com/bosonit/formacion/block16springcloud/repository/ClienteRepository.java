package com.bosonit.formacion.block16springcloud.repository;

import com.bosonit.formacion.block16springcloud.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Integer> {
}
