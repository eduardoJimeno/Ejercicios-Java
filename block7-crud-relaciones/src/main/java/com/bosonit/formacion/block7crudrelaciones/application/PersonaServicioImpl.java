package com.bosonit.formacion.block7crudrelaciones.application;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaRolOutputDto;
import com.bosonit.formacion.block7crudrelaciones.domain.Persona;
import com.bosonit.formacion.block7crudrelaciones.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block7crudrelaciones.exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block7crudrelaciones.repository.EstudianteRepository;
import com.bosonit.formacion.block7crudrelaciones.repository.PersonaRepository;
import com.bosonit.formacion.block7crudrelaciones.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PersonaServicioImpl implements PersonaServicio{
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    EstudianteRepository estudianteRepository;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto) {
        validarCampos(personaInputDto);
        return personaRepository.save(new Persona(personaInputDto)).personaToPersonaOutputDto();
    }

    @Override
    public PersonaRolOutputDto getPersonaById(int id_persona, boolean rol) {
        Persona persona = personaRepository.findById(id_persona)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con Id: " + id_persona));

        PersonaRolOutputDto personaRolOutputDto = persona.personaToPersonaRolOutputDto();
        //Cambiar y crear relación también en persona
        if (rol) {
            if (estudianteRepository.existsById(persona.getId_persona())) {
                personaRolOutputDto.setRol("Estudiante");
            } else if (profesorRepository.existsById(persona.getId_persona())) {
                personaRolOutputDto.setRol("Profesor");
            } else {
                personaRolOutputDto.setRol("Ninguno");
            }
        }
        return personaRolOutputDto;
    }
    @Override
    public Iterable<PersonaRolOutputDto> getPersonasByName(String nombre, boolean rol) {
        return personaRepository.findByNameLike("%" + nombre + "%").stream()
                .map(persona -> {
                    PersonaRolOutputDto personaRolOutputDto = persona.personaToPersonaRolOutputDto();
                    if (rol) {
                        if (estudianteRepository.existsById(persona.getId_persona())) {
                            personaRolOutputDto.setRol("Estudiante");
                        } else if (profesorRepository.existsById(persona.getId_persona())) {
                            personaRolOutputDto.setRol("Profesor");
                        } else {
                            personaRolOutputDto.setRol("Ninguno");
                        }
                    }
                    return personaRolOutputDto;
                })
                .collect(Collectors.toList());
    }
    @Override
    public Iterable<PersonaRolOutputDto> getAllPersonas(int pageNumber, int pageSize, boolean rol) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(persona -> {
                    PersonaRolOutputDto personaRolOutputDto = persona.personaToPersonaRolOutputDto();
                    if (rol) {
                        if (estudianteRepository.existsById(persona.getId_persona())) {
                            personaRolOutputDto.setRol("Estudiante");
                        } else if (profesorRepository.existsById(persona.getId_persona())) {
                            personaRolOutputDto.setRol("Profesor");
                        } else {
                            personaRolOutputDto.setRol("Ninguno");
                        }
                    }
                    return personaRolOutputDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto personaInputDto) {

        validarCampos(personaInputDto);
        Persona persona = personaRepository.findById(personaInputDto.getId_persona()).orElseThrow();

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
        if(personaInputDto.getCompany_email() != null){
            persona.setCompany_email(personaInputDto.getCompany_email());
        }
        if(personaInputDto.getPersonal_email() != null){
            persona.setPersonal_email(personaInputDto.getPersonal_email());
        }
        if(personaInputDto.getActive() !=null){
            persona.setActive(personaInputDto.getActive());
        }
        if(personaInputDto.getCreated_date() != null){
            persona.setCreated_date(personaInputDto.getCreated_date());
        }
        if(personaInputDto.getCity() != null){
            persona.setCity(personaInputDto.getCity());
        }
        if(personaInputDto.getImagen_url() !=null){
            persona.setImagen_url(personaInputDto.getImagen_url());
        }
        if(personaInputDto.getTermination_date() != null){
            persona.setTermination_date(personaInputDto.getTermination_date());
        }

        return personaRepository.save(persona).personaToPersonaOutputDto();
    }

    @Override
    public void deletePersonaById(int id) {
        personaRepository.findById(id).orElseThrow();
        personaRepository.deleteById(id);

        if (estudianteRepository.existsByPersonaId(id) || profesorRepository.existsByPersonaId(id)){
            throw new IllegalStateException("No se puede eliminar la persona ya que tiene roles de estudiante o profesor asociados.");
        }
    }

    private void validarCampos(PersonaInputDto personaInputDto){
        if (personaInputDto.getUsuario().length() < 6 || personaInputDto.getUsuario().length() > 10) {
            throw new UnprocessableEntityException("El nombre de usuario debe tener entre 6 y 10 caracteres");
        }
        if (personaInputDto.getPassword() == null) {
            throw new UnprocessableEntityException("La contraseña no puede estar vacía");
        }
        if (personaInputDto.getCompany_email() == null) {
            throw new UnprocessableEntityException("Escriba el email de su compañía");
        }
        if (personaInputDto.getCity() == null) {
            throw new UnprocessableEntityException("El campo ciudad no puede ser nulo");
        }
        if (personaInputDto.getCreated_date() == null) {
            throw new UnprocessableEntityException("El campo de fecha de creación no puede ser nulo");
        }
    }
}
