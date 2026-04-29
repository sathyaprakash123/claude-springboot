package com.example.claudetestspringboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/date")
public class DateController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping
    public ResponseEntity<String> getDaysDifference(@RequestParam String date) {
        LocalDate target;
        try {
            target = LocalDate.parse(date, FORMATTER);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Use yyyy-MM-dd (e.g. 2025-12-31).");
        }

        LocalDate today = LocalDate.now();
        long days = ChronoUnit.DAYS.between(today, target);

        if (days > 0) {
            return ResponseEntity.ok(days + " day" + (days == 1 ? "" : "s") + " until " + date + ".");
        } else if (days < 0) {
            return ResponseEntity.ok((-days) + " day" + (-days == 1 ? "" : "s") + " have elapsed since " + date + ".");
        } else {
            return ResponseEntity.ok("That is today!");
        }
    }
}
