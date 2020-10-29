package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.services.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SecurityService.signOut(req);
        resp.sendRedirect(getServletContext().getContextPath() + "/");
    }
}
