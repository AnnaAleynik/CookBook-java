package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.services.SecurityService;
import ru.itis.aleynik.cookingbook.services.Validators;
import ru.itis.aleynik.cookingbook.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HashMap<String, String> map = Validators.signUpData(req);

        if (map.get("accept").equals("1")) {
            UserDAO userDAO = new UserDAO();
            String name = map.get("login");
            String password = map.get("password");
            String email = map.get("email");
            try {
                userDAO.addUser(name, password, email);

                if (SecurityService.signIn(req, email, password)) {
                    resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                }
                userDAO.destroy();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);

            }
        } else {
            req.setAttribute("error", 1);
            resp.sendRedirect(getServletContext().getContextPath() + "/signup");

        }

    }

}
