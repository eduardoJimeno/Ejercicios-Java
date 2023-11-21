package com.bosonit.formacion.block6simplecontrollers;

import com.bosonit.formacion.block6simplecontrollers.entities.Persona;
import org.springframework.web.bind.annotation.*;

@RestController
public class RESTController {

    @GetMapping(value = "/user/{nombre}")
    public String hola(@PathVariable String nombre){
        return "Hola " + nombre;
    }

    @PostMapping(value = "/useradd")
    public Persona persona (@RequestBody Persona request){
        return new Persona(request.getNombre(), request.getPoblacion(), request.getEdad() +1);
    }


}
