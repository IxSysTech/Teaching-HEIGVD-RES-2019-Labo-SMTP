package model.mail;

import java.util.ArrayList;
import java.util.List;

public class Groups {
    private List<Group> groups;
    private List<String> users;
    private final int nbMinGroupUsers = 3;


    public Groups(List<String> users){
        this.users = users;
    }


    public void createGroups(int nbGroups){
        List<String> tmpUsers = users;
        List<String> tmpGroup = null;
        int rest = users.size()%nbGroups;
        int nbMembers = (int)users.size()/nbGroups;
        if(nbMembers < nbMinGroupUsers){
            System.out.println("Not enough users to create " + nbGroups);
        } else{
            groups = new ArrayList<Group>(nbGroups);

            for(int i = 0; i < nbGroups; ++i){
                Group group = new Group();
                int tmpNbMembers = nbMembers;
                if(i == nbGroups - 1){tmpNbMembers += rest;}
                for(int j = 0; j < tmpNbMembers; ++j) {
                    group.addMemebers(tmpUsers.get(tmpUsers.size() - 1));
                    tmpUsers.remove(tmpUsers.size() - 1);
                }
                groups.add(group);
            }
        }
    }


    public List<Group> getGroups(){
        return groups;
    }
}
