package com.bosonit.formacion.block7crudvalidation;

import com.bosonit.formacion.block7crudvalidation.application.PersonaServicio;
import com.bosonit.formacion.block7crudvalidation.application.PersonaServicioImpl;
import com.bosonit.formacion.block7crudvalidation.controller.PersonaController;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.repository.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PersonaServiceTest {
    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonaServicio personaServicio = new PersonaServicioImpl();

    final static public String USUARIO = "wtrioja";
    final static public String EMAIL = "company@email.com";
    final static public String PASSWORD = "password";
    final static public String CIUDAD = "ciudad";
    final static public Date DATE = new Date();

    final static public int ID = 1;

    @InjectMocks
    private PersonaController personaController;
    final static public int PAGENUMBER = 0;
    final static public int PAGESIZE = 10;
    final static public String NAME= "Eduardo";

    @Test
    public void testAddPersona(){
        PersonaInputDto personaInputDto = new PersonaInputDto();
        personaInputDto.setUsuario(USUARIO);
        personaInputDto.setCompanyEmail(EMAIL);
        personaInputDto.setPassword(PASSWORD);
        personaInputDto.setCity(CIUDAD);
        personaInputDto.setCreatedDate(DATE);

        Persona expectedPersona = new Persona();
        expectedPersona.setUsuario(USUARIO);
        when(personaRepository.save(any())).thenReturn(expectedPersona);

        PersonaOutputDto result = personaServicio.addPersona(personaInputDto);

        verify(personaRepository, times(1)).save(any());

        assertNotNull(result);
        assertEquals(USUARIO, result.getUsuario());
    }

    @Test
    public void testGetPersonaById_Existente() {

        when(personaRepository.findById(ID)).thenReturn(Optional.of(new Persona()));
        PersonaOutputDto result = personaServicio.getPersonaById(ID);
        verify(personaRepository, times(1)).findById(ID);
        assertNotNull(result);
    }

    @Test
    public void testGetPersonaById_Inexistente() {

        when(personaRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> personaServicio.getPersonaById(ID));
        verify(personaRepository, times(1)).findById(ID);
    }

    @Test
    public void testUpdatePersona(){

        when(personaRepository.findByCompanyEmail(anyString())).thenReturn(Optional.of(new Persona()));
        when(personaRepository.save(any(Persona.class))).thenReturn(new Persona());

        PersonaInputDto inputDto = new PersonaInputDto();
        inputDto.setUsuario(USUARIO);
        inputDto.setPassword(PASSWORD);
        inputDto.setCompanyEmail(EMAIL);
        inputDto.setCity(CIUDAD);
        inputDto.setCreatedDate(new Date());

        PersonaOutputDto result = personaServicio.updatePersona(inputDto);

        assertNotNull(result);
    }

    @Test
    public void testGetAllPersonasWithPagination(){
        List<Persona> personasList = Arrays.asList(new Persona(), new Persona());
        Page<Persona> page = new PageImpl<>(personasList);

        when(personaRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Iterable<PersonaOutputDto> result = personaServicio.getAllPersonas(PAGENUMBER, PAGESIZE);

        assertNotNull(result);
        assertInstanceOf(List.class, result);
        assertEquals(2, ((List<PersonaOutputDto>) result).size());
    }

}



