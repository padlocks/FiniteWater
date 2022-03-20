package tfar.finitewater.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigHandler {
    public static final ConfigHandler CONFIG_HANDLER = new ConfigHandler();
    private File file;
    private Config config;

    private ConfigHandler() {
    }

    private boolean prepareConfigFile() {
        if (file != null) {
            return false;
        }
        File configDirectory = new File(".", "config");
        file = new File(configDirectory, "finitewater.json");
        if (file.exists()){
            return false;
        }
        return true;
    }

    public Config getConfig() {
        if (config != null) {
            return config;
        }

        config = new Config();
        load();

        return config;
    }

    private void load() {
        prepareConfigFile();

        try {
            if (file.exists()) {
                Gson gson = new Gson();
                BufferedReader br = new BufferedReader(new FileReader(file));

                config = gson.fromJson(br, Config.class);
            }
        } catch (Exception e) {
            System.err.println("Couldn't load configuration file for finite water, reverting to defaults");
            e.printStackTrace();
        }
    }

    public void save() {
        if (!prepareConfigFile()){
            return;
        }
        config = new Config();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(config);

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            System.err.println("Couldn't save ore configuration file for gt4r");
            e.printStackTrace();
        }
    }
}
