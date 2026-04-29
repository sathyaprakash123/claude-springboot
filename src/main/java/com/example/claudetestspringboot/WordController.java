package com.example.claudetestspringboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/words")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping
    public ResponseEntity<?> getWords(@RequestBody WordRequest request) {
        String name = request.getName();
        if (name == null || name.isBlank()) {
            return ResponseEntity.badRequest().body("Name must not be empty.");
        }
        List<String> words = wordService.generateWords(name);
        return ResponseEntity.ok(words);
    }
}
