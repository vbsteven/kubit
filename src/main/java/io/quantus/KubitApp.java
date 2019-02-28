package io.quantus;

import io.quantus.commands.AddCommand;
import io.quantus.commands.InitCommand;
import io.quantus.commands.ListCommand;
import io.quantus.commands.RmCommand;
import picocli.CommandLine;

@CommandLine.Command(subcommands = {
        CommandLine.HelpCommand.class,
        ListCommand.class,
        AddCommand.class,
        RmCommand.class,
        InitCommand.class
}, name = "kubit")
public class KubitApp implements Runnable {

    @CommandLine.Option(names = {"-c", "--config"})
    private String configFilename;

    @Override
    public void run() {
        KubitCore.init(configFilename);
    }
}
