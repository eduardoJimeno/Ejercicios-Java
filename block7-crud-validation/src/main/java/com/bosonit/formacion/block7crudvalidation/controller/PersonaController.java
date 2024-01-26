package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.application.PersonaServicio;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PersonaController {

    @Autowired
    PersonaServicio personaServicio;
    @Autowired
    PersonaRepository personaRepository;
    public static final String GREATER_THAN="greater";
    public static final String LESS_THAN="less";
    public static final String EQUAL="equal";

    @CrossOrigin(origins = "https://cdpn.io")
    @PostMapping({"/addperson" , "/persona"})
    public ResponseEntity<PersonaOutputDto> addPersonaB(@RequestBody PersonaInputDto personaInputDto) {
        return ResponseEntity.ok().body(personaServicio.addPersona(personaInputDto));
    }

    @GetMapping("persona/{id_persona}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable int id_persona) {
        return ResponseEntity.ok().body(personaServicio.getPersonaById(id_persona));
    }

    @GetMapping("persona/nombre/{name}")
    public ResponseEntity<Iterable<PersonaOutputDto>> getAllPersonasByNameLike(@PathVariable String nombre) {
        Iterable<PersonaOutputDto> personas = personaServicio.getPersonasByName(nombre);

        if (!personas.iterator().hasNext()) {
            throw new EntityNotFoundException("No se encontraron personas con el nombre: " + nombre);
        }
        return ResponseEntity.ok(personas);
    }

    @CrossOrigin(origins = "https://cdpn.io")
    @GetMapping({"/getall", "persona"})
    public Iterable<PersonaOutputDto> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        Iterable<PersonaOutputDto> personas = personaServicio.getAllPersonas(pageNumber, pageSize);

        if (!personas.iterator().hasNext()) {
            throw new EntityNotFoundException("No hay personas registradas.");
        }
        return personaServicio.getAllPersonas(pageNumber, pageSize);
    }

    @DeleteMapping("persona/{id_persona}")
    public void deletePersonaById(@PathVariable int id_persona) {
        personaServicio.deletePersonaById(id_persona);
    }

    @PutMapping("persona/{id_persona}")
    public ResponseEntity<PersonaOutputDto> updatePersona(
            @PathVariable int id_persona,
            @RequestBody(required = false) PersonaInputDto personaInputDto) {

        return ResponseEntity.ok(personaServicio.updatePersona(personaInputDto));
    }

    @GetMapping("persona/customquery")
    public Page<PersonaOutputDto> findPersonaByManyFields(
            @RequestParam(required = false) Integer id_persona,
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date created_date,
            @RequestParam(required = false) String dateCondition,
            @RequestParam(required = false) String orderBy,
            @RequestParam int page,
            @RequestParam(defaultValue = "10") int size) {
        HashMap<String, Object> data = new HashMap<>();
        if (id_persona != null) data.put("id_persona", id_persona);
        if (usuario != null) data.put("usuario", usuario);
        if (name != null) data.put("name", name);
        if (surname != null) data.put("surname", surname);
        if (created_date == null)
            dateCondition = GREATER_THAN;
        if (!dateCondition.equals(GREATER_THAN) && !dateCondition.equals(LESS_THAN) && !dateCondition.equals((EQUAL)))
            dateCondition = GREATER_THAN;
        if (created_date != null) {
            data.put("created_date", created_date);
            data.put("dateCondition", dateCondition);
        }

        Sort sort = StringUtils.hasText(orderBy) ? Sort.by(orderBy) : Sort.unsorted();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        return personaRepository.findPersonaByManyFields(data, pageable);
    }
}