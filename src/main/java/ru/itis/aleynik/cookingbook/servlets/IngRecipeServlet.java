package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.dao.IngredientDAO;
import ru.itis.aleynik.cookingbook.dao.TagDAO;
import ru.itis.aleynik.cookingbook.models.Ingredient;
import ru.itis.aleynik.cookingbook.models.Tag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/recipes-ingredients")
public class IngRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/recipe-ings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String reqTag = req.getParameter("t");
            String[] titles = reqTag.split(",");
            int[] tagsId = getIngs(titles);
            String reqTo = getReq(tagsId);
            resp.sendRedirect(getServletContext().getContextPath() + reqTo);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private String getReq(int[] ingsId) {
        StringBuilder reqTo = new StringBuilder();
        reqTo.append("/recipes/list?i=");
        for (int i = 0; i < ingsId.length - 1; i++) {
            reqTo.append(ingsId[i]);
            reqTo.append(",");
        }
        reqTo.append(ingsId[ingsId.length - 1]);
        return reqTo.toString();
    }

    private int[] getIngs(String[] titles) throws SQLException {
        IngredientDAO ingDAO = new IngredientDAO();
        int i = 0;
        int[] res = new int[titles.length];
        Ingredient ing;
        for (String title : titles) {
            ing = ingDAO.getIngByTitle(title.trim());
            res[i] = ing.getId();
            i++;
        }
        ingDAO.destroy();
        return res;

    }
}
