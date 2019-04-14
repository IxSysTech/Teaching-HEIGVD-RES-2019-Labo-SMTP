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
import java.util.Scanner;

public class MailRobot {
    /**
     * Programme principal qui permet d'envoyer les mails
     * @param args Argument passer à la fonction (pas utilisé)
     */
    public static void main(String[] args) {
        ConfigurationManager cm = new ConfigurationManager();
        SMTPClient client = new SMTPClient(cm);
        GroupGenerator gm = new GroupGenerator(cm.getVictims());
        Scanner in = new Scanner(System.in);

        System.out.println("Nomber of groups to generate : ");
        String nbGroups = in.nextLine();

        while(!gm.createGroups(Integer.valueOf(nbGroups))){
            System.out.println("Nomber of groups to generate : ");
            nbGroups = in.nextLine();
        }

        System.out.println("Use connection for the SMTP server [y/n]");
        String userLogin = in.nextLine();

        while(!(userLogin.equals("y") || userLogin.equals("n"))){
            System.out.println("Bad response");
            System.out.println("Use connection for the SMTP server [y/n]");
            userLogin = in.nextLine();
        }

        List<Group> prankGroup = gm.getGroups();
        List<Message> allMessages = new ArrayList<Message>();

        client.connect();
        int i = 0;
        for (String key : cm.getMessages().keySet()) {
            Message m = null;
            if(i < prankGroup.size()) {
                m = new Message(prankGroup.get(i++), key, cm.getMessages().get(key));
                client.send(m, userLogin.equals("y") ? true : false);
                allMessages.add(m);
            }
        }

        client.disconnect();
    }
}
