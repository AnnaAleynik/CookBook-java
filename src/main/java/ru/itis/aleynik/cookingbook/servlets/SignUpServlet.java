package ru.itis.aleynik.cookingbook.servlets;

import ru.itis.aleynik.cookingbook.services.PasswordCoder;
import ru.itis.aleynik.cookingbook.services.SecurityService;
import ru.itis.aleynik.cookingbook.services.Validators;
import ru.itis.aleynik.cookingbook.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.HashMap;


@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/signup.jsp").forward(req, resp);
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html; charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html; charset=UTF-8");

        HashMap<String, String> map = Validators.signUpData(req);

        if (map.get("accept").equals("1")) {
            UserDAO userDAO = new UserDAO();
            String name = map.get("login");
            String salt = PasswordCoder.getSalt();
            System.out.println("salt: " + salt);
            try {
                String pass = map.get("password");
                String password = PasswordCoder.getHash(pass, salt);
                String email = map.get("email");

                userDAO.addUser(name, password, email, salt);
                userDAO.destroy();
                if (SecurityService.signIn(req, email, pass)) {
//                    System.out.println("yes");
                    resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                }
//                System.out.println("hnmm");

            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);

            }
        } else {
            req.setAttribute("error", 1);
//            System.out.println("ooo");
            resp.sendRedirect(getServletContext().getContextPath() + "/signup");

        }

    }

}
