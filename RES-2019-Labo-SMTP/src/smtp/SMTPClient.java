package smtp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SMTPClient {
    private Socket smtpSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String serverName;
    private int serverPort;

    public SMTPClient(String serverName, int serverPort){
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    public void connect(){
        try {
            smtpSocket = new Socket(serverName, serverPort);
            System.out.println("You are connected to the SMTP server "+ serverName);
        } catch(Exception e){
            System.out.println("Error during the connection to the SMTP Server " + serverName + " on port " + serverPort);
        }
    }

    public void send(String message){
        try {
            out = new PrintWriter(smtpSocket.getOutputStream(), true);
            out.println(message);
        } catch(Exception e){
            System.out.println("Error for sending the message to the SMTP Server " + serverName + " on port " + serverPort);
        }
    }


    public void disconnect(){
        try {
            out.close();
            //in.close();
            smtpSocket.close();
        } catch(Exception e){
            System.out.println("Error during the deconnection");
        }
    }
}
