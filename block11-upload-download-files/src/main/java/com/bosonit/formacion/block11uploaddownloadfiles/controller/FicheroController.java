package com.bosonit.formacion.block11uploaddownloadfiles.controller;

import com.bosonit.formacion.block11uploaddownloadfiles.application.FicheroServicio;
import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;


@RestController
@RequestMapping("/fichero")
public class FicheroController {
    @Autowired
    FicheroServicio ficheroServicio;

    @PostMapping(path = "/{tipo}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FicheroOutputDto> addFichero(
            @PathVariable String tipo,
            @RequestParam("fichero")MultipartFile fichero,
            @RequestParam("categoria")String categoria) throws IOException {
        return ResponseEntity.ok().body(ficheroServicio.addFichero(fichero, categoria, tipo));
    }

    @GetMapping("/setpath")
    public ResponseEntity<Void> setPath(@RequestParam("path") String nuevoDirectorio) {
        ficheroServicio.setUploadPath(nuevoDirectorio);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/descarga")
    public ResponseEntity<Resource> descargaFichero(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "nombre", required = false) String nombre) {
        try {
            Resource recurso;

            if (id != null && id > 0) {
                recurso = ficheroServicio.getFicheroById(id);
            } else if (nombre != null && !nombre.isEmpty()) {
                recurso = ficheroServicio.getFicheroByNombre(nombre);
            } else {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                    .body(recurso);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
