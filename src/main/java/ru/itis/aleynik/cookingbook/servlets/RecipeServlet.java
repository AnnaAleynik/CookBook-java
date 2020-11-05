package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.dao.RecipeDAO;
import ru.itis.aleynik.cookingbook.models.Recipe;
import ru.itis.aleynik.cookingbook.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/recipe/*")
public class RecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String tale = req.getPathInfo();
//        System.out.println(tale);
        String[] path = tale.split("/");
//        System.out.println(Arrays.toString(path));
//        System.out.println(req.getParameter("id")); ///edit?id=12 -> 12

        int r_id = getId(path[1]);
//        System.out.println(r_id);
        String action = "";
        if (path.length > 2) {
            action = path[2];
        }

//        int r_id = getId(req.getRequestURI());
        if (r_id != -1) {
            try {
                RecipeDAO recipeDAO = new RecipeDAO();
                Recipe recipe = recipeDAO.getRecipeById(r_id);
                if (recipe != null && recipe.getId() != -1) {
                    req.setAttribute("recipe", recipe);
                }
                switch (action) {
                    case "":
                        show(req, resp, recipe);
                        break;
                    case "edit":
                        getServletContext().getRequestDispatcher("/WEB-INF/views/edit_recipe.jsp").forward(req, resp);
                        break;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            req.getRequestDispatcher("/WEB-INF/views/error404.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void show(HttpServletRequest req, HttpServletResponse resp, Recipe r) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/views/recipe.jsp").forward(req, resp);

    }


    private int getId(String arr) throws NumberFormatException {
        int res = -1;
        try {
            res = Integer.valueOf(arr);
        } catch (NumberFormatException e) {
            return res;
        }
        return res;
    }
}
