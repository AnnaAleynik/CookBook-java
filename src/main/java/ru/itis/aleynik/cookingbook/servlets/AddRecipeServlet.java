package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.dao.IngredientDAO;
import ru.itis.aleynik.cookingbook.dao.RecipeDAO;
import ru.itis.aleynik.cookingbook.models.Ingredient;
import ru.itis.aleynik.cookingbook.models.User;

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
    private IngredientDAO ingDAO = new IngredientDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/add_recipe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        HashMap<Ingredient, String> map = new HashMap();
        LinkedList<Ingredient> list = new LinkedList<>();
        try {
            putToMap(map, req);
            addtoList(list, req);

            if (title != null && description != null && !list.isEmpty()) {

                recipeDAO.addRecipe(user.id, title, description, list);
                recipeDAO.destroy();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    private void addtoList(LinkedList<Ingredient> list, HttpServletRequest req) throws SQLException {
        ingDAO = new IngredientDAO();
        int amount = Integer.parseInt(req.getParameter("amount"));
        String ing = "titleIng";
        String amountIngFrom = "amount";
        System.out.println("amount" + amount);
        Ingredient ingredient;
        String amountIngTo;
        for (int i = 0; i < amount; i++) {
            ingredient = ingDAO.getIngByTitle(req.getParameter(ing + i));
            amountIngTo = req.getParameter(amountIngFrom + i);
            ingredient.setAmount(amountIngTo);
            list.add(ingredient);
        }
    }

    private void putToMap(HashMap<Ingredient, String> map, HttpServletRequest req) throws SQLException {
        ingDAO = new IngredientDAO();
        int amount = Integer.parseInt(req.getParameter("amount"));
        String ing = "titleIng";
        String amountIngFrom = "amount";
        Ingredient ingredient;
        String amountIngTo;
        for (int i = 0; i < amount; i++) {
            ingredient = ingDAO.getIngByTitle(req.getParameter(ing + i));
            amountIngTo = req.getParameter(amountIngFrom + i);
            map.put(ingredient, amountIngTo);
        }
    }
}
