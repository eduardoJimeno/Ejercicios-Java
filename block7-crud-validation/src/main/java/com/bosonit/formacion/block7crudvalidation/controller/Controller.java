package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.application.PersonaServicioImpl;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.exceptions.LanzarException;
import com.bosonit.formacion.block7crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class Controller {

    @Autowired
    PersonaServicioImpl personaServicio;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona){
       validarCampos(persona);
        return ResponseEntity.status(200).body(personaServicio.addPersona(persona));
    }

    @GetMapping("/{id_persona}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable int id_persona)
    {
        return ResponseEntity.ok().body(personaServicio.getPersonaById(id_persona));
    }
    @GetMapping("/nombre/{name}")
    public Iterable<PersonaOutputDto> getAllPersonasByNameLike(@PathVariable String nombre){
        Iterable<PersonaOutputDto> personas = personaServicio.getPersonasByName(nombre);

        if (!personas.iterator().hasNext()) {
            throw new EntityNotFoundException("No se encontraron personas con el nombre: " + nombre);
        }
        return personas;
    }
    @GetMapping
    public Iterable<PersonaOutputDto> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize){
        Iterable<PersonaOutputDto> personas = personaServicio.getAllPersonas(pageNumber, pageSize);

        if (!personas.iterator().hasNext()) {
            throw new EntityNotFoundException("No hay personas registradas.");
        }
        return personaServicio.getAllPersonas(pageNumber, pageSize);
    }

    @DeleteMapping("/{id_persona}")
    public ResponseEntity<String> deletePersonaById(@PathVariable int id_persona) {
        personaServicio.deletePersonaById(id_persona);
        return ResponseEntity.ok().body("Persona con id: " + id_persona + " borrada");
    }

    @PutMapping("/{id_persona}")
    public ResponseEntity<PersonaOutputDto> updatePersona(
            @PathVariable int id_persona,
            @RequestBody(required = false) PersonaInputDto persona) {

        try {
            PersonaOutputDto existingPersona = personaServicio.getPersonaById(id_persona);

            if (existingPersona == null){
                throw new EntityNotFoundException("No se encontró ninguna persona con el Id: " +id_persona);
            }

            PersonaInputDto existingPersonaInput = new PersonaInputDto();
            existingPersonaInput.setId_persona(existingPersona.getId_persona());
            existingPersonaInput.setName(existingPersona.getName());
            existingPersonaInput.setUsuario(existingPersona.getUsuario());
            existingPersonaInput.setSurname(existingPersona.getSurname());
            existingPersonaInput.setCity(existingPersona.getCity());
            existingPersonaInput.setCreated_date(existingPersona.getCreated_date());
            existingPersonaInput.setCompany_email(existingPersona.getCompany_email());
            existingPersonaInput.setPersonal_email(existingPersona.getPersonal_email());
            existingPersonaInput.setActive(existingPersona.getActive());
            existingPersonaInput.setImagen_url(existingPersona.getImagen_url());
            existingPersonaInput.setTermination_date(existingPersona.getTermination_date());


            if (persona.getName() != null) {
                existingPersonaInput.setName(persona.getName());
            }
            if (persona.getUsuario() != null){
                existingPersonaInput.setUsuario(persona.getUsuario());
            }
            if (persona.getCity() != null) {
                existingPersonaInput.setCity(persona.getCity());
            }
            PersonaOutputDto updatePersona = personaServicio
                    .updatePersona(existingPersonaInput);
            if(updatePersona != null){
                return ResponseEntity.ok().body(updatePersona);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private void validarCampos(PersonaInputDto persona){
        if (persona.getUsuario().length() < 6 || persona.getUsuario().length() > 10) {
            throw new UnprocessableEntityException("El nombre de usuario debe tener entre 6 y 10 caracteres");
        }
        if (persona.getPassword() == null) {
            throw new UnprocessableEntityException("La contraseña no puede estar vacía");
        }
        if (persona.getCompany_email() == null) {
            throw new UnprocessableEntityException("Escriba el email de su compañía");
        }
        if (persona.getCity() == null) {
            throw new UnprocessableEntityException("El campo ciudad no puede ser nulo");
        }
        if (persona.getCreated_date() == null) {
            throw new UnprocessableEntityException("El campo de fecha de creación no puede ser nulo");
        }
    }
}
