package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.dao.UserDAO;
import ru.itis.aleynik.cookingbook.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/recipes/*")
public class RecipesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String tale = req.getPathInfo();
        String s = req.getParameter("u");
        System.out.println("uri: " + uri); // /CookBook/recipes/tags/list
        System.out.println("tale: " + tale); // /tags/list
        System.out.println("s: " + s); // /1,3  //?tags=1,3

        String action = getAction(tale);

        switch (action) {
            case "my" :
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

            case "user" :
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

            case "list" :
                String[] tags = getTags(req.getParameter("t"));

                break;
        }

    }

    private String[] getTags(String t) {
        String[] params = t.split(",");
        return params;
    }

    private String getAction(String tale) {
        String[] tales = tale.split("/");
        return tales[1];
    }
}
