package ru.itis.aleynik.cookingbook.models;

public class Ingredient {

    public int i_id;
    public String title;
    private String amount;


    public Ingredient(int id, String title) {
        this.i_id = id;
        this.title = title;
    }

    public Ingredient(int id, String title, String amount) {
        this.i_id = id;
        this.title = title;
        this.amount = amount;
    }

    public int getI_id() {
        return i_id;
    }

    public void setI_id(int i_id) {
        this.i_id = i_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
