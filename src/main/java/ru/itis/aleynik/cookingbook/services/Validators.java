package ru.itis.aleynik.cookingbook.services;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class Validators {

    public static HashMap<String, String> signUpData(HttpServletRequest req) {

        String name = req.getParameter("login");
        boolean nameB = true;
        boolean passB = true;
        boolean mailB = true;
        boolean acceptB = true;

        if (!name.matches("[A-Za-z0-9_.]*")) {
            name = "null";
//            System.out.println("name");
            nameB = false;
        }

        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
//        System.out.println("pass: " + password + " pass2: " + password2);

        if (!password.equals(password2)) {
            password = "null";
//            System.out.println("pass");
            passB = false;
        }

        String mail = req.getParameter("email");
        if (!mail.matches("[a-zA-Z\\-0-9\\.]+@([a-zA-Z\\-0-9]+\\.([a-z\\-])+)+")) {
//            System.out.println("mail");
            mail = "null";
            mailB = false;
        }

        String accept = req.getParameter("accept");

        if (accept == null) {
            acceptB = false;
//            System.out.println(accept);

        }

        HashMap<String, String> map = new HashMap<>();
        if (nameB && passB && mailB) {
            map.put("accept", "1");
            map.put("login", name);
            map.put("password", password);
            map.put("email", mail);
//            System.out.println(name + " " + password + " " + mail);
        } else {
            map.put("accept", "0");
            map.put("help", accept);
            System.out.println("jjjjj");
        }

        return map;
    }
}
