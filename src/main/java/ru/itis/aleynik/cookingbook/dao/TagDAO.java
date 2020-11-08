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

    public LinkedList<Tag> listTagsByRecipe(int id) {
        return new LinkedList<>();
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
            list.add(getTagByResultSet(set));
        }
        return list;
    }

    private Tag getTagByResultSet(ResultSet set) throws SQLException {
        return new Tag(
            set.getInt("t_id"),
            set.getString("title")
        );
    }
}
