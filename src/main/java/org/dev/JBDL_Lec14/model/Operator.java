package org.dev.JBDL_Lec14.model;

public enum Operator {
    EQUALS("="),
    LESS_THAN("<"),
    GREATER_THAN(">"),
    LIKE("LIKE"),
    IN("IN");

    private String value;

    Operator(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
