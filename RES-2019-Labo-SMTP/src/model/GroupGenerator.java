/**
 * Auteur   : Nemanja Pantic, David Simeonovic
 * Nom      : GroupGenerator.java
 * But      : Cette classe permet de créer plusieurs groupes de mail. Un groupe doit avoir au minimum 3 membres.
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class GroupGenerator {
    private List<Group> groups;
    private List<String> users;
    private final int nbMinGroupUsers = 3;

    /**
     * Contructeur de la classe GroupGenerator
     * @param users Listes de tous les utilisateurs
     */
    public GroupGenerator(List<String> users){
        this.users = users;
    }

    /**
     * Génére des groupes d'utilisateurs
     * @param nbGroups Int : nombre de groupes a créer
     */
    public void createGroups(int nbGroups){
        List<String> tmpUsers = users;
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

    /**
     * Retourne une liste de groupe
     * @return List<Group> : tous les groups générer par la classe
     */
    public List<Group> getGroups(){
        return groups;
    }
}
