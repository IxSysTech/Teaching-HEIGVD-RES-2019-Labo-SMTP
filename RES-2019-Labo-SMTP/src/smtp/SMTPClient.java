/**
 * Auteur   : Nemanja Pantic, David Simeonovic
 * Nom      : SMTPClient.java
 * But      : Cette classe créer un socket et se connecte à un serveur. Elle permet ainsi d'envoyer des messages
 *            au serveur ainsi que d'afficher ses réponses
 */
package smtp;

import config.ConfigurationManager;
import model.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Base64;

public class SMTPClient {
    private Socket smtpSocket;
    private PrintWriter out;
    private BufferedReader in;
    private ConfigurationManager cm;

    /***
     * Constructeur de la classe SMTPClient
     * @param cm ConfigurationManager, permet de récupérer les configuration pour la connection au serveur stmp
     */
    public SMTPClient(ConfigurationManager cm){
       this.cm = cm;
    }

    /***
     * Connecte notre application avec le serveurs SMTP via un socket
     */
    public void connect(){
        try {
            smtpSocket = new Socket(cm.getServer(), cm.getPort());
            System.out.println("You are connected to the SMTP server "+ cm.getServer());
        } catch(Exception e){
            System.out.println("Error during the connection to the SMTP Server " + cm.getServer() + " on port " + cm.getPort());
        }
    }

    /***
     * Permet d'envoyer les mails aux personnes
     * @param m Message, contient le message à envoyé ainsi que la personne qui l'envoie et les personne qui le reçoivent
     * @param useLogin
     */
    public void send(Message m, Boolean useLogin){
        List<String> rcpt = m.getRcpt();

        try {
            out = new PrintWriter(smtpSocket.getOutputStream(), true);
            out.print("EHLO david\r\n");
            out.flush();
            this.displayServerResponse();
            if (useLogin){
                out.print("AUTH LOGIN\r\n");
                out.flush();
                out.print(Base64.getEncoder().encodeToString(cm.getUsername().getBytes()) + "\r\n");
                out.flush();
                out.print(Base64.getEncoder().encodeToString(cm.getPassword().getBytes()) + "\r\n");
                out.flush();
            }
            for(String s : rcpt) {
                out.print(m.getMailFrom());
                out.flush();
                System.out.print(m.getMailFrom());
                this.displayServerResponse();
                out.print(s);
                out.flush();
                System.out.print(s);
                this.displayServerResponse();
                out.print("DATA\r\n");
                out.flush();
                this.displayServerResponse();
                out.print(m.getFrom());
                out.flush();
                System.out.print(m.getFrom());
                out.print(m.getTo(s));
                out.flush();
                System.out.print(m.getTo(s));
                out.print(m.getSubject());
                out.flush();
                System.out.print(m.getSubject());
                out.print(m.getContent());
                out.flush();
                System.out.print(m.getContent());
                out.print("\r\n.\r\n");
                out.flush();
                this.displayServerResponse();
            }
        } catch(Exception e){
            System.out.println("Error for sending the message to the SMTP Server " + cm.getServer() + " on port " + cm.getPort());
        }
    }

    /***
     * Permet d'afficher les réponses du serveur
     */
    private void displayServerResponse(){
        String line = "";
        try {
            in = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
            while(!line.contains("250") && !line.contains("500") && !line.contains("501") && !line.contains("354") && !line.contains("550")){
                line = in.readLine();
                System.out.println(line);
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }

    /***
     * Déconnecte notre application au serveur SMTP
     */
    public void disconnect(){
        try {
            out.close();
            in.close();
            smtpSocket.close();
        } catch(Exception e){
            System.out.println("Error during the deconnection");
        }
    }
}
