package io.quantus.commands;

import picocli.CommandLine;

@CommandLine.Command(name = "init", description = "Initializes the bash/zsh helper functions")
public class InitCommand implements Runnable {
    @Override
    public void run() {
        System.out.printf("Inside init");

    }
}
