package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChatRoom {
    private String name;
    private User creator;
    private List<User> users = new ArrayList<>();
    private List<Message> messages = new LinkedList<>();

    public ChatRoom(String name, User creator) {
        this.name = name;
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public boolean addUser(User userEnter) {
        for (User user : users) {
            if (user.getLogin().equals(userEnter.getLogin())) {
                return false;
            }
        }
        users.add(userEnter);
        return true;
    }

    public synchronized void userDelete(String userName) {
        for (User user : users) {
            if (user.getLogin().equals(userName)) {
                users.remove(user);
                break;
            }
        }
    }


    public void msgAdd(Message message) {
        messages.add(message);
    }

    public String toJSON(int n) {
        Gson gson = new GsonBuilder().create();
        if (n >= messages.size()) return null;
        return gson.toJson(new JsonMessages(messages, n));
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static ChatRoom fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, ChatRoom.class);
    }
}
