package fr.efrei.tp1devops.calculator.service;

import fr.efrei.tp1devops.calculator.dto.CalculationResponse;
import fr.efrei.tp1devops.calculator.exception.DivisionByZeroException;
import fr.efrei.tp1devops.calculator.model.Operation;
import fr.efrei.tp1devops.calculator.validation.OperandValidator;
import org.springframework.stereotype.Service;

/**
 * Encapsulates calculator business rules (operand validation and division safeguards).
 */
@Service
public class CalculatorService {

    private final OperandValidator operandValidator;

    public CalculatorService(OperandValidator operandValidator) {
        this.operandValidator = operandValidator;
    }

    public CalculationResponse add(double a, double b) {
        operandValidator.validatePair(a, b);
        return build(Operation.ADD, a, b, a + b);
    }

    public CalculationResponse subtract(double a, double b) {
        operandValidator.validatePair(a, b);
        return build(Operation.SUB, a, b, a - b);
    }

    public CalculationResponse multiply(double a, double b) {
        operandValidator.validatePair(a, b);
        return build(Operation.MUL, a, b, a * b);
    }

    public CalculationResponse divide(double a, double b) {
        operandValidator.validatePair(a, b);
        if (b == 0.0d) {
            throw new DivisionByZeroException();
        }
        return build(Operation.DIV, a, b, a / b);
    }

    private static CalculationResponse build(Operation operation, double a, double b, double result) {
        return new CalculationResponse(operation.getApiName(), a, b, result);
    }
}
