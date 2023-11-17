package com.bosonit.formacion.block5properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Componente implements CommandLineRunner {

    private final MyConfigProperties myConfigProperties;
    private String entorno = "PUBLIC";
    private String entorno2 = "MYURL";
    private String valor = System.getenv(entorno);
    private String valor2 = System.getenv(entorno2);

    @Autowired
    public Componente(MyConfigProperties myConfigProperties){
        this.myConfigProperties = myConfigProperties;
    }

    @Value("${greeting}")
    private String greeting;

    @Value("${my.number}")
    private int myNumber;

    @Value("${new.property:new.property no tiene valor}")
    private String newProperty;

    public Componente() {
        myConfigProperties = null;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Valores desde application.properties:");
        System.out.println("\t El valor de greeting es: " + greeting);
        System.out.println("\t El valor de my.number es: " + myNumber);
        System.out.println("\t El valor de new.property es: " + newProperty);

        System.out.println("Valores desde application.yml");
        System.out.println("\t El valor de greeting es: " + myConfigProperties.getGreeting());
        System.out.println("\t El valor de my.number es: " + myConfigProperties.getMyNumber());
        System.out.println("\t El valor de new.property es: " + myConfigProperties.getNewProperty());

        System.out.println("Devolución de una variable de entorno existente: ");
        if (valor != null) {
            System.out.println("\t El valor de la variable de entorno " + entorno + " es " + valor);
        } else {
            System.out.println("\t La variable de entorno " + entorno + " no esta definida.");
        }
        System.out.println("Devolución de una variable de entono que no existe: ");
        if (valor2 != null) {
            System.out.println("\t El valor de la variable de entorno " + entorno2 + " es " + valor2);
        } else {
            System.out.println("\t La variable de entorno " + entorno2 + " no esta definida.");
        }
    }
}
