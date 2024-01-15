package com.bosonit.formacion.block7crudvalidation.repository;

import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>, PagingAndSortingRepository<Persona, Integer>
    {
    public List<PersonaOutputDto> findByNameLike(String name);
    public Page<PersonaOutputDto> findPersonaByManyFields(HashMap<String, Object> conditions, Pageable pageable);
    public Optional<Persona> findByCompanyEmail(String companyEmail);
            Optional<Persona> findByUsuario(String usuario);
    }

