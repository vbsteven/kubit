package io.quantus.commands;

import io.quantus.Entry;
import io.quantus.KubitCore;
import picocli.CommandLine;

import java.util.Optional;

@CommandLine.Command(name = "current", description = "Show the currently configured entry")
public class CurrentCommand implements Runnable {

    @Override
    public void run() {

        String kubeconfigValue = System.getenv("KUBECONFIG");

        if (kubeconfigValue == null) {
            System.err.printf("* KUBECONFIG is not set");
            System.exit(1);
        }

        Optional<Entry> entry = KubitCore.get()
                .findEntryByKubeconfig(kubeconfigValue);

        if (!entry.isPresent()) {
            System.err.printf("* KUBECONFIG is set to '%s' but that entry is not present in kubit\n", kubeconfigValue);
            System.exit(1);
        }

        System.out.printf("%s -> %s\n", entry.get().getName(), entry.get().getKubeconfig());
    }

}
