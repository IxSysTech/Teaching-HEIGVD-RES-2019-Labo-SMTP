import model.mail.*;
import smtp.*;
import config.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MailRobot {
    public static void main(String[] args) {
        SMTPClient client = new SMTPClient("127.0.0.1",2525);
        ConfigurationManager cm = new ConfigurationManager();
        Groups groups = new Groups(cm.getVictims());

        groups.createGroups(4);
        List<Group> prankGroup = groups.getGroups();
        List<Message> allMessages = new ArrayList<Message>();

        for (String key : cm.getMessages().keySet()) {
            Message m = new Message(prankGroup.get(1), key, cm.getMessages().get(key));
            allMessages.add(m);
        }

        if(prankGroup != null) {
            for (Group g : prankGroup) {
                g.displayMembers();
            }
        }

    }
}
