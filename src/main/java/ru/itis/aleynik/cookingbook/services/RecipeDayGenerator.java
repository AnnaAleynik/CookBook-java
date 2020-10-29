package ru.itis.aleynik.cookingbook.services;

import ru.itis.aleynik.cookingbook.dao.RecipeDAO;
import ru.itis.aleynik.cookingbook.entities.Recipe;

import java.sql.SQLException;
import java.util.Random;

public class RecipeDayGenerator {

    private RecipeDAO recipeDAO;

    public RecipeDayGenerator(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    public Recipe getRandomRecipe() throws SQLException {
        int[] id = recipeDAO.getAllId();
        int randomId = new Random().nextInt(id.length);
        return recipeDAO.getRecipeById(randomId);
    }
}
