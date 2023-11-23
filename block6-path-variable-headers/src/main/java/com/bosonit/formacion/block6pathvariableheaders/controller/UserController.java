package com.bosonit.formacion.block6pathvariableheaders.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @PostMapping("/")
    public String json(@RequestBody String json){
        return json;
    }

    @GetMapping(value = "user/{id}")
    public int userId(@PathVariable int id ){
        return id;
    }

    @PutMapping("/post")
    public ResponseEntity<Map<String, Object>> putData(
            @RequestParam Map<String, String> requestParams) {

        Map<String, Object> resultMap = new HashMap<>(requestParams);

        return ResponseEntity.ok(resultMap);
    }

    @GetMapping("/header")
    public String h1H2(
            @RequestHeader(value="h1", required = false) String h1,
            @RequestHeader(value ="h2", required = false) String h2)
    {
        return (h1 + "\n" + h2);
    }

    @PostMapping("/all")
    public ResponseEntity<Datos> all(
            @RequestBody (required = false) String body,
            @RequestHeader Map<String, String> headers,
            @RequestParam Map<String, String> requestParams){

        Datos datos = new Datos();
        datos.setBody(body);
        datos.setHeaders(new ArrayList<>(headers.values()));
        datos.setRequestParams(new ArrayList<>(requestParams.values()));

        return ResponseEntity.ok(datos);
    }


    public static class Datos{
        private String body;
        private List<String> headers;
        private List<String> requestParams;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<String> getHeaders() {
            return headers;
        }

        public void setHeaders(List<String> headers) {
            this.headers = headers;
        }

        public List<String> getRequestParams() {
            return requestParams;
        }

        public void setRequestParams(List<String> requestParams) {
            this.requestParams = requestParams;
        }
    }
}
