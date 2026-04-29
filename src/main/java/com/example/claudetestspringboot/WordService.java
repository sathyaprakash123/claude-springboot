package com.example.claudetestspringboot;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WordService {

    private final List<String> dictionary = new ArrayList<>();

    @PostConstruct
    public void loadDictionary() throws Exception {
        ClassPathResource resource = new ClassPathResource("words.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim().toLowerCase();
                if (!word.isEmpty()) {
                    dictionary.add(word);
                }
            }
        }
    }

    public List<String> generateWords(String name) {
        String letters = name.toLowerCase().replaceAll("[^a-z]", "");
        Map<Character, Integer> available = charFrequency(letters);

        List<String> result = new ArrayList<>();
        for (String word : dictionary) {
            if (canForm(word, available)) {
                result.add(word);
            }
        }
        result.sort(String::compareTo);
        return result;
    }

    private boolean canForm(String word, Map<Character, Integer> available) {
        Map<Character, Integer> needed = charFrequency(word);
        for (Map.Entry<Character, Integer> entry : needed.entrySet()) {
            if (available.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    private Map<Character, Integer> charFrequency(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.merge(c, 1, Integer::sum);
        }
        return freq;
    }
}
