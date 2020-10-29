package ru.itis.aleynik.cookingbook.entities;

import ru.itis.aleynik.cookingbook.dao.UserDAO;

import java.sql.SQLException;
import java.util.LinkedList;

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
}
