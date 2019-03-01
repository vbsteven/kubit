package io.quantus.commands;

import io.quantus.KubitApp;
import picocli.AutoComplete;
import picocli.CommandLine;

@CommandLine.Command(name = "completion", description = "Generates the autocompletion script for your shell")
public class CompletionCommand implements Runnable {

    @Override
    public void run() {
        String completionScript = AutoComplete.bash("kubit", new CommandLine(new KubitApp()));
        System.out.println(completionScript);
    }
}
