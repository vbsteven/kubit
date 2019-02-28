package io.quantus.commands;

import io.quantus.Entry;
import io.quantus.KubitCore;
import io.quantus.exceptions.EntryAlreadyExistsException;
import picocli.CommandLine;

@CommandLine.Command(name = "add", description = "Adds the given KUBECONFIG as NAME")
public class AddCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "NAME", index = "0")
    private String name;

    @CommandLine.Parameters(paramLabel = "KUBECONFIG", index = "1")
    private String kubeconfig;

    @CommandLine.Option(names = {"-f", "--force"})
    private boolean force;

    @Override
    public void run() {
        KubitCore core = KubitCore.get();
        try {
            core.addEntry(new Entry(name, kubeconfig), force);
            System.out.printf("* entry added\n");
        } catch (EntryAlreadyExistsException e) {
            System.out.printf("* entry with name '%s' already exists\n", name);
            System.exit(1);
        }

    }
}
