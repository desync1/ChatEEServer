package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CreateChatRoomServlet extends HttpServlet {

    ChatRoomList chatRoomList = ChatRoomList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = Helper.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        ChatRoom room = ChatRoom.fromJSON(bufStr);
        if (room != null) {
            chatRoomList.add(room);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
