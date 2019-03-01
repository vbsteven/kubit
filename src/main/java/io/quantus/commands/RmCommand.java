package io.quantus.commands;

import io.quantus.KubitCore;
import io.quantus.exceptions.EntryNotFoundException;
import picocli.CommandLine;

@CommandLine.Command(name = "rm", description = "Removes the entry with the given NAME")
public class RmCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "NAME", index = "0", completionCandidates = EntryNameCompletionCandidates.class)
    private String name;

    @Override
    public void run() {
        KubitCore core = KubitCore.get();
        try {
            core.removeEntry(name);
            System.out.printf("* entry '%s' removed\n", name);
        } catch (EntryNotFoundException e) {
            System.out.printf("* no entry exists with name '%s'\n", name);
            System.exit(1);
        }
    }
}
