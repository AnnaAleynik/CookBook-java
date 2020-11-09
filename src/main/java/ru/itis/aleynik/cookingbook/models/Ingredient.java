package ru.itis.aleynik.cookingbook.models;

import java.util.Objects;

public class Ingredient {

    private int i_id;
    private String title;
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

    public int getId() {
        return i_id;
    }

    public void setId(int i_id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return i_id == that.i_id &&
                Objects.equals(title, that.title) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i_id, title, amount);
    }

    @Override
    public String toString() {
        return title;
    }
}
