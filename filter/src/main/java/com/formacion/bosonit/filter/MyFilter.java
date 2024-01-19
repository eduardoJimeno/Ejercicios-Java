package com.formacion.bosonit.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class MyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Map<String, String[]> parameterMap = request.getParameterMap();

        // Crear una cadena con la información de los parámetros
        String parametros = parameterMap.entrySet().stream()
                .map(entry -> entry.getKey() + " " + String.join(", ", entry.getValue()))
                .collect(Collectors.joining(", "));

        // Agregar la información de los parámetros a la respuesta
        request.setAttribute("parametros", parametros);

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
