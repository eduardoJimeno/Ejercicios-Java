package com.formacion.bosonit.filter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/add")
    public String addParametros(HttpServletRequest request){
        String parametros = (String) request.getAttribute("parametros");

        return "Me has añadido una " + parametros;
    }
    @GetMapping("/{endPoint}/{parametro}={path}")
    public String miEndpoint(
            @PathVariable String endPoint,
            @PathVariable String parametro,
            @PathVariable String path) {
        return "Has llamado al endpoint \"" + endPoint +
                "\" con parámetro \"" + parametro +
                "\" y path \"" + path + "\"";
    }
}
