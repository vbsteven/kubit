package io.quantus.commands;

import io.quantus.KubitCore;
import picocli.CommandLine;

@CommandLine.Command(name = "list", description = "Lists all entries")
public class ListCommand implements Runnable {

    @Override
    public void run() {
        KubitCore core = KubitCore.get();

        System.out.printf("* All entries\n");

        int longest = core.getEntries()
                .stream()
                .mapToInt(e -> e.getName().length())
                .max()
                .orElse(0);

        core.getEntries().forEach(e -> {
            String formattedName = String.format("%1$" + longest + "s", e.getName());
            System.out.printf("%s  ->  %s\n", formattedName, e.getKubeconfig());
        });

    }
}
