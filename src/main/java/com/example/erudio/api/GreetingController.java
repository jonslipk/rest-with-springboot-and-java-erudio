package com.example.erudio.api;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.erudio.domain.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/greetings")
public class GreetingController {

    private static final String THEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public ResponseEntity<Greeting> findGreeting(
            @RequestParam(value = "name", defaultValue = "Wolrd") String valString) {
        return ResponseEntity.ok().body(new Greeting(counter.incrementAndGet(), String.format(THEMPLATE, valString)));
    }

}
