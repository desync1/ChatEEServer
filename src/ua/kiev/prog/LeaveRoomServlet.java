package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LeaveRoomServlet extends HttpServlet {

    private ChatRoomList chatRoomList = ChatRoomList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomName = req.getParameter("roomname");
        String userName = req.getParameter("username");
        chatRoomList.leaveUser(userName, roomName);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
