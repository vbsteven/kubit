package io.quantus;

import io.quantus.commands.*;
import picocli.CommandLine;

@CommandLine.Command(subcommands = {
        CommandLine.HelpCommand.class,
        AddCommand.class,
        CurrentCommand.class,
        InitCommand.class,
        ListCommand.class,
        RmCommand.class,
        UseCommand.class
}, name = "kubit")
public class KubitApp implements Runnable {

    @CommandLine.Option(names = {"-c", "--config"})
    private String configFilename;

    @Override
    public void run() {
        KubitCore.init(configFilename);
    }
}
