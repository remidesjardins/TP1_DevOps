package fr.efrei.tp1devops.calculator.model;

/**
 * Supported calculator operations exposed by the API.
 */
public enum Operation {
    ADD("add"),
    SUB("sub"),
    MUL("mul"),
    DIV("div");

    private final String apiName;

    Operation(String apiName) {
        this.apiName = apiName;
    }

    public String getApiName() {
        return apiName;
    }
}
