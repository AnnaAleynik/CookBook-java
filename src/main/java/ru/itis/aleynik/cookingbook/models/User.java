package ru.itis.aleynik.cookingbook.models;

import ru.itis.aleynik.cookingbook.dao.UserDAO;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;

public class User {

    private static UserDAO userDAO;

    public int id;
    public String login;
    public String email;
    public String password;
    public LinkedList<Recipe> favoriteRecipes;
    public LinkedList<Recipe> addedRecipes;

    public static User userById(int id) throws SQLException {
        userDAO = new UserDAO();
        return userDAO.getUserById(id);
    }


    public User(int id, String login, String email, String password) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public User(int id, String login, String email, String password, LinkedList<Recipe> favoriteRecipe, LinkedList<Recipe> addedRecipe) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.favoriteRecipes = favoriteRecipe;
        this.addedRecipes = addedRecipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFavoriteRecipes(LinkedList<Recipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    public void setAddedRecipes(LinkedList<Recipe> addedRecipes) {
        this.addedRecipes = addedRecipes;
    }

    public LinkedList<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public LinkedList<Recipe> getAddedRecipes() {
        return addedRecipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, password, favoriteRecipes, addedRecipes);
    }
}
