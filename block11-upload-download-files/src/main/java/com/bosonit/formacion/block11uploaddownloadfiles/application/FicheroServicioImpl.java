package com.bosonit.formacion.block11uploaddownloadfiles.application;

import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroInputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.domain.Fichero;
import com.bosonit.formacion.block11uploaddownloadfiles.repository.FicheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

import java.util.Date;


@Service
public class FicheroServicioImpl implements FicheroServicio{
    @Autowired
    FicheroRepository ficheroRepository;
    private String uploadPath = "c:/tmp";
    @Value("${download.path:}")
    private String downloadPath;
    private final ResourceLoader resourceLoader;

    public FicheroServicioImpl(ResourceLoader resourceLoader) throws IOException {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public FicheroOutputDto addFichero(MultipartFile fichero, String categoria, String tipo) throws IOException {
        if(!tipo.equals(categoria)){
            throw new IllegalArgumentException("El tipo y la categoría no coinciden");
        }
        FicheroInputDto ficheroInputDto = validarCampos(fichero, categoria);
        return ficheroRepository.save(new Fichero(ficheroInputDto)).ficheroToFicheroOutputDto();
    }

    @Override
    public Resource getFicheroById(int id)  throws FileNotFoundException{
        Fichero fichero = ficheroRepository.findById(id)
                .orElseThrow(()-> new FileSystemNotFoundException("Fichero no encontrado con ID: " +id));
        return getResourceByFileName(fichero.getNombreFichero());
    }

    @Override
    public Resource getFicheroByNombre(String nombre) throws FileNotFoundException{
        Fichero fichero = ficheroRepository.findByNombreFichero(nombre)
                .orElseThrow(()-> new FileSystemNotFoundException("Fichero no encontrado con nombre: " +nombre));
        return getResourceByFileName(fichero.getNombreFichero());
    }

    private Resource getResourceByFileName(String nombre) throws FileNotFoundException {
        try {
            File uploadPathFile = new File(uploadPath);
            File ficheroBuscado = new File(uploadPathFile, nombre);
            if (!ficheroBuscado.exists()) {
                throw new FileNotFoundException("Fichero no encontrado con nombre: " + nombre);
            }

            // Obtengo la ruta real de la app
            String applicationPath = Paths.get("").toAbsolutePath().toString();

            // Creo la carpeta de descarga si no existe
            Path downloadFolderPath = Paths.get(applicationPath, downloadPath);
            if (!Files.exists(downloadFolderPath)) {
                try {
                    Files.createDirectories(downloadFolderPath);
                    System.out.println("Carpeta de descarga creada: " + downloadFolderPath.toAbsolutePath());
                } catch (Exception e) {
                    System.err.println("Error al crear la carpeta de descarga: " + e.getMessage());
                }
            }

            // Copio el fichero a la carpeta de descargas
            Path ficheroDestino = Paths.get(downloadFolderPath.toString(), nombre);
            Files.copy(ficheroBuscado.toPath(), ficheroDestino, StandardCopyOption.REPLACE_EXISTING);

            // Obtengo el recurso descargado
            return resourceLoader.getResource("file:" + ficheroDestino.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error al obtener el recurso del fichero", e);
        }
    }

    private FicheroInputDto validarCampos(MultipartFile fichero, String categoria) throws IOException {

        File uploadPathFile = new File(uploadPath);
        // Creo carpeta en disco duro si no existe (por defecto c:/tmp)
        if(!uploadPathFile.exists())
        {
            uploadPathFile.mkdirs();
        }
        String nombreFichero = fichero.getOriginalFilename();
        assert nombreFichero != null;
        Path pathFichero = uploadPathFile.toPath().resolve(nombreFichero);
        Files.copy(fichero.getInputStream(), pathFichero, StandardCopyOption.REPLACE_EXISTING);
        return new FicheroInputDto(nombreFichero, new Date(), categoria);
    }

    @Override
    public void setUploadPath(String nuevoDirectorio) {
        this.uploadPath = nuevoDirectorio;
    }

    @Override
    public void setDownloadPath(String nuevoDownload) {
        this.downloadPath = nuevoDownload;
    }

}
