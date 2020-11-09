package ru.itis.aleynik.cookingbook.models;

import java.util.LinkedList;
import java.util.Objects;

public class Recipe {

    private int r_id;
    private String title;
    private String description;
    private User author;
    private LinkedList<Ingredient> ingredients;
    private LinkedList<Tag> tags;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return r_id == recipe.r_id &&
                Objects.equals(title, recipe.title) &&
                Objects.equals(description, recipe.description) &&
                Objects.equals(author, recipe.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(r_id, title, description, author);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "r_id=" + r_id +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}

