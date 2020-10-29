package ru.itis.aleynik.cookingbook.dao;

import ru.itis.aleynik.cookingbook.models.User;
import ru.itis.aleynik.cookingbook.services.DBWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection conn;

    public UserDAO() {
        this.conn = DBWorker.getConn();
    }

    public void destroy() throws SQLException {
        conn.close();
    }

    public int addUser(String login, String password, String email) throws SQLException {
//        String command = "INSERT INTO Users (login, password, email) VALUES (?, ?, ?)";
        String command = "INSERT INTO userp (login, password, email) VALUES (?, ?, ?)";

        PreparedStatement st = conn.prepareStatement(command);
        st.setString(1, login);
        st.setString(2, password);
        st.setString(3, email);
        return st.executeUpdate();
    }

    private User getUserByResultSet(ResultSet set) throws SQLException {
        User user;
        if (set.next()) {
            user = new User(
                    set.getInt("id"),
                    set.getString("login"),
                    set.getString("email"),
                    set.getString("password")
            );
        } else {
            user = new User(-1, null, null, null);
        }
        return user;
    }

    public User getUserByEmail(String email) throws SQLException {
        ResultSet set = getByEmail(email);
        return getUserByResultSet(set);
    }

    private ResultSet getByEmail(String email) throws SQLException {
//        String command = "SELECT * FROM Users WHERE email LIKE ?";
        String command = "SELECT * FROM userp WHERE email = ?";

        PreparedStatement st = conn.prepareStatement(command);
        st.setString(1, email);
        ResultSet set = st.executeQuery();
        return set;
    }

    public User getUserById(int id) throws SQLException{
        String command = "SELECT * FROM userp WHERE id = ? ";
        PreparedStatement st = conn.prepareStatement(command);
        st.setInt(1, id);
        ResultSet set = st.executeQuery();
        return getUserByResultSet(set);
    }

}
