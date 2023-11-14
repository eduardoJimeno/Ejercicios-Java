package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArchivoCSV {
    public static List<Person> leerArchivo(String ruta) throws IOException {

        Path path = Paths.get(ruta);

        List<Person> personas = new ArrayList<>();
        List<String> lineas = null;
        try {
            lineas = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
            System.out.println(lineas);
            System.out.println(personas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (lineas != null) {
            for (String linea : lineas) {
                String[] campos = linea.split(":");
                String name = campos[0].trim();
                String town = (campos.length > 1) ? campos[1].trim() : "";
                int age = (campos.length > 2) ? Integer.parseInt(campos[2]) : 0;
                if (!name.isEmpty()) {
                    personas.add(new Person(name, town, age));
                } else {
                    throw new IOException("El nombre es obligatorio" + linea);
                }
            }
        }
        System.out.println(personas);
        return personas;

    }

}
