package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.dao.TagDAO;
import ru.itis.aleynik.cookingbook.models.Tag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/recipes-tags")
public class TagRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/recipe-tags.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String reqTag = req.getParameter("t");
            String[] titles = reqTag.split(",");
            int[] tagsId = getTags(titles);
            String reqTo = getReq(tagsId);
            resp.sendRedirect(getServletContext().getContextPath() + reqTo);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private String getReq(int[] tagsId) {
        StringBuilder reqTo = new StringBuilder();
        reqTo.append("/recipes/list?t=");
        for (int i = 0; i < tagsId.length - 1; i++) {
            reqTo.append(tagsId[i]);
            reqTo.append(",");
        }
        reqTo.append(tagsId[tagsId.length - 1]);
        return reqTo.toString();
    }

    private int[] getTags(String[] titles) throws SQLException {
        TagDAO tagDAO = new TagDAO();
        int i = 0;
        int[] res = new int[titles.length];
        Tag tag;
        for (String title : titles) {
            tag = tagDAO.getTagByTitle(title);
            res[i] = tag.getId();
            i++;
        }
        tagDAO.destroy();
        return res;
    }
}
