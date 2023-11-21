package com.bosonit.formacion.block6personcontrollers.controller;

import com.bosonit.formacion.block6personcontrollers.entities.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/controlador")
public class Controlador {

    @Autowired
    @Qualifier("bean1")
    private Persona personaBean1;

    @Autowired
    @Qualifier("bean2")
    private Persona personaBean2;

    @Autowired
    @Qualifier("bean3")
    private Persona personaBean3;

    @GetMapping(value = "/bean/{bean}")
    public Persona obtenerBeanPorNombre(@PathVariable String bean){
        return switch (bean) {
            case "bean1" -> personaBean1;
            case "bean2" -> personaBean2;
            case "bean3" -> personaBean3;
            default -> null;
        };
    }
}
