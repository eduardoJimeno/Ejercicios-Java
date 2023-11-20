package com.bosonit.formacion.block5profile;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppConfig {

    @Value("${app.name}")
    private String name;

    @Value("${bd.url}")
    private String url;

}
