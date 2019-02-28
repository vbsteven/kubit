package io.quantus.commands;

import io.quantus.Entry;
import io.quantus.KubitCore;
import picocli.CommandLine;

@CommandLine.Command(name = "add", description = "Adds the given <kubeconfig> as <name>")
public class AddCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "NAME", index = "0")
    private String name;

    @CommandLine.Parameters(paramLabel = "KUBECONFIG", index = "1")
    private String kubeconfig;

    @Override
    public void run() {
        KubitCore core = new KubitCore();
        core.addEntry(new Entry(name, kubeconfig));

        // TODO entry already exists
        System.out.printf("* entry added");
    }
}
