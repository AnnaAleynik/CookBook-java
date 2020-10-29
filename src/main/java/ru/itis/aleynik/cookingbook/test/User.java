package ru.itis.aleynik.cookingbook.test;

public class User {
    public String name;
    public String email;

    private static UserDAO userDAO;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static User userByEmail(String email) {
        userDAO = new UserDAO();
        return userDAO.getByEmail(email);

    }

    public static void main(String[] args) {
        User user = User.userByEmail("as");
        System.out.println(user.name);
        System.out.println(user.email);
    }
}
