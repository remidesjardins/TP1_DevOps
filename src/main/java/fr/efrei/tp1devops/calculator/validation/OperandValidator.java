package fr.efrei.tp1devops.calculator.validation;

import fr.efrei.tp1devops.calculator.exception.InvalidOperandException;
import org.springframework.stereotype.Component;

/**
 * Validates calculator operands: finite values within an absolute bound.
 */
@Component
public class OperandValidator {

    static final double ABS_MAX_OPERAND = 1e12d;

    public void validatePair(double a, double b) {
        validateSingle("a", a);
        validateSingle("b", b);
    }

    private static void validateSingle(String name, double value) {
        if (Double.isNaN(value)) {
            throw new InvalidOperandException("Parameter " + name + " must be a finite number (NaN is not allowed).");
        }
        if (Double.isInfinite(value)) {
            throw new InvalidOperandException("Parameter " + name + " must be a finite number (infinity is not allowed).");
        }
        if (Math.abs(value) > ABS_MAX_OPERAND) {
            throw new InvalidOperandException(
                    "Parameter " + name + " is out of bounds: absolute value must be <= " + ABS_MAX_OPERAND + "."
            );
        }
    }
}
