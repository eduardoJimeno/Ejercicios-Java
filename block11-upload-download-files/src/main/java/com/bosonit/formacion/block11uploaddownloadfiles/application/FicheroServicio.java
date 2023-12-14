package com.bosonit.formacion.block11uploaddownloadfiles.application;

import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FicheroServicio {
    FicheroOutputDto addFichero(MultipartFile fichero, String categoria, String tipo) throws IOException;
    Resource getFicheroById(int id) throws FileNotFoundException;
    Resource getFicheroByNombre(String nombre) throws FileNotFoundException;
    void setUploadPath(String nuevoDirectorio);
    void setDownloadPath(String nuevoDownload);
}
