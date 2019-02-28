package io.quantus;

import picocli.CommandLine;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        CommandLine cmd = new CommandLine(new KubitApp());

        List<CommandLine> cmds = cmd.parse(args);

        if (cmds.size() == 1) {
            // show help when no subcommand is given
            cmds.get(0).usage(System.out);
        } else {
            cmd.parseWithHandler(new CommandLine.RunAll(), args);
        }

    }
}
