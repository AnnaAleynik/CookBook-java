package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.services.PasswordCoder;
import ru.itis.aleynik.cookingbook.services.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("redirect_url",utils.getRedirectPath());
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html; charset=UTF-8");
        req.getRequestDispatcher("WEB-INF/views/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");


            if (email != null && password != null) {
                if (SecurityService.signIn(req, email, password)) {
                    resp.sendRedirect(getServletContext().getContextPath() + "/profile");

                } else {
                    req.setAttribute("email", req.getParameter("email"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req, resp);
                }

            } else {
                req.setAttribute("error", 1);
                resp.sendRedirect(getServletContext().getContextPath() + "/signin");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}