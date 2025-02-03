package com.example.erudio.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.erudio.services.MathService;

@RestController
@RequestMapping("/caculator")
public class MathController {

    private final MathService mathService;

    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> soma(@PathVariable String numberOne,
            @PathVariable String numberTwo) {
        return ResponseEntity.ok().body(mathService.sum(numberOne, numberTwo));
    }

    @GetMapping("/subs/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> substract(@PathVariable String numberOne,
            @PathVariable String numberTwo) {
        return ResponseEntity.ok().body(mathService.substract(numberOne, numberTwo));
    }

    @GetMapping("/mult/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> multiplication(@PathVariable String numberOne,
            @PathVariable String numberTwo) {
        return ResponseEntity.ok().body(mathService.multiplication(numberOne, numberTwo));
    }

    @GetMapping("/div/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> division(@PathVariable String numberOne,
            @PathVariable String numberTwo) {
        return ResponseEntity.ok().body(mathService.division(numberOne, numberTwo));
    }

    @GetMapping("/med/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> media(@PathVariable String numberOne,
            @PathVariable String numberTwo) {
        return ResponseEntity.ok().body(mathService.media(numberOne, numberTwo));
    }

    @GetMapping("/raiz/{number}")
    public ResponseEntity<Double> raizQuadrada(@PathVariable String number) {
        return ResponseEntity.ok().body(mathService.raizQuadrada(number));
    }

}
