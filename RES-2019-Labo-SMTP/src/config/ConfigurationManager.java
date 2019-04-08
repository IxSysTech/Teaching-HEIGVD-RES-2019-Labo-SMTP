package config;

import javax.swing.text.Keymap;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationManager {
    private List<String> victims;
    private Map<String,String> messages;
    private List<String> config;

    public ConfigurationManager(){
        config = this.readFile("src/config/config.properties");
        messages = this.readMessage("src/config/messages.txt");
        victims = this.readFile("src/config/victims.txt");
    }

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

    public List<String> getConfig() {
        return config;
    }

    public Map<String,String> getMessages() {
        return messages;
    }

    public List<String> getVictims() {
        return victims;
    }
}
