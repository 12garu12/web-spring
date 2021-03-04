package com.practice.webspring.app;

import com.practice.webspring.app.models.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebSpringApplication implements CommandLineRunner {

    @Autowired
    private IUploadService uploadService;

    public static void main(String[] args) {
        SpringApplication.run(WebSpringApplication.class, args);
    }

    /**
     Para crear la carpeta upload para subir las imagenes del cliente automaticmento el programa la crea
     */
    @Override
    public void run(String... args) throws Exception {
        uploadService.deleteAll();
        uploadService.init();
    }
}
