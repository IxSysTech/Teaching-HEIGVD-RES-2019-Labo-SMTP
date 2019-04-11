/**
 * Auteur   : Nemanja Pantic, David Simeonovic
 * Nom      : Message.java
 * But      : Cette classe permet de lier contient un groupe de personne qui seront les déstinataires du message.
 *            Elle propose plusieurs geteur qui retourne les chaines de caractères formatées pour l'envoie de message
 *            au serveur SMTP.
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private Group group;
    private String subject;
    private String content;

    /***
     * Constructeur de la classe
     * @param group Groupe auquel on envoie le message
     * @param subject Sujet du message
     * @param content Contenu du message
     */
    public Message(Group group, String subject, String content){
        this.content = content;
        this.subject = subject;
        this.group = group;
    }

    /***
     * Retourne le mail de la personne qui envoie le mail
     * @return String : personne qui envoie le mail
     */
    public String getMailFrom(){
        return "MAIL FROM: <" + group.getSender() + ">\r\n";
    }

    /***
     * Retourne une liste qui contient les mails des personnes qui reçoivent le mail
     * @return List<String> : personne qui reçoie le mail
     */
    public List<String> getRcpt(){
        List<String> to = new ArrayList<String>();
        List<String> victims = group.getVictims();
        for(String victim : victims){
            to.add("RCPT TO: <" + victim + ">\r\n");
        }

        return to;
    }

    /***
     * Retourne le sujet du mail
     * @return String : sujet du mail
     */
    public String getSubject(){
        return "Subject: " + this.subject + "\r\n";
    }

    /***
     * Retourne le contenu du mail
     * @return String : contenu du mail
     */
    public String getContent(){
        return this.content + "\r\n";
    }

    /***
     * Retourne le from du mail
     * @return String : from du mail
     */
    public String getFrom(){
        return "From: " + group.getSender() + "\r\n";
    }

    /***
     * Retourne le to du mail
     * @param mail Mail à qui on va envoyé le mail
     * @return String : mail formaté de la personne qui reçoit le mail
     */
    public String getTo(String mail){
        return "To:" + mail.split(":")[1];
    }

}
