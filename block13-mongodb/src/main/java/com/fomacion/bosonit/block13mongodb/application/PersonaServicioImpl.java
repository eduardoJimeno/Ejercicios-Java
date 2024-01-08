package com.fomacion.bosonit.block13mongodb.application;

import com.fomacion.bosonit.block13mongodb.controller.dto.PersonaMapper;
import com.fomacion.bosonit.block13mongodb.controller.dto.PersonaDto;
import com.fomacion.bosonit.block13mongodb.entity.Persona;
import com.fomacion.bosonit.block13mongodb.exceptions.UnprocessableEntityException;
import com.fomacion.bosonit.block13mongodb.repository.PersonaRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServicioImpl implements PersonaServicio {

    PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public PersonaDto addPersona(Persona persona)
    {
        validarCampos(persona);
        mongoTemplate.save(persona);
        return mapper.personaToPersonaDto(persona);
    }

    @Override
    public PersonaDto getPersonaById(String idPersona)
    {
        Persona persona = mongoTemplate.findById(idPersona, Persona.class);
        return mapper.personaToPersonaDto(persona);
    }

    @Override
    public List<PersonaDto> getAllPersonas(int page, int size)
    {
        Query query = new Query().with(PageRequest.of(page, size));
        List<Persona> personas = mongoTemplate.find(query, Persona.class);
        return personas.stream()
                .map(persona -> mapper.personaToPersonaDto(persona))
                .toList();
    }

    @Override
    public PersonaDto updatePersona(String idPersona, Persona persona)
    {
        validarCampos(persona);
        Persona personaExistente = mongoTemplate.findById(idPersona, Persona.class);
        if(persona.getUsuario() != null){
            personaExistente.setUsuario(persona.getUsuario());
        }
        if(persona.getName() != null){
            personaExistente.setName(persona.getName());
        }
        if(persona.getPassword() != null){
            personaExistente.setPassword(persona.getPassword());
        }
        if(persona.getSurname() != null){
            personaExistente.setSurname(persona.getSurname());
        }
        if(persona.getCompanyEmail() != null){
            personaExistente.setCompanyEmail(persona.getCompanyEmail());
        }
        if(persona.getPersonalEmail() != null){
            personaExistente.setPersonalEmail(persona.getPersonalEmail());
        }
        if(persona.getActive() !=null){
            personaExistente.setActive(persona.getActive());
        }
        if(persona.getCreatedDate() != null){
            personaExistente.setCreatedDate(persona.getCreatedDate());
        }
        if(persona.getCity() != null){
            personaExistente.setCity(persona.getCity());
        }
        if(persona.getImagenUrl() !=null){
            personaExistente.setImagenUrl(persona.getImagenUrl());
        }
        if(persona.getTerminationDate() != null){
            personaExistente.setTerminationDate(persona.getTerminationDate());
        }
        mongoTemplate.save(personaExistente);
        return mapper.personaToPersonaDto(personaExistente);
    }

    @Override
    public void deletePersona(String idPersona) {
        mongoTemplate.findById(idPersona, Persona.class);
        mongoTemplate.remove(idPersona);
    }

    private void validarCampos(Persona persona) {
        if (persona.getUsuario().length() < 6 || persona.getUsuario().length() > 10) {
            throw new UnprocessableEntityException("El nombre de usuario debe tener entre 6 y 10 caracteres");
        }
        if (persona.getCompanyEmail() == null) {
            throw new UnprocessableEntityException("Escriba el email de su compañía");
        }
        if (persona.getCity() == null) {
            throw new UnprocessableEntityException("El campo ciudad no puede ser nulo");
        }
        if (persona.getCreatedDate() == null) {
            throw new UnprocessableEntityException("El campo de fecha de creación no puede ser nulo");
        }
    }
}
