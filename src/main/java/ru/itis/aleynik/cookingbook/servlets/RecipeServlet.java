package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.dao.RecipeDAO;
import ru.itis.aleynik.cookingbook.dao.UserDAO;
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
import java.util.LinkedList;

@WebServlet("/recipe/*")
public class RecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html; charset=UTF-8");
        String uri = req.getRequestURI();
        String tale = req.getPathInfo();
//        System.out.println(tale);
        String[] path = tale.split("/");
//        System.out.println(Arrays.toString(path));
//        System.out.println(req.getParameter("id")); ///edit?id=12 -> 12

        int r_id = getId(path[1]);
//        System.out.println(r_id);
        String action = "";
        if (path.length > 2) {
            action = path[2];
        }

//        int r_id = getId(req.getRequestURI());
        if (r_id != -1) {
            try {
                RecipeDAO recipeDAO = new RecipeDAO();
                Recipe recipe = recipeDAO.getRecipeById(r_id);
                recipeDAO.destroy();
                if (recipe != null && recipe.getId() != -1) {
                    req.setAttribute("recipe", recipe);
                }
                switch (action) {
                    case "":
                        show(req, resp, r_id);
                        break;
                    case "edit":
                        getServletContext().getRequestDispatcher("/WEB-INF/views/edit_recipe.jsp").forward(req, resp);
                        break;
                    case "delete":
                        delete(req, resp, r_id);
                        break;
                    case "add-fav":
                        addFav(req, r_id);
                        resp.sendRedirect(getServletContext().getContextPath() + "/recipe/" + r_id);
                        break;
                    case "delete-fav":
                        deleteFav(req, r_id);
                        resp.sendRedirect(getServletContext().getContextPath() + "/recipe/" + r_id);
                        break;
                    case "edit-ingredients" :
                        getServletContext().getRequestDispatcher("/WEB-INF/views/edit_ing.jsp").forward(req, resp);
                        break;

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            req.getRequestDispatcher("/WEB-INF/views/error404.jsp").forward(req, resp);
        }

    }

    private boolean deleteFav(HttpServletRequest req, int r_id) throws SQLException{
        UserDAO userDAO = new UserDAO();
        User user = (User) req.getSession().getAttribute("user");
        boolean res = userDAO.deleteFavRecipes(user.getId(), r_id);
        userDAO.destroy();
        return res;
    }

    private boolean addFav(HttpServletRequest req, int r_id) throws SQLException {
        UserDAO userDAO = new UserDAO();
        User user = (User) req.getSession().getAttribute("user");
        boolean res = userDAO.addFavRecipes(user.getId(), r_id);
        userDAO.destroy();
        return res;
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp, int r_id) throws SQLException, IOException {
        RecipeDAO recipeDAO = new RecipeDAO();
        boolean res = recipeDAO.deleteRecipeById(r_id);
        recipeDAO.destroy();
        resp.sendRedirect(getServletContext().getContextPath() + "/my-recipes");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html; charset=UTF-8");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int r_id = Integer.parseInt(req.getParameter("r_id"));

        try {
            RecipeDAO recipeDAO = new RecipeDAO();
            boolean res = recipeDAO.updateRecipeById(r_id, title, description);
            recipeDAO.destroy();
            resp.sendRedirect(getServletContext().getContextPath() + "/recipe/" + r_id);
        } catch (SQLException ex) {
            req.getRequestDispatcher("/WEB-INF/views/error404.jsp").forward(req, resp);
        }
    }


    private void show(HttpServletRequest req, HttpServletResponse resp, int r_id) throws ServletException, IOException, SQLException {
        User user = (User) req.getSession().getAttribute("user");
//        UserDAO userDAO = new UserDAO();
//        boolean contains = userDAO.containsFav(user.getId(), r_id);
//        req.getSession().setAttribute();
//        userDAO.destroy();
        if (user != null) {
            LinkedList<Integer> r_idList = new LinkedList<>();
//            LinkedList<Recipe> recipes = user.getFavoriteRecipesId();

            req.getSession().setAttribute("fav_recipes", user.getFavoriteRecipes());
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/recipe.jsp").forward(req, resp);

    }


    private int getId(String arr) throws NumberFormatException {
        int res = -1;
        try {
            res = Integer.valueOf(arr);
        } catch (NumberFormatException e) {
            return res;
        }
        return res;
    }
}
