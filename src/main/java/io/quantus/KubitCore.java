package io.quantus;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quantus.exceptions.EntryAlreadyExistsException;
import io.quantus.exceptions.EntryNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class KubitCore {

    private Path configPath;
    private Config config;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static KubitCore instance;

    KubitCore(Path configPath) {
        this.configPath = configPath;
        readConfig();
    }

    public static void init(String configFilename) {
         // default to .kubit in home dir
        Path configPath = Paths.get(System.getProperty("user.home"), ".kubit");

        if (configFilename != null) {
            configPath = Paths.get(configFilename);
        }

        if (Files.notExists(configPath)) {
            System.out.printf("* Creating config file at %s\n", configPath);
            try {
                Files.createFile(configPath);
                Files.write(configPath, "{\"entries\":[]}".getBytes());
            } catch (IOException e) {
                System.err.printf("* Could not write config file %s\n", configPath);
                System.exit(1);
            }
        }

        instance = new KubitCore(configPath);
    }

    public static KubitCore get() {
        if (instance == null) {
            throw new RuntimeException("Kubit is not initialized");
        }

        return instance;
    }


    public List<Entry> getEntries() {
        return config.getEntries();
    }

    public void addEntry(Entry entry, boolean overwrite) throws EntryAlreadyExistsException {
        config.addEntry(entry, overwrite);
        writeConfig();
    }

    public void removeEntry(String name) throws EntryNotFoundException {
        config.removeEntry(name);
        writeConfig();
    }

    private void readConfig() {
        try {
            String input = new String(Files.readAllBytes(configPath));
            this.config = objectMapper.readValue(input, Config.class);
        } catch (IOException e) {
            System.err.printf("* could not read from config file %s\n",
                              configPath);
            System.exit(1);
        }
    }

    private void writeConfig() {
        try {
            String json = objectMapper.writeValueAsString(config);
            Files.write(configPath, json.getBytes());
        } catch (IOException e) {
            System.err.printf("* could not write config to file %s\n",
                              configPath);
            System.exit(1);
        }
    }

    public Optional<Entry> findEntryByName(String name) {
        return config.findEntryByName(name);
    }

    public Optional<Entry> findEntryByKubeconfig(String kubeconfig) {
        return config.findEntryByKubeconfig(kubeconfig);
    }
}
