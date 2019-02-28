package io.quantus.commands;

import io.quantus.Entry;
import io.quantus.KubitCore;
import picocli.CommandLine;

import java.util.Optional;

@CommandLine.Command(name = "use", description = "Use the given entry by setting KUBECONFIG")
public class UseCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "NAME", index = "0")
    private String name;

    @Override
    public void run() {
        Optional<Entry> entry = KubitCore.get().findEntryByName(name);
        if (!entry.isPresent()) {
            System.err.printf("* no entry with name '%s' exists\n", name);
            System.exit(1);
        }

        entry.ifPresent(e -> {
            System.out.printf("export KUBECONFIG=%s\n", e.getKubeconfig());
        });

    }
}
