package ru.itis.aleynik.cookingbook.services;

import ru.itis.aleynik.cookingbook.dao.UserDAO;
import ru.itis.aleynik.cookingbook.models.User;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class SecurityService {

    private static UserDAO userDAO = new UserDAO();

    public static User getUser(HttpServletRequest req) {
        if(isSigned(req)){
            return (User) req.getSession().getAttribute("user");
        }
        return null;
    }

    public static boolean isSigned(HttpServletRequest req) {
        return req.getSession().getAttribute("user") != null;
    }

    public static boolean signIn(HttpServletRequest req, String email, String password) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {

        User user = userDAO.getUserByEmail(email);
        password = PasswordCoder.getHash(password, user.getSalt());

        if (email.equals(user.getEmail()) && password.equals(user.getPassword())){
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("login", user.getLogin());
            req.getSession().setAttribute("user_id", user.getId());
            req.getSession().setAttribute("user", user);
            return true;
        }

        return false;
    }

    public static void signOut(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
    }
}
