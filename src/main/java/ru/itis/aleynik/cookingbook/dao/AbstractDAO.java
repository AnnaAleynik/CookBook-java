package ru.itis.aleynik.cookingbook.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class AbstractDAO {

    private Connection conn;



    public void destroy() throws SQLException {
        conn.close();
    }
}
