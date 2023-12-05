package com.bosonit.formacion.block7crudrelaciones.application;

import com.bosonit.formacion.block7crudrelaciones.controller.dto.ProfesorOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(url = "http://localhost:8081", name = "myFeign")
@RequestMapping("/profesor")
public interface MyFeign {
    @GetMapping("/{id_profesor}")
    ProfesorOutputDto getProfesorById(@PathVariable("id_profesor") int id);
}
