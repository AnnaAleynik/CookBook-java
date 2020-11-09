package ru.itis.aleynik.cookingbook.services;

import ru.itis.aleynik.cookingbook.dao.RecipeDAO;
import ru.itis.aleynik.cookingbook.dao.TagDAO;
import ru.itis.aleynik.cookingbook.models.Recipe;
import ru.itis.aleynik.cookingbook.models.Tag;

import java.sql.SQLException;
import java.util.LinkedList;

public class MenuDayGenerator {
    private RecipeDAO recipeDAO;
    private TagDAO tagDAO;

    public MenuDayGenerator() {
        recipeDAO = new RecipeDAO();
        tagDAO = new TagDAO();
    }

    public LinkedList<Recipe> getMenuDay() throws SQLException {
        Recipe recipe1 = getRecipe("завтрак");
        Recipe recipe2 = getRecipe("обед");
        Recipe recipe3 = getRecipe("ужин");

        LinkedList<Recipe> res = new LinkedList<>();
        res.add(recipe1);
        res.add(recipe2);
        res.add(recipe3);
        return res;
    }

    private Recipe getRecipe(String key) throws SQLException {
        Tag tag = tagDAO.getTagByTitle(key);
        return recipeDAO.getRandRecipeByTag(tag);
    }
}
