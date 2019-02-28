package io.quantus;

import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {

//        KubitApp app = CommandLine.populateCommand(new KubitApp(),args);
        CommandLine.run(new KubitApp(), args);

    }
}
