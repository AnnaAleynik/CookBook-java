package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.dao.UserDAO;
import ru.itis.aleynik.cookingbook.models.Ingredient;
import ru.itis.aleynik.cookingbook.models.Recipe;
import ru.itis.aleynik.cookingbook.models.Tag;
import ru.itis.aleynik.cookingbook.models.User;
import ru.itis.aleynik.cookingbook.services.RecipeByIngredientGenerator;
import ru.itis.aleynik.cookingbook.services.RecipeByTagGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

@WebServlet("/recipes/*")
public class RecipesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tale = req.getPathInfo();

        String action = getAction(tale);


        switch (action) {
            case "my":
                User user = (User) req.getSession().getAttribute("user");
                if (user != null) {
                    UserDAO userDAO = new UserDAO();
                    try {
                        userDAO.addRecipes(user);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    req.getRequestDispatcher("/WEB-INF/views/my-recipes.jsp").forward(req, resp);
                } else {
                    req.getRequestDispatcher("/WEB-INF/views/error403.jsp").forward(req, resp);
                }
                break;

            case "user":
                String authorId = req.getParameter("u");
                if (authorId != null && !authorId.equals("")) {
                    try {
                        UserDAO userDAO = new UserDAO();
                        User author = userDAO.getUserById(Integer.parseInt(authorId));
                        userDAO.addRecipes(author);
                        req.setAttribute("author", author);
                        req.getRequestDispatcher("/WEB-INF/views/author-recipes.jsp").forward(req, resp);
                    } catch (SQLException ex) {
                        req.getRequestDispatcher("/WEB-INF/views/error404.jsp").forward(req, resp);
                    }
                } else {
                    req.getRequestDispatcher("/WEB-INF/views/error404.jsp").forward(req, resp);
                }
                break;

            case "list":
                String t = (req.getParameter("t") != null) ? req.getParameter("t") : "-1";
                String i = (req.getParameter("i") != null) ? req.getParameter("i") : "-1";
                if (t.equals("-1") && i.equals("-1")) {
                    req.getRequestDispatcher("/WEB-INF/views/error404.jsp").forward(req, resp);
                } else {
                    if (!t.equals("-1")) {
                        try {
                            int[] tags = getParams(t);
                            showByTags(tags, req, resp);
                        } catch (SQLException ex) {
                            req.getRequestDispatcher("/WEB-INF/views/error404.jsp").forward(req, resp);
                        }
                    } else {
                        System.out.println("hmm");
                        try {
                            int[] ings = getParams(req.getParameter("i"));
                            System.out.println(Arrays.toString(ings));
                            showByIngs(ings, req, resp);
                        } catch (SQLException ex) {
                            req.getRequestDispatcher("/WEB-INF/views/error404.jsp").forward(req, resp);
                        }
                    }
                }
                break;
        }

    }

    private void showByIngs(int[] ings, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("sh");
        RecipeByIngredientGenerator generator = new RecipeByIngredientGenerator();
        LinkedList<Ingredient> ingList = generator.getIngs(ings);
        System.out.println("before");
        LinkedList<Recipe> recipes = generator.getRecipeByIngs(ingList);
        System.out.println("after");
        req.setAttribute("recipes", recipes);
        req.setAttribute("ings", ingList);
        req.getRequestDispatcher("/WEB-INF/views/list_recipes.jsp").forward(req, resp);
    }

    private void showByTags(int[] tags, HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        RecipeByTagGenerator generator = new RecipeByTagGenerator();
        LinkedList<Tag> tagList = generator.getTags(tags);
        LinkedList<Recipe> recipes = generator.getRecipeByTags(tagList);
        req.setAttribute("recipes", recipes);
        req.setAttribute("tags", tagList);
        req.getRequestDispatcher("/WEB-INF/views/list_recipes.jsp").forward(req, resp);
    }

    private int[] getParams(String t) {
        String[] paramsStr = t.split(",");
        int[] params = new int[paramsStr.length];
        for (int i = 0; i < params.length; i++) {
            params[i] = Integer.parseInt(paramsStr[i].trim());
        }
        return params;
    }

    private String getAction(String tale) {
        String[] tales = tale.split("/");
        return tales[1];
    }
}
