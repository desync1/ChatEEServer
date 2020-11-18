package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

public class UserList {
    private static final UserList userList = new UserList();
    private final List<User> users = new ArrayList<>();

    public static UserList getInstance() {
        return userList;
    }

    public synchronized void add(User userAdd) {
        users.add(userAdd);
    }

    public synchronized boolean checkUserRegister(User check) {
        for (User user : users) {
            if (user.getLogin().equals(check.getLogin())) {
                return true;
            }
        }
        return false;
    }

    public synchronized User getUser(String username) {
        for (User user : users) {
            if (user.getLogin().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public synchronized boolean userLogin(User check) {
        for (User user : users) {
            if (user.getLogin().equals(check.getLogin())) {
                if (user.getPassword().equals(check.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized String getUserStatus(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user.getAvailable();
            }
        }
        return null;
    }

    public synchronized void changeUserStatus(User change, String s) {
        for (User user : users) {
            if (user.getLogin().equals(change.getLogin())) {
                user.setAvailable(s);
            }
        }
    }

    public synchronized String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

}
