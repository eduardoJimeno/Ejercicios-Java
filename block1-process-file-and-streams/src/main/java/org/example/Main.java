package org.example;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.ArchivoCSV.leerArchivo;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws Exception {


        List<Person> personas = leerArchivo("people.csv");

        List<Person> menores25 = personas.stream()
                        .filter(person -> person.getAge() < 25 && person.getAge()!=0)
                        .toList();
        System.out.println("A) Personas menores de 25 años: ");
        for (Person persona : menores25){
            System.out.println("\t" + persona);
        }

        List<Person> eliminarA = personas.stream()
                        .filter(person -> !person.getName().matches("[AÁaÁ].*"))
                        .toList();
        System.out.println("B) Eliminar personas cuyo nombre empiece por A: ");
        for (Person persona : eliminarA){
            System.out.println("\t" + persona);
        }

        System.out.println("C) Usando las personas de A) 1ª persona de Madrid: (Optional)");
            Optional<Person> madrid = personas.stream()
                    .filter(person -> person.getAge() < 25 && person.getAge()!=0)
                    .filter(person -> person.getTown().equalsIgnoreCase("madrid"))
                    .findFirst();
            madrid.ifPresent(person -> System.out.println("\t" + person));


        System.out.println("C) Usando las personas de A) 1ª persona de Barcelona: (Optional)");
            Optional<Person> barcelona = personas.stream()
                    .filter(person -> person.getAge() < 25 && person.getAge()!=0)
                    .filter(person -> person.getTown().equalsIgnoreCase("barcelona"))
                    .findFirst();
            barcelona.ifPresentOrElse(System.out::println, () -> System.out.println("\t El optional está vacío") );
    }
}
