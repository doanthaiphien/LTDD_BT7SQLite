package com.example.bt10sqlite.Model;

public class Category {
    String idCategory246,nameCategory246;

    public Category() {
    }

    public Category(String idCategory, String nameCategory) {
        this.idCategory246 = idCategory;
        this.nameCategory246 = nameCategory;
    }

    public String getIdCategory() {
        return idCategory246;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory246 = idCategory;
    }

    public String getNameCategory() {
        return nameCategory246;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory246 = nameCategory;
    }

    @Override
    public String toString() {
        return "Khoa: "+ idCategory246 +"\n"+"Ngành: "+nameCategory246;
    }
}
