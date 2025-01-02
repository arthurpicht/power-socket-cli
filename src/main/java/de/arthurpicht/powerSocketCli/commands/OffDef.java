package de.arthurpicht.powerSocketCli.commands;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.parameter.ParametersOne;

public class OffDef {

    public static CommandSequence get() {
        ParametersOne parametersOne = new ParametersOne("outletId", "outlet ID");
        return new CommandSequenceBuilder()
                .addCommands("off")
                .withCommandExecutor(new OffCommand())
                .withDescription("Switch power of outlet off.")
                .withParameters(parametersOne)
                .build();
    }

}
