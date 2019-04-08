package smtp;

import model.mail.Message;

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

    public void send(Message m){
        try {
            out = new PrintWriter(smtpSocket.getOutputStream(), true);
            out.print("EHLO alkapote\r\n");
            out.flush();
            this.displayServerResponse();
            out.print("MAIL FROM: david.simeonovic@heig-vd.ch\r\n");
            out.flush();
            this.displayServerResponse();
            out.print("RCPT TO: nemanja.pantic@heig-vd.ch\r\n");
            out.flush();
            this.displayServerResponse();
            out.print("DATA\r\n");
            out.flush();
            this.displayServerResponse();
            out.print("From: david.simeonovic@heig-vd.ch\r\n");
            out.flush();
            out.print("To: nemanja.pantic@heig-vd.ch\r\n");
            out.flush();
            out.print("Subject: Test 2 depuis l'app\r\n");
            out.flush();
            out.print("\r\nCeci est un test depuis l'app\r\n");
            out.flush();
            out.print("\r\n.\r\n");
            out.flush();
            this.displayServerResponse();
        } catch(Exception e){
            System.out.println("Error for sending the message to the SMTP Server " + serverName + " on port " + serverPort);
        }
    }

    private void displayServerResponse(){
        String line = "";
        try {
            in = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
            while(!line.contains("250") && !line.contains("500") && !line.contains("501") && !line.contains("354")){
                line = in.readLine();
                System.out.println(line);
            }
        } catch(Exception e){
            System.out.println(e);
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
