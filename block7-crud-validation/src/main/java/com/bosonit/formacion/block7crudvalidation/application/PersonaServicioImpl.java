package com.bosonit.formacion.block7crudvalidation.application;

import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class PersonaServicioImpl implements PersonaServicio{

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto) {
        validarCampos(personaInputDto);
        return personaRepository.save(new Persona(personaInputDto))
                .personaToPersonaOutputDto();
    }

    @Override
    public PersonaOutputDto getPersonaById(int id_persona) {
        return personaRepository.findById(id_persona)
                .orElseThrow(()-> new EntityNotFoundException("No se encontró la persona con Id: "+id_persona))
                .personaToPersonaOutputDto();
    }

    @Override
    public Iterable<PersonaOutputDto> getPersonasByName(String nombre) {
        return personaRepository.findByNameLike("%" + nombre + "%");
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDto).toList();
    }

    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto personaInputDto) {

        validarCampos(personaInputDto);
        Persona persona = personaRepository.findByCompanyEmail(personaInputDto.getCompanyEmail())
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada"));

        // Actualizar los campos según sea necesario

        if(personaInputDto.getUsuario() != null){
            persona.setUsuario(personaInputDto.getUsuario());
        }
        if(personaInputDto.getName() != null){
            persona.setName(personaInputDto.getName());
        }
        if(personaInputDto.getSurname() != null){
            persona.setSurname(personaInputDto.getSurname());
        }
        if(personaInputDto.getPassword() !=null){
            persona.setPassword(personaInputDto.getPassword());
        }
        if(personaInputDto.getCompanyEmail() != null){
            persona.setCompanyEmail(personaInputDto.getCompanyEmail());
        }
        if(personaInputDto.getPersonalEmail() != null){
            persona.setPersonalEmail(personaInputDto.getPersonalEmail());
        }
        if(personaInputDto.getActive() !=null){
            persona.setActive(personaInputDto.getActive());
        }
        if(personaInputDto.getCreatedDate() != null){
            persona.setCreatedDate(personaInputDto.getCreatedDate());
        }
        if(personaInputDto.getCity() != null){
            persona.setCity(personaInputDto.getCity());
        }
        if(personaInputDto.getImagenUrl() !=null){
            persona.setImagenUrl(personaInputDto.getImagenUrl());
        }
        if(personaInputDto.getTerminationDate() != null){
            persona.setTerminationDate(personaInputDto.getTerminationDate());
        }

        return personaRepository.save(persona).personaToPersonaOutputDto();
    }

    @Override
    public void deletePersonaById(int id_persona) {
        personaRepository.findById(id_persona)
                .orElseThrow(()-> new EntityNotFoundException("No se encontró la persona con Id: "+id_persona));
        personaRepository.deleteById(id_persona);
    }

    private void validarCampos(PersonaInputDto personaInputDto){
        if (personaInputDto.getUsuario().length() < 6 || personaInputDto.getUsuario().length() > 10) {
            throw new UnprocessableEntityException("El nombre de usuario debe tener entre 6 y 10 caracteres");
        }
        if (personaInputDto.getPassword() == null) {
            throw new UnprocessableEntityException("La contraseña no puede estar vacía");
        }
        if (personaInputDto.getCompanyEmail() == null) {
            throw new UnprocessableEntityException("Escriba el email de su compañía");
        }
        if (personaInputDto.getCity() == null) {
            throw new UnprocessableEntityException("El campo ciudad no puede ser nulo");
        }
        if (personaInputDto.getCreatedDate() == null) {
            throw new UnprocessableEntityException("El campo de fecha de creación no puede ser nulo");
        }
    }
}
