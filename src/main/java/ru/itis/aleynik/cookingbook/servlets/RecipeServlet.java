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

        int r_id = getId(req.getRequestURI());
        if (r_id != -1) {
            try {
                RecipeDAO recipeDAO = new RecipeDAO();
                Recipe recipe = recipeDAO.getRecipeById(r_id);
                if (recipe != null && recipe.getId() != -1) {
                    req.setAttribute("recipe", recipe);
                } else {
                    req.getRequestDispatcher("/WEB-INF/views/error404.jsp").forward(req, resp);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/recipe.jsp").forward(req, resp);

    }

    private int getId(String uri) throws NumberFormatException {
        String[] arr = uri.split("/");
        int res = -1;
//        System.out.println(Arrays.toString(arr));
//        String last = arr[arr.length-1].trim().replaceAll("[^0-9]", "");
//        System.out.println(last);
//        int res = Integer.parseInt(last);
        try {
            res = Integer.valueOf(arr[arr.length - 1]);
        } catch (NumberFormatException e) {
            return res;
        }
        return res;
    }
}
