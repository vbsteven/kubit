package io.quantus;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class KubitCore {

    private Config config;

    public KubitCore() {
        readConfig();
    }


    public List<Entry> getEntries() {
        return config.getEntries();
    }

    public void addEntry(Entry entry) {
        config.addEntry(entry);

        writeConfig();
    }

    private void readConfig() {

        try {
            String input = new String(Files.readAllBytes(Paths.get("/home/steven/.kubit")));

            ObjectMapper mapper = new ObjectMapper();
            this.config = mapper.readValue(input, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            // todo proper error handling
            throw new RuntimeException(e);
        }
    }

    private void writeConfig() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(config);
            Files.write(Paths.get("/home/steven/.kubit"), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
