import smtp.*;
import config.*;

import java.util.List;

public class MailRobot {
    public static void main(String[] args) {
        SMTPClient client = new SMTPClient("127.0.0.1",2525);
        ConfigurationManager cm = new ConfigurationManager();

        List<String> victims = cm.getVictims();

        for(String s : victims){
            System.out.println(s);
        }
    }
}
