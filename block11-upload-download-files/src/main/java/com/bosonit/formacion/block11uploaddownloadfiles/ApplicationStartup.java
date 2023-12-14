package com.bosonit.formacion.block11uploaddownloadfiles;


import com.bosonit.formacion.block11uploaddownloadfiles.application.FicheroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private FicheroServicio ficheroServicio;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        String[] args = event.getArgs();
        if (args.length > 0) {
            ficheroServicio.setDownloadPath(args[0]);
            System.out.println("Ruta de descarga: " + args[0]);
        } else {
            System.out.println("Sin argumento para la ruta de descarga.");
        }
    }
}