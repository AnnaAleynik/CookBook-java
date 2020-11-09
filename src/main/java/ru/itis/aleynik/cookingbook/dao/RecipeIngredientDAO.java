package ru.itis.aleynik.cookingbook.dao;

import ru.itis.aleynik.cookingbook.models.Ingredient;
import ru.itis.aleynik.cookingbook.services.DBWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class RecipeIngredientDAO {

    private Connection conn;

    public RecipeIngredientDAO() {
        this.conn = DBWorker.getConn();
    }

    public void destroy() throws SQLException {
        conn.close();
    }

    public LinkedList<Ingredient> getListByRecipe(int id) throws SQLException {
        String command = "select i.title, ri.amount, i.i_id from recipe_ingr ri join ingredient i on ri.i_id=i.i_id  where r_id = ?";
        PreparedStatement st = conn.prepareStatement(command);
        st.setInt(1, id);
        ResultSet set = st.executeQuery();
        return getIngListByResultSet(set);
    }

    private LinkedList<Ingredient> getIngListByResultSet(ResultSet set) throws SQLException {
        LinkedList<Ingredient> list = new LinkedList<>();
        Ingredient ing;
        while (set.next()) {
            ing = new Ingredient(
                    set.getInt("i_id"),
                    set.getString("title"),
                    set.getString("amount")
            );
            list.add(ing);
        }
        return list;
    }

}
