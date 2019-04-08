package model.mail;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<String> members;

    public Group(){
        members = new ArrayList<String>();
    }

    public void addMemebers(String user){
        members.add(user);
    }

    public List<String> getMembers(){
        return members;
    }

    public void displayMembers(){
        for(String s : members){
            System.out.println(s);
        }
        System.out.println("-----------------------------");
    }

    public String getSender(){
        return members.get(0);
    }

    public List<String> getVictims(){
        return members.subList(1, members.size() - 1);
    }

}
