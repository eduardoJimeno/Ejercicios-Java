package com.bosonit.formacion.block7crudrelaciones.application;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaRolOutputDto;

public interface PersonaServicio {
    PersonaOutputDto addPersona(PersonaInputDto persona);
    PersonaRolOutputDto getPersonaById(int id, boolean rol);
    Iterable<PersonaRolOutputDto> getPersonasByName(String name, boolean rol);
    Iterable<PersonaRolOutputDto> getAllPersonas (int pageNumber, int pageSize, boolean rol);
    PersonaOutputDto updatePersona(PersonaInputDto persona);
    void deletePersonaById (int id);
}
