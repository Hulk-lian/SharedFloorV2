package com.jtcode.sharedfloor.model;


public class ClearTurn {
    private String id;
    private String name;
    private String description;

    public ClearTurn(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
