package config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationManager {
    private List<String> victims;
    private List<String> messages;
    private List<String> config;

    public ConfigurationManager(){
        config = this.readFile("src/config/config.properties");
        messages = this.readFile("src/config/messages.txt");
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

    public List<String> getConfig() {
        return config;
    }

    public List<String> getMessages() {
        return messages;
    }

    public List<String> getVictims() {
        return victims;
    }
}
