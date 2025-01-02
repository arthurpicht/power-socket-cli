package de.arthurpicht.powerSocketCli.commands;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.parameter.Parameters;
import de.arthurpicht.cli.parameter.ParametersOne;

public class OnDef {

    public static CommandSequence get() {
        ParametersOne parametersOne = new ParametersOne("outletId", "outlet ID");
        return new CommandSequenceBuilder()
                .addCommands("on")
                .withCommandExecutor(new OnCommand())
                .withDescription("Switch power of outlet on.")
                .withParameters(parametersOne)
                .build();
    }

}
