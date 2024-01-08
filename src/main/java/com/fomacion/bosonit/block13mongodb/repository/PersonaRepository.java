package com.fomacion.bosonit.block13mongodb.repository;

import com.fomacion.bosonit.block13mongodb.entity.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonaRepository extends MongoRepository<Persona, String> {
}
