package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.dao.RecipeDAO;
import ru.itis.aleynik.cookingbook.entities.Ingredient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

@WebServlet("/add-recipe")
public class AddRecipeServlet extends HttpServlet {

    private RecipeDAO recipeDAO = new RecipeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/add_recipe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int amount = Integer.parseInt(req.getParameter("amount"));
        LinkedList<Ingredient> list = new LinkedList();
        HashMap<Ingredient, String> map = new HashMap();
        String ing = "titleIng";
        String amountIngFrom = "amount";
//        while (ing.append(i))
        Ingredient ingredient;
        String amountIngTo;
        for (int i = 0; i < amount; i++) {
            ingredient = new Ingredient(req.getParameter(ing + i));
//            dao
            amountIngTo = req.getParameter(amountIngFrom + i);
            map.put(ingredient, amountIngTo);
        }

        if (title != null && description != null && !map.isEmpty()) {
            try {
                recipeDAO.addRecipe(title, description, map);
                recipeDAO.destroy();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
