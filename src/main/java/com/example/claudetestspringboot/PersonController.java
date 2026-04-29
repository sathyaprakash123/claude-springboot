package com.example.claudetestspringboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody PersonRequest request) {
        String response = String.format("Received: name=%s, age=%d, gender=%s",
                request.getName(), request.getAge(), request.getGender());
        return ResponseEntity.ok(response);
    }
}
