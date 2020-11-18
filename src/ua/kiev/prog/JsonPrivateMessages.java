package ua.kiev.prog;

import java.util.ArrayList;
import java.util.List;

public class JsonPrivateMessages {
    private List<Message> list = new ArrayList<>();

    public JsonPrivateMessages(List<Message> sourceList, String to) {
        for (int i = 0; i < sourceList.size(); i++) {
            if (sourceList.get(i).getTo().equals(to)) {
                list.add(sourceList.get(i));
            }
        }
    }
}