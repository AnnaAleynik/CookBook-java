package ru.itis.aleynik.cookingbook.dao;

import ru.itis.aleynik.cookingbook.models.Tag;
import ru.itis.aleynik.cookingbook.services.DBWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class TagDAO {
    private Connection conn;

    public TagDAO() {
        this.conn = DBWorker.getConn();
    }

    public void destroy() throws SQLException {
        conn.close();
    }

    public Tag getTagByTitle(String title) throws SQLException {
        try {
            String command = "INSERT INTO tag (title) VALUES (?)";
            PreparedStatement st = conn.prepareStatement(command);
            st.setString(1, title);
            st.executeUpdate();
        } finally {
            String command = "SELECT t_id, title FROM tag where title LIKE ?";
            PreparedStatement st = conn.prepareStatement(command);
            st.setString(1, title);
            ResultSet set = st.executeQuery();
            Tag tag = getTagByResultSet(set);
            return tag;
        }
    }

    public LinkedList<Tag> listTagsByRecipe(int id) throws SQLException{
        String command = "select t.title, t.t_id from recipe_tag rt join tag t on rt.t_id=t.t_id  where r_id = ?";
        PreparedStatement st = conn.prepareStatement(command);
        st.setInt(1, id);
        ResultSet set = st.executeQuery();
        return getListByResultSet(set);
    }

    public LinkedList<Tag> getAllTags() throws SQLException{
//        language=SQL
        String command = "SELECT t_id, title FROM tag";
        PreparedStatement st = conn.prepareStatement(command);
        ResultSet set = st.executeQuery();
        return getListByResultSet(set);
    }

    private LinkedList<Tag> getListByResultSet(ResultSet set) throws SQLException {
        LinkedList<Tag> list = new LinkedList<>();
        while(set.next()) {
            list.add(new Tag(
                    set.getInt("t_id"),
                    set.getString("title")
            ));
        }
        return list;
    }

    private Tag getTagByResultSet(ResultSet set) throws SQLException {
        if (set.next()) {
            return new Tag(
                    set.getInt("t_id"),
                    set.getString("title")
            );
        } else {
            return null;
        }
    }
}
