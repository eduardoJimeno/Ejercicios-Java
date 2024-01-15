package com.bosonit.formacion.block7crudvalidation.application;

import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;

public interface PersonaServicio {
    PersonaOutputDto addPersona(PersonaInputDto personaInputDto);
    PersonaOutputDto getPersonaById(int id);
    Iterable<PersonaOutputDto> getPersonasByName(String name);
    Iterable<PersonaOutputDto> getAllPersonas (int pageNumber, int pageSize);
    PersonaOutputDto updatePersona(PersonaInputDto personaInputDto);
    void deletePersonaById (int id);


}
