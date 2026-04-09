package fr.efrei.tp1devops.calculator.exception;

/**
 * Thrown when {@code b} is zero for division.
 */
public class DivisionByZeroException extends RuntimeException {

    public DivisionByZeroException() {
        super("Division by zero is not allowed");
    }
}
