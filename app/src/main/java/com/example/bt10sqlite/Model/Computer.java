package com.example.bt10sqlite.Model;

public class Computer {
    String idComputer246,nameComputer246,idCategory246;


    public Computer(String idComputer, String nameComputer, String idCategory) {
        this.idComputer246 = idComputer;
        this.nameComputer246 = nameComputer;
        this.idCategory246 = idCategory;
    }

    public String getIdComputer() {
        return idComputer246;
    }

    public void setIdComputer(String idComputer) {
        this.idComputer246 = idComputer;
    }

    public String getNameComputer() {
        return nameComputer246;
    }

    public void setNameComputer(String nameComputer) {
        this.nameComputer246 = nameComputer;
    }

    public String getIdCategory() {
        return idCategory246;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory246 = idCategory;
    }
}
