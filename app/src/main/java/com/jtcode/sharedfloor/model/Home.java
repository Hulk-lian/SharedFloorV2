package com.jtcode.sharedfloor.model;

public class Home {
    private String id;
    private String name;
    private User administrator;
    private int numberUsers; //calculated field the number of user in the home

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAdministrator() {
        return administrator;
    }

    public void setAdministrator(User administrator) {
        this.administrator = administrator;
    }

    public int getNumberUsers() {
        return numberUsers;
    }

    public Home(String name,User administrator){
        //id
        this.name=name;
        this.numberUsers=1;
        this.administrator=administrator;
    }
    public void addUsetToHome(){
        numberUsers++;
    }
}
