package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.models.Recipe;
import ru.itis.aleynik.cookingbook.services.RecipeDayGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/day-recipe")
public class DayRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RecipeDayGenerator generator = new RecipeDayGenerator();
        Recipe recipe = generator.getRandomRecipe();
        if (recipe != null) {
            req.setAttribute("recipe", recipe);
            getServletContext().getRequestDispatcher("/WEB-INF/views/recipe.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }

    }

}
