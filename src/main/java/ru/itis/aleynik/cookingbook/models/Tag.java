package ru.itis.aleynik.cookingbook.models;

import java.util.LinkedList;

public class Tag {

    private int t_id;
    private String title;

    public Tag(int t_id, String title) {
        this.t_id = t_id;
        this.title = title;
    }

    public static LinkedList<Tag> listTagsByRecipeTitle(String title) {
        return new LinkedList<>();
    }

    public int getId() {
        return t_id;
    }

    public void setId(int t_id) {
        this.t_id = t_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
