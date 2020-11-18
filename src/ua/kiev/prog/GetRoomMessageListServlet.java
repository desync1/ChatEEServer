package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetRoomMessageListServlet extends HttpServlet {
    ChatRoomList chatRoomList = ChatRoomList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String roomName = req.getParameter("roomname");
        String fromStr = req.getParameter("from");
        int from = 0;
        try {
            from = Integer.parseInt(fromStr);
            if (from < 0) from = 0;
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        resp.setContentType("application/json");
        ChatRoom room = chatRoomList.getRoom(roomName);
        String json = room.toJSON(from);
        if (json != null) {
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }
}
