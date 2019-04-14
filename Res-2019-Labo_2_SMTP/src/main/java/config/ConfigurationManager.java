/**
 * Auteur   : Nemanja Pantic, David Simeonovic
 * Nom      : ConfigurationManager.java
 * But      : Cette classe permet de lire tous nos fichiers de configuration(config.properties, message.txt, victims.txt)
 *            et les stoque dans des variables.
 */
package config;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConfigurationManager {
    private List<String> victims;
    private Map<String,String> messages;
    private List<String> config;

    /**
     * Constructeur de la classe, appelle les fonctions qui lisent les fichier de config
     */
    public ConfigurationManager(){
        config = this.readFile("config.properties");
        messages = this.readMessage("messages.txt");
        victims = this.readFile("victims.txt");
    }

    /**
     * Permet de lire le fichier de config ainsi que le fichier des victimes
     * @param file Fichier à lire
     * @return List<String> : liste qui contient chaque ligne du fichier lu
     */
    private List<String> readFile(String file){
        List<String> lst = new ArrayList<String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                lst.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return lst;
    }

    /**
     * Permet de lire le fichier qui contient les messages à envoyer
     * @param file Fichier à lire
     * @return Map<String,String> : map qui a comme clé le sujet du mail et comme valeur le contenu du mail
     */
    private Map<String,String> readMessage(String file){
        String subject = "";
        String content = "";
        Map<String,String> messages = new HashMap<String,String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains("Subject")){
                    subject = line;
                }else if (line.contains("==")){
                    messages.put(subject,content);
                    subject = "";
                    content = "";
                }else {
                    content += line + "\n";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return messages;
    }

    /**
     * Retourne un map qui contient tous les messages à envoyer
     * @return Map<String,String> : messages à envoyé
     */
    public Map<String,String> getMessages() {
        return messages;
    }

    /**
     * Retourne la liste des victimes
     * @return List<String> : liste des victimes
     */
    public List<String> getVictims() {
        return victims;
    }

    /**
     * Retourne l'utilisateur pour l'authentification au serveur STMP
     * @return String : utilisateur
     */
    public String getUsername(){
        return config.get(2).split("=")[1];
    }

    /**
     * Retourne le mot de passe pour l'authentification au serveur SMTP
     * @return String : mot de passe
     */
    public String getPassword(){
        return config.get(3).split("=")[1];
    }

    /**
     * Retourne le nom du serveur pour la connection au serveur SMTP
     * @return String : nom du serveur
     */
    public String getServer(){
        return config.get(0).split("=")[1];
    }

    /**
     * Retourne le port pour la connection au serveur SMTP
     * @return String : port
     */
    public int getPort(){
        return Integer.valueOf(config.get(1).split("=")[1]);
    }
}
