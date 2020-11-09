package ru.itis.aleynik.cookingbook.services;

import ru.itis.aleynik.cookingbook.dao.IngredientDAO;
import ru.itis.aleynik.cookingbook.dao.RecipeDAO;
import ru.itis.aleynik.cookingbook.dao.RecipeIngredientDAO;
import ru.itis.aleynik.cookingbook.dao.TagDAO;
import ru.itis.aleynik.cookingbook.models.Ingredient;
import ru.itis.aleynik.cookingbook.models.Recipe;
import ru.itis.aleynik.cookingbook.models.Tag;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class RecipeByIngredientGenerator {

    private IngredientDAO ingDAO;
    private RecipeDAO recipeDAO;
    private RecipeIngredientDAO riDAO;

    public RecipeByIngredientGenerator() {
        this.ingDAO = new IngredientDAO();
        this.recipeDAO = new RecipeDAO();
        this.riDAO = new RecipeIngredientDAO();
    }


    public LinkedList<Ingredient> getIngs(int[] ings) throws SQLException {
        Ingredient ing;
        LinkedList<Ingredient> ingsList = new LinkedList<>();
        for (Integer ingId : ings) {
            ing = ingDAO.getIngById(ingId);
            System.out.println("ing " + ing);
            if (ing != null) {
                ingsList.add(ing);
            }
        }

        return ingsList;
    }

    public LinkedList<Recipe> getRecipeByIngs(LinkedList<Ingredient> ingList) throws SQLException {
        LinkedList<Recipe> list = new LinkedList<>();
        compare(ingList, list);

        riDAO.destroy();
        ingDAO.destroy();
        recipeDAO.destroy();
        return list;
    }

    private void compare(LinkedList<Ingredient> ingList, LinkedList<Recipe> list) throws SQLException {
        ArrayList<Integer> recipes = recipeDAO.getAllId();
        LinkedList<Ingredient> ingsRecipe;
        for (Integer rId : recipes) {
            System.out.print("rId " + rId);
            ingsRecipe = riDAO.getListByRecipe(rId);
            if (cont(ingsRecipe, ingList)) {
                list.add(recipeDAO.getRecipeById(rId));
            }
        }
    }

//    ingsRecipe <= ingList

    private boolean cont(LinkedList<Ingredient> ingsRecipe, LinkedList<Ingredient> ingsList) {
        for (Ingredient ingRec : ingsRecipe) {
            if (!recipeContIng(ingRec, ingsList)) {
                return false;
            }
        }
        return true;
    }

    private boolean recipeContIng(Ingredient ingRec, LinkedList<Ingredient> ingsList) {
        for (Ingredient ing : ingsList) {
            System.out.println("ingrec " + ingRec.getId() + " ing " + ing.getId());
            if (ingRec.getId() == ing.getId()) return true;
        }
        return false;
    }
}
