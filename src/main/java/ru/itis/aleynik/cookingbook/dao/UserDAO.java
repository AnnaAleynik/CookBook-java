package ru.itis.aleynik.cookingbook.dao;

import ru.itis.aleynik.cookingbook.models.User;
import ru.itis.aleynik.cookingbook.services.DBWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection conn;
    private RecipeDAO recipeDAO;

    public UserDAO() {
        this.conn = DBWorker.getConn();
    }

    public void destroy() throws SQLException {
        conn.close();
    }

    public int addUser(String login, String password, String email, String salt) throws SQLException {
//        String command = "INSERT INTO Users (login, password, email) VALUES (?, ?, ?)";
        String command = "INSERT INTO userp (login, password, email, salt) VALUES (?, ?, ?, ?)";

        PreparedStatement st = conn.prepareStatement(command);
        st.setString(1, login);
        st.setString(2, password);
        st.setString(3, email);
        st.setString(4, salt);
        return st.executeUpdate();
    }

    private User getUserByResultSet(ResultSet set) throws SQLException {
        User user;
        if (set.next()) {
            user = new User(
                    set.getInt("id"),
                    set.getString("login"),
                    set.getString("email"),
                    set.getString("password"),
                    set.getString("salt")
            );
        } else {
//            user = new User(-1, null, null, null, n);
            user = null;
        }

//        addRecipes(user);
        return user;
    }

    public User getUserByEmail(String email) throws SQLException {
        ResultSet set = getByEmail(email);
        User user = getUserByResultSet(set);

        return user;
    }

    public void addRecipes(User user) throws SQLException {
        recipeDAO = new RecipeDAO();
        user.setFavoriteRecipes(recipeDAO.getListFavByUser(user.getId()));
        user.setAddedRecipes(recipeDAO.getListAddedByUser(user.getId()));
        recipeDAO.destroy();
    }


    private ResultSet getByEmail(String email) throws SQLException {
//        String command = "SELECT * FROM Users WHERE email LIKE ?";
        String command = "SELECT * FROM userp WHERE email = ?";

        PreparedStatement st = conn.prepareStatement(command);
        st.setString(1, email);
        ResultSet set = st.executeQuery();
        return set;
    }

    public User getUserById(int id) throws SQLException {
        String command = "SELECT * FROM userp WHERE id = ? ";
        PreparedStatement st = conn.prepareStatement(command);
        st.setInt(1, id);
        ResultSet set = st.executeQuery();
        return getUserByResultSet(set);
    }

    public boolean addFavRecipes(int id, int r_id) throws SQLException {
//        language=SQL
        String command = "INSERT INTO userp_recipe (u_id, r_id) VALUES (?, ?)";
        PreparedStatement st = conn.prepareStatement(command);
        st.setInt(1, id);
        st.setInt(2, r_id);
        if (st.executeUpdate() > 0) {
            return true;
        }

        return false;
    }

    public boolean containsFav(int id, int r_id) throws SQLException {
//        language=SQL
        String command = "SELECT 1 AS res FROM userp_recipe WHERE u_id = ? AND r_id = ?";
        PreparedStatement st = conn.prepareStatement(command);
        st.setInt(1, id);
        st.setInt(2, r_id);

        return st.executeQuery().next();
    }

    public boolean deleteFavRecipes(int id, int r_id) throws SQLException {
        String command = "DELETE FROM recipe WHERE r_id=? AND u_id=?";
        PreparedStatement st = conn.prepareStatement(command);
        st.setInt(2, id);
        st.setInt(1, r_id);

        return st.execute();
    }
}
