package com.jtcode.sharedfloor.model;


public class ClearTurn {
    private String id;
    private String nameUser;//the user who have this turn
    private String nameTurn;
    private String description;

    public ClearTurn(String name, String description,String nameTurn) {
        this.nameUser = name;
        this.nameTurn= nameTurn;
        this.description = description;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getNameTurn() {
        return nameTurn;
    }

    public String getDescription() {
        return description;
    }
}
