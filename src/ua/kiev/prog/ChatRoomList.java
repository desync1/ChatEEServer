package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomList {
    private static final ChatRoomList chatRoomList = new ChatRoomList();
    private final List<ChatRoom> list = new ArrayList<>();

    public static ChatRoomList getInstance() {
        return chatRoomList;
    }

    public synchronized void leaveUser(String userName, String roomName) {
        for (ChatRoom room : list) {
            if (room.getName().equals(roomName)) {
                room.userDelete(userName);
                break;
            }
        }
    }


    public synchronized ChatRoom getRoom(String nameRoom) {
        for (ChatRoom room : list) {
            if (room.getName().equals(nameRoom)) {
                return room;
            }
        }
        return null;
    }

    public synchronized void add(ChatRoom m) {
        list.add(m);
    }


    public synchronized boolean delete(String room, String user) {
        ChatRoom chat = chatRoomList.getRoom(room);
        if (chat.getCreator().getLogin().equals(user)) {
            for (ChatRoom chatRoom : list) {
                if (chatRoom.getName().equals(chat.getName())) {
                    list.remove(chatRoom);
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
