package ru.itis.aleynik.cookingbook.models;

import java.util.LinkedList;

public class Tag {

    public int t_id;
    public String title;

    public Tag(int t_id, String title) {
        this.t_id = t_id;
        this.title = title;
    }


    public static LinkedList<Tag> listTagsByRecipeTitle(String title) {
        return new LinkedList<>();
    }
}
