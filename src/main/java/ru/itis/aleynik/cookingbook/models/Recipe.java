package ru.itis.aleynik.cookingbook.models;

import java.util.LinkedList;

public class Recipe {

    public int r_id;
    public String title;
    public String description;
    public User author;
    public LinkedList<Ingredient> ingredients;
    public LinkedList<Tag> tags;

    public Recipe() {
    }

    public Recipe(int r_id, String title, String description, User author, LinkedList<Ingredient> ingredients, LinkedList<Tag> tags) {
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

    public int getId() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LinkedList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(LinkedList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public LinkedList<Tag> getTags() {
        return tags;
    }

    public void setTags(LinkedList<Tag> tags) {
        this.tags = tags;
    }
}

