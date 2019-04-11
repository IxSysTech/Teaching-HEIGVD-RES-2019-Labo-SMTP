/**
 * Auteur   : Nemanja Pantic, David Simeonovic
 * Nom      : MailRobot.java
 * But      : Cette classe réunie toutes les autres classes et permet d'envoyé les mails aux personnes
 */
import model.*;
import smtp.*;
import config.*;

import java.util.ArrayList;
import java.util.List;

public class MailRobot {
    /**
     * Programme principal qui permet d'envoyer les mails
     * @param args Argument passer à la fonction (pas utilisé)
     */
    public static void main(String[] args) {
        ConfigurationManager cm = new ConfigurationManager();
        SMTPClient client = new SMTPClient(cm);
        GroupGenerator gm = new GroupGenerator(cm.getVictims());

        gm.createGroups(4);
        List<Group> prankGroup = gm.getGroups();
        List<Message> allMessages = new ArrayList<Message>();

        client.connect();
        int i = 0;
            for (String key : cm.getMessages().keySet()) {
            Message m = new Message(prankGroup.get(i++), key, cm.getMessages().get(key));
            client.send(m, true);
            allMessages.add(m);
        }
        client.disconnect();
    }
}
