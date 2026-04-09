package fr.efrei.tp1devops.calculator.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

/**
 * Standard error envelope returned by {@link fr.efrei.tp1devops.calculator.exception.GlobalExceptionHandler}.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        @JsonProperty("timestamp") Instant timestamp,
        @JsonProperty("status") int status,
        @JsonProperty("error") String error,
        @JsonProperty("message") String message,
        @JsonProperty("path") String path
) {
}
