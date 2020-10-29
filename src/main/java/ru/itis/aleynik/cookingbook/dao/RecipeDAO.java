package ru.itis.aleynik.cookingbook.dao;

import ru.itis.aleynik.cookingbook.entities.*;
import ru.itis.aleynik.cookingbook.services.DBWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class RecipeDAO {

    private Connection conn;

    public RecipeDAO() {
        this.conn = DBWorker.getConn();
    }

    public void destroy() throws SQLException {
        conn.close();
    }

    private Recipe getRecipeByResultSet(ResultSet set) throws SQLException {
        Recipe recipe;
        String title = set.getString("title");
        if (set.next()) {
            recipe = new Recipe(
                    set.getInt("id"),
                    title,
                    set.getString("description"),
                    User.userById(set.getInt("user_id")),
                    null,
                    Tag.listTagsByRecipeTitle(title)
            );
        } else {
            recipe = new Recipe(-1, null, null, null, null, null);
        }
        return recipe;
    }

    public Recipe getRecipeByTitle(String title) throws SQLException {
        String command = "SELECT r_id, user_id, description FROM recipe WHERE title LIKE ?";
        PreparedStatement st = conn.prepareStatement(command);
        st.setString(1, title);
        ResultSet set = st.executeQuery();

//        todo join
        Recipe recipe = getRecipeByResultSet(set);
        return recipe;
    }

    public int addRecipe(String title, String description, HashMap<Ingredient, String> map) throws SQLException {
        String command = "INSERT INTO recipe (title, description) VALUES (?, ?)";
        PreparedStatement st = conn.prepareStatement(command);
        st.setString(1, title);
        st.setString(2, description);
        int res = st.executeUpdate();
        addRecipeIngredient(getIdRecipeByTitle(title), map);
        return res;
    }

    public int addRecipeIngredient(int r_id, HashMap<Ingredient, String> map) throws SQLException {
        String command;
        PreparedStatement st;
        Set<Ingredient> set = map.keySet();
        int res = 0;
        for (Ingredient item: set) {
            command = "INSERT INTO recipe_ingr (r_id, i_id, amount) VALUES (?, ?, ?)";
            st = conn.prepareStatement(command);
            st.setInt(1, r_id);
            st.setInt(2, item.i_id);
            st.setString(3, map.get(item));
            res += st.executeUpdate();
        }
//        map.forEach((k,v) -> );
        return res;
    }


    private int getIdRecipeByTitle(String title) throws SQLException {
        String command = "SELECT r_id FROM recipe WHERE title LIKE ?";
        PreparedStatement st = conn.prepareStatement(command);
        st.setString(1, title);
        ResultSet set = st.executeQuery();
        set.next();
        return set.getInt("id");
    }

    public Recipe getRecipeById(int id) throws SQLException {
        String command = "SELECT r_id FROM recipe WHERE id LIKE ?";
        PreparedStatement st = conn.prepareStatement(command);
        st.setInt(1, id);
        ResultSet set = st.executeQuery();
        return getRecipeByResultSet(set);
    }


/*
    private Recipe getRecipeByTitle(String title) throws SQLException {
        String command = "SELECT * FROM recipe WHERE title LIKE ?";
        PreparedStatement st = conn.prepareStatement(command);
        st.setString(1, title);
        ResultSet set = st.executeQuery();
        set.next();
        return new Recipe(
                set.getInt("id"),
                set.getString("title"),
                set.getString("description")
                );
    }*/
}
