package com.formacion.bosonit.examen_JPA_cascada;

import com.formacion.bosonit.examen_JPA_cascada.application.FacturaServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationRunner implements ApplicationRunner {
    @Autowired
    FacturaServicioImpl facturaServicioImpl;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        facturaServicioImpl.addFacturaWithLinesOnStartup();
    }
}
