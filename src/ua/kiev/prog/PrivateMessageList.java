package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

public class PrivateMessageList {
    private static final PrivateMessageList privateMessageList = new PrivateMessageList();

    private Gson gson;
    private final List<Message> list = new LinkedList<>();

    public static PrivateMessageList getInstance() {
        return privateMessageList;
    }

   private PrivateMessageList() {
       gson = new GsonBuilder().create();
    }

    public synchronized void add(Message m) {
        list.add(m);
    }


    public synchronized String toJSON(int n, String to) {
        if (n >= list.size()) return null;
        return gson.toJson(new JsonPrivateMessages(list, to));
    }
}
