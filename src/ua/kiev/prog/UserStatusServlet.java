package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UserStatusServlet extends HttpServlet {
    UserList userList = UserList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = User.fromJSON(req.getParameter("user"));
        String status = req.getParameter("status");
        if (status.equalsIgnoreCase("available") || status.equalsIgnoreCase("notavailable")) {
            userList.changeUserStatus(user, status);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("user");
        String res = userList.getUserStatus(login);
        if (res.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            OutputStream os = resp.getOutputStream();
            byte[] buf = res.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }
}
