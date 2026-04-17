package fr.efrei.tp1devops.calculator.controller;

import fr.efrei.tp1devops.calculator.dto.CalculationResponse;
import fr.efrei.tp1devops.calculator.service.CalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** REST API for basic arithmetic (finite operands within configured bounds). */
@RestController
@RequestMapping("/api/calc")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public CalculationResponse add(
            @RequestParam("a") double a,
            @RequestParam("b") double b
    ) {
        return calculatorService.add(a, b);
    }

    @GetMapping("/sub")
    public CalculationResponse sub(
            @RequestParam("a") double a,
            @RequestParam("b") double b
    ) {
        return calculatorService.subtract(a, b);
    }

    @GetMapping("/mul")
    public CalculationResponse mul(
            @RequestParam("a") double a,
            @RequestParam("b") double b
    ) {
        return calculatorService.multiply(a, b);
    }

    @GetMapping("/div")
    public CalculationResponse div(
            @RequestParam("a") double a,
            @RequestParam("b") double b
    ) {
        return calculatorService.divide(a, b);
    }
}
