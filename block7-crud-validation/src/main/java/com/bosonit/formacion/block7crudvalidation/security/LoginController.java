package com.bosonit.formacion.block7crudvalidation.security;

import com.bosonit.formacion.block7crudvalidation.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.repository.PersonaRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    PersonaRepository personaRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMap) {
        String usuario = requestMap.get("usuario");
        String password = requestMap.get("password");
        Persona persona = personaRepository.findByUsuario(usuario)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Persona not found with username: " + usuario
                ));
        if (!persona.getPassword().equals(password)) {
            throw new EntityNotFoundException("Passwords do not match");
        }
        String role = Boolean.TRUE.equals(persona.getAdmin())
                ? "ADMIN"
                : "USER";
        return new ResponseEntity<>(getJWTToken(usuario, role), HttpStatus.OK);
    }

    private String getJWTToken(String usuario, String role) {
        String secretKey = "mySecretKey";
        System.out.println("Used Secret Key: " + secretKey);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(role);

        String token = Jwts
                .builder()
                .setSubject(usuario)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .toList())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        System.out.println("Generated Token: " + token);

        return "Bearer " + token;
    }
}