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
    public LinkedList<Recipe> favorite_recipe;
    public LinkedList<Recipe> added_recipe;

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

    public User(int id, String login, String email, String password, LinkedList<Recipe> favorite_recipe, LinkedList<Recipe> added_recipe) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.favorite_recipe = favorite_recipe;
        this.added_recipe = added_recipe;
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

    public LinkedList<Recipe> getFavorite_recipe() {
        return favorite_recipe;
    }

    public void setFavorite_recipe(LinkedList<Recipe> favorite_recipe) {
        this.favorite_recipe = favorite_recipe;
    }

    public LinkedList<Recipe> getAdded_recipe() {
        return added_recipe;
    }

    public void setAdded_recipe(LinkedList<Recipe> added_recipe) {
        this.added_recipe = added_recipe;
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
        return Objects.hash(id, login, email, password, favorite_recipe, added_recipe);
    }
}
