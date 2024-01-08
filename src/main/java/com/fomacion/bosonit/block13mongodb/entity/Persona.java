package com.fomacion.bosonit.block13mongodb.entity;

import com.fomacion.bosonit.block13mongodb.controller.dto.PersonaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "persona")
public class Persona {
    @Id
    private String idPersona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private Boolean active;
    private Date createdDate;
    private String imagenUrl;
    private Date terminationDate;

}


