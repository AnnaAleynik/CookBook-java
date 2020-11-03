package ru.itis.aleynik.cookingbook.dao;

import ru.itis.aleynik.cookingbook.models.*;
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
    private UserDAO userDAO;
    private TagDAO tagDAO;
    private RecipeIngredientDAO riDAO;

    public RecipeDAO() {
        this.conn = DBWorker.getConn();
    }

    public void destroy() throws SQLException {
        conn.close();
    }

    private Recipe getRecipeByResultSet(ResultSet set) throws SQLException {
        Recipe recipe;
        userDAO = new UserDAO();
        riDAO = new RecipeIngredientDAO();
        tagDAO = new TagDAO();

//        String title = set.getString("title");
        if (set.next()) {
            int id = set.getInt("r_id");
            recipe = new Recipe(
                    id,
                    set.getString("title"),
                    set.getString("description"),
                    userDAO.getUserById(set.getInt("user_id")),
                    riDAO.getListByRecipe(id),
                    tagDAO.listTagsByRecipe(id)
            );
        } else {
            recipe = new Recipe(-1, null, null, null, null, null);
        }

        userDAO.destroy();
        tagDAO.destroy();
        riDAO.destroy();
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

    public int addRecipe(int user_id, String title, String description, LinkedList<Ingredient> list) throws SQLException {
        String command = "INSERT INTO recipe (user_id, title, description) VALUES (?, ?, ?) RETURNING r_id"; //returnung id
        PreparedStatement st = conn.prepareStatement(command);
        st.setInt(1, user_id);
        st.setString(2, title);
        st.setString(3, description);
        ResultSet set = st.executeQuery();
//        addRecipeIngredient(getIdRecipeByTitle(title), list);

        int res = -1;
        if (set.next()) {
            res = set.getInt("r_id");
        }

        addRecipeIngredient(res, list);
        return res;
    }

    public int addRecipeIngredient(int r_id, LinkedList<Ingredient> list) throws SQLException {
        String command;
        PreparedStatement st;
        int res = 0;
        for (Ingredient item : list) {
            command = "INSERT INTO recipe_ingr (r_id, i_id, amount) VALUES (?, ?, ?)";
            st = conn.prepareStatement(command);
            st.setInt(1, r_id);
            st.setInt(2, item.getI_id());
            st.setString(3, item.getAmount());
            res += st.executeUpdate();
        }
        return res;
    }

    /*
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
*/

    private int getIdRecipeByTitle(String title) throws SQLException {
        String command = "SELECT r_id FROM recipe WHERE title LIKE ?";
        PreparedStatement st = conn.prepareStatement(command);
        st.setString(1, title);
        ResultSet set = st.executeQuery();
        set.next();
        return set.getInt("id");
    }

    public Recipe getRecipeById(int id) throws SQLException {
        String command = "SELECT r_id, title, description, user_id FROM recipe WHERE r_id = ?";
        PreparedStatement st = conn.prepareStatement(command);
        st.setInt(1, id);
        ResultSet set = st.executeQuery();
        return getRecipeByResultSet(set);
    }

}
