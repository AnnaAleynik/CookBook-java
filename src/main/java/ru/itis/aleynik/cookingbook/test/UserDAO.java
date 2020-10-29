package ru.itis.aleynik.cookingbook.test;

public class UserDAO {
    public int uid;

    public UserDAO() {
        uid = 1;
    }

    public User getByEmail(String email) {
        User user = new User(uid++ + "re", email + uid);
        return user;
    }
}
