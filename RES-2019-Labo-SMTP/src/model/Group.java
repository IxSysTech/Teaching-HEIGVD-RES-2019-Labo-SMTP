/**
 * Auteur   : Nemanja Pantic, David Simeonovic
 * Nom      : Group.java
 * But      : Cette classe permet de créer un groupe qui contient plusieurs adresse mail. Elle donne la possibilité de
 *            récupérer la personne qui envoie le mail ainsi que les personnes qui le reçoivent
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<String> members;

    /**
     * Constructeur de la classe Groupe
     */
    public Group(){
        members = new ArrayList<String>();
    }

    /**
     * Permet d'ajouter une membre au groupe
     * @param user String : mail du membre
     */
    public void addMemebers(String user){
        members.add(user);
    }

    /**
     * Retourne le mail de la personne qui envoie le mail dans le groupe
     * @return String : mail de l'envoyeur
     */
    public String getSender(){
        return members.get(0);
    }

    /**
     * Retourne une liste des personnes qui reçoivent le mail
     * @return List<String> : listes des personnes qui reçoivent le mail
     */
    public List<String> getVictims(){
        return members.subList(1, members.size());
    }

}
