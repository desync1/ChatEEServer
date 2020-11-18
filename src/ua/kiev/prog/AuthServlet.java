package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AuthServlet extends HttpServlet {
    private UserList userList = UserList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buffer = Helper.requestBodyToArray(req);
        String bufferString = new String(buffer, StandardCharsets.UTF_8);
        User user = User.fromJSON(bufferString);
        if (userList.checkUserRegister(user)) {
            if (userList.userLogin(user)) {
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            userList.add(user);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
