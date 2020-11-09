package ru.itis.aleynik.cookingbook.services;

import ru.itis.aleynik.cookingbook.dao.RecipeDAO;
import ru.itis.aleynik.cookingbook.dao.TagDAO;
import ru.itis.aleynik.cookingbook.models.Recipe;
import ru.itis.aleynik.cookingbook.models.Tag;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class RecipeByTagGenerator {

    private TagDAO tagDAO;
    private RecipeDAO recipeDAO;

    public RecipeByTagGenerator() {
        this.tagDAO = new TagDAO();
        this.recipeDAO = new RecipeDAO();
    }

    public LinkedList<Tag> getTags(int[] tags) throws SQLException {
        Tag tag;
        LinkedList<Tag> tagsList = new LinkedList<>();
        for (Integer tagId: tags) {
            tag = tagDAO.getTagById(tagId);
            if (tag != null) {
                tagsList.add(tag);
//                System.out.println(tag.toString());
            }
        }
        return tagsList;
    }

    public LinkedList<Recipe> getRecipeByTags(LinkedList<Tag> tagsList) throws SQLException {
        LinkedList<Recipe> list = new LinkedList<>();

//        select * from recipe_tag rt join recipe r on r_id where t_id = ? and
//        getTagsByRecipeId().contains yes
        compare(tagsList, list);

        recipeDAO.destroy();
        tagDAO.destroy();

        return list;
    }

    private void compare(LinkedList<Tag> tagsList, LinkedList<Recipe> list) throws SQLException {
        ArrayList<Integer> recipes = recipeDAO.getAllId();
        LinkedList<Tag> tagsRecipe;

//        tagsList <= tags

        for (Integer rId: recipes) {
//            System.out.print("id: " + rId);
            tagsRecipe = tagDAO.listTagsByRecipe(rId);
            if (cont(tagsRecipe, tagsList)) {
                list.add(recipeDAO.getRecipeById(rId));
            }
        }
    }

    private boolean cont(LinkedList<Tag> tagsRecipe, LinkedList<Tag> tagsList) {
        for (Tag tag : tagsList) {
            for (Tag tagRec : tagsRecipe) {
                if (tagRec.getId() == tag.getId()) return true;
            }
        }
        return false;
    }
}
