package com.bosonit.formacion.block7crudrelaciones.controller;

import com.bosonit.formacion.block7crudrelaciones.application.PersonaServicioImpl;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudrelaciones.controller.dto.PersonaRolOutputDto;
import com.bosonit.formacion.block7crudrelaciones.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block7crudrelaciones.exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block7crudrelaciones.repository.ProfesorRepository;
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
        return ResponseEntity.status(200)
                .body(personaServicio.addPersona(persona));
    }

    @GetMapping("/{id_persona}")
    public ResponseEntity<PersonaRolOutputDto> getPersonaById(
            @PathVariable int id_persona,
            @RequestParam(defaultValue = "false") boolean rol)
    {
        return ResponseEntity.ok().body(personaServicio.getPersonaById(id_persona, rol));
    }
    @GetMapping("/nombre/{name}")
    public Iterable<PersonaRolOutputDto> getAllPersonasByNameLike(
            @PathVariable String nombre,
            @RequestParam(defaultValue = "false") boolean rol){
        Iterable<PersonaRolOutputDto> personas = personaServicio.getPersonasByName(nombre, rol);

        if (!personas.iterator().hasNext()) {
            throw new EntityNotFoundException("No se encontraron personas con el nombre: " + nombre);
        }
        return personas;
    }
    @GetMapping
    public Iterable<PersonaRolOutputDto> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize,
            @RequestParam(defaultValue = "false") boolean rol){
        Iterable<PersonaRolOutputDto> personas = personaServicio.getAllPersonas(pageNumber, pageSize, rol);

        if (!personas.iterator().hasNext()) {
            throw new EntityNotFoundException("No hay personas registradas.");
        }
        return personas;
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

            PersonaRolOutputDto existingPersona = personaServicio.getPersonaById(id_persona, false);

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
