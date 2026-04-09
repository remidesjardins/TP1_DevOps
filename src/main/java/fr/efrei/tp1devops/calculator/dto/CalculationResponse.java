package fr.efrei.tp1devops.calculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON body for successful calculation responses.
 */
public record CalculationResponse(
        @JsonProperty("operation") String operation,
        @JsonProperty("a") double a,
        @JsonProperty("b") double b,
        @JsonProperty("result") double result
) {
}
