package io.quantus.commands;

import picocli.CommandLine;

import java.io.*;

@CommandLine.Command(name = "init", description = "Initializes the bash/zsh helper functions")
public class InitCommand implements Runnable {
    @Override
    public void run() {
        // for easy maintenance the init scripts are defined in resources/shell_init.txt
        // and this command just reads the resource and prints to stdout
        InputStream in = getClass().getClassLoader()
                .getResourceAsStream("shell_init.txt");
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = r.readLine()) != null) {
                System.out.printf("%s\n", line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
