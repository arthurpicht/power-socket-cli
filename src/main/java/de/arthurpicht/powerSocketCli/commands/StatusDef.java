package de.arthurpicht.powerSocketCli.commands;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class StatusDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("status")
                .withCommandExecutor(new StatusCommand())
                .withDescription("Show status.")
                .build();
    }

}
