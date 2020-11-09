package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.models.Recipe;
import ru.itis.aleynik.cookingbook.services.MenuDayGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

@WebServlet("/day-menu")
public class DayMenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MenuDayGenerator generator = new MenuDayGenerator();
        try {
            LinkedList<Recipe> list = generator.getMenuDay();
            req.setAttribute("recipes", list);
            req.getRequestDispatcher("/WEB-INF/views/list_recipes.jsp").forward(req, resp);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
