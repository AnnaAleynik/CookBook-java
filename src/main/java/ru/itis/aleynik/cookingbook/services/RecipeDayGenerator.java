package ru.itis.aleynik.cookingbook.services;

import ru.itis.aleynik.cookingbook.dao.RecipeDAO;
import ru.itis.aleynik.cookingbook.models.Recipe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class RecipeDayGenerator {

    private RecipeDAO recipeDAO;

    public RecipeDayGenerator() {
        this.recipeDAO = new RecipeDAO();
    }

    public Recipe getRandomRecipe() {
        try {
            /*
            ArrayList<Integer> id = recipeDAO.getAllId();
//        int randomId = new Random().nextInt(id.length);
//        return recipeDAO.getRecipeById(randomId);
            int res =
            */
            int r = recipeDAO.getRandId();
            Recipe recipe;
            if (r > 0) {
                recipe =  recipeDAO.getRecipeById(r);
            } else {
                recipe = null;
            }
            recipeDAO.destroy();
            return recipe;
        } catch (SQLException ex) {
            return null;
        }
    }
}
