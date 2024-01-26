package com.bosonit.formacion.block7crudvalidation.repository;

import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

<<<<<<< HEAD
public interface PersonaRepository extends
        JpaRepository<Persona, Integer>,
    PagingAndSortingRepository<Persona, Integer>
    {
    List<Persona> findByNameLike(String name);
=======
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>, PagingAndSortingRepository<Persona, Integer>
    {
    public List<PersonaOutputDto> findByNameLike(String name);
    public Page<PersonaOutputDto> findPersonaByManyFields(HashMap<String, Object> conditions, Pageable pageable);
    public Optional<Persona> findByCompanyEmail(String companyEmail);
            Optional<Persona> findByUsuario(String usuario);
>>>>>>> bfb929555f864b1d9cda33f4baa9b7c4b4f1dc7d
    }

