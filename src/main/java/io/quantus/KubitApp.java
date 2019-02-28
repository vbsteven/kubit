package io.quantus;

import io.quantus.commands.AddCommand;
import io.quantus.commands.InitCommand;
import io.quantus.commands.ListCommand;
import picocli.CommandLine;

@CommandLine.Command(subcommands = {
        CommandLine.HelpCommand.class,
        ListCommand.class,
        AddCommand.class,
        InitCommand.class
}, name = "kubit")
public class KubitApp implements Runnable {

    @Override
    public void run() {
        // when run without subcommand we show help
        new CommandLine(this).usage(System.out);
    }
}
