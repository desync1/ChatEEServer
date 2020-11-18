package ua.kiev.prog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

    private MessageList msgList = MessageList.getInstance();
    private PrivateMessageList privateMessageList = PrivateMessageList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = Helper.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        Message msg = Message.fromJSON(bufStr);

        if (msg != null && msg.getTo() == null) {
            msgList.add(msg);
        } else if (msg != null && msg.getTo() != null) {
            privateMessageList.add(msg);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


}
