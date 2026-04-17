package fr.efrei.tp1devops.calculator.exception;

/**
 * Thrown when operands are not acceptable for the calculator domain (non-finite or out of bounds).
 */
public class InvalidOperandException extends RuntimeException {

    public InvalidOperandException(String message) {
        super(message);
    }
}
