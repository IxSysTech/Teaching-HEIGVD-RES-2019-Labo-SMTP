package model.mail;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private Group group;
    private String subject;
    private String content;

    public Message(Group group, String subject, String content){
        this.content = content;
        this.subject = subject;
        this.group = group;
    }

    public String getMailFrom(){
        return "MAIL FROM: " + group.getSender() + "\r\n";
    }

    public List<String> getRcpt(){
        List<String> to = new ArrayList<String>();
        for(String victim : group.getVictims() ){
            to.add("RCPT TO: " + victim + "\r\n");
        }

        return to;
    }

    private String getSubject(){
        return "Subject: " + this.subject + "\r\n";
    }

    private String getContent(){
        return this.content + "\r\n";
    }

    private String getFrom(){
        return "From:" + group.getSender() + "\r\n";
    }

    private String getTo(){
        String to = "To: ";

        for(String s : group.getVictims()){
            to += s + ";";
        }
        to = to.substring(0, to.length() - 1);
        to += "\r\n";

        return to;
    }

}
