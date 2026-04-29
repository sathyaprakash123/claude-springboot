package com.example.claudetestspringboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody PersonRequest request) {
        Person saved = personService.savePerson(request);
        String response = String.format(
                "Person data successfully stored! ID: %d | Name: %s | Age: %d | Gender: %s",
                saved.getId(), saved.getName(), saved.getAge(), saved.getGender());
        return ResponseEntity.ok(response);
    }
}
