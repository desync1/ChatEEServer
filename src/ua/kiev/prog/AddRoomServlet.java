package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AddRoomServlet extends HttpServlet {
    ChatRoomList chatRoomList = ChatRoomList.getInstance();
    UserList userList = UserList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = Helper.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        Message message = Message.fromJSON(bufStr);
        String userName = message.getFrom();
        String roomName = message.getTo();
        ChatRoom room = chatRoomList.getRoom(roomName);
        User user = userList.getUser(userName);
        if (room != null && message != null) {
            room.addUser(user);
            room.msgAdd(message);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
 }
}
