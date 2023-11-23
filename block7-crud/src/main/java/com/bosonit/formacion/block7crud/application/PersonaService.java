package com.bosonit.formacion.block7crud.application;

import com.bosonit.formacion.block7crud.contoller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crud.contoller.dto.PersonaOutputDto;

public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto persona);
    PersonaOutputDto getPersonaById(int id);
    Iterable<PersonaOutputDto> getAllPersonasByNombreLike(String nombre);
    void deletePersonaById (int id);
    Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDto updatePersona(PersonaInputDto persona);
}
