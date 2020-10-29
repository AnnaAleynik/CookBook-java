package ru.itis.aleynik.cookingbook.dao;

import ru.itis.aleynik.cookingbook.models.Ingredient;
import ru.itis.aleynik.cookingbook.services.DBWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientDAO {

    private Connection conn;

    public IngredientDAO() {
        this.conn = DBWorker.getConn();
    }

    public void destroy() throws SQLException {
        conn.close();
    }

    private Ingredient getIngByResultSet(ResultSet set) throws SQLException {
        Ingredient ing;
        if (set.next()) {
            ing = new Ingredient(
                    set.getInt("i_id"),
                    set.getString("title")
            );
        } else {
            ing = null;
        }
        return ing;
    }

    public Ingredient getIngByTitle(String title) throws SQLException {
        try {
            String command = "INSERT INTO ingredient (title) VALUES (?)";
            PreparedStatement st = conn.prepareStatement(command);
            st.setString(1, title);
            st.executeUpdate();
        } finally {
            String command = "SELECT i_id, title FROM ingredient where title LIKE ?";
            PreparedStatement st = conn.prepareStatement(command);
            st.setString(1, title);
            ResultSet set = st.executeQuery();
            Ingredient ing = getIngByResultSet(set);
            return ing;
        }
    }
}
