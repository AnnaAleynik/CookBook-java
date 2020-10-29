package ru.itis.aleynik.cookingbook.models;

import java.util.LinkedList;

public class Recipe {

    public int r_id;
    public String title;
    public String description;
    public User author;
    public LinkedList<RecipeIngredient> ingredients;
    public LinkedList<Tag> tags;

    public Recipe() {
    }

    public Recipe(int r_id, String title, String description, User author, LinkedList<RecipeIngredient> ingredients, LinkedList<Tag> tags) {
        this.r_id = r_id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.ingredients = ingredients;
        this.tags = tags;
    }

    public Recipe(int r_id, String title, String description) {
        this.r_id = r_id;
        this.title = title;
        this.description = description;
    }
}
