package de.arthurpicht.powerSocketCli;

import de.arthurpicht.cli.*;
import de.arthurpicht.cli.command.Commands;
import de.arthurpicht.cli.command.InfoDefaultCommand;
import de.arthurpicht.cli.common.UnrecognizedArgumentException;
import de.arthurpicht.console.Console;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.Level;
import de.arthurpicht.powerSocketApi.IllegalOperationException;
import de.arthurpicht.powerSocketApi.PowerSocketApiException;
import de.arthurpicht.powerSocketCli.commands.GlobalOptionsDef;
import de.arthurpicht.powerSocketCli.commands.OffDef;
import de.arthurpicht.powerSocketCli.commands.OnDef;
import de.arthurpicht.powerSocketCli.commands.StatusDef;
import de.arthurpicht.utils.core.strings.Strings;

public class PowerSocketCli {

    private static final String EXEC_NAME = "psocket";

    private static final int EXIT_STATUS_SYNTAX_ERROR = 1;
    private static final int EXIT_STATUS_INTERNAL_ERROR = 2;
    private static final int EXIT_STATUS_ILLEGAL_OPERATION = 3;
    private static final int EXIT_STATUS_API_EXCEPTION = 4;

    private static Cli prepareCli() {
        Commands commands = new Commands();

        commands.setDefaultCommand(new InfoDefaultCommand());
        commands.add(StatusDef.get());
        commands.add(OnDef.get());
        commands.add(OffDef.get());

        CliDescription cliDescription = new CliDescriptionBuilder()
                .withDescription(EXEC_NAME + ". The isPoweredOn socket CLI.\nhttps://github.com/arthurpicht/isPoweredOn-socket-cli")
                .withVersionText("v0.0.1-SNAPSHOT")
                .build(EXEC_NAME);

        return new CliBuilder()
                .withGlobalOptions(GlobalOptionsDef.getOptions())
                .withCommands(commands)
                .withAutoHelp()
                .build(cliDescription);
    }


    public static void main(String[] args) {

        Cli cli = prepareCli();
        CliCall cliCall = null;

        try {
            cliCall = cli.parse(args);
        } catch (UnrecognizedArgumentException e) {
            Console.println("[Error] m7r syntax error. " + e.getMessage());
            Console.println(e.getCallString());
            Console.println(e.getCallPointerString());
            System.exit(EXIT_STATUS_SYNTAX_ERROR);
        }

        Console.configure(new ConsoleConfigurationBuilder()
                .withMutedOutput(GlobalOptions.isSilent(cliCall))
                .withLevel(GlobalOptions.isDebug(cliCall) ? Level.VERBOSE : Level.NORMAL)
                .withSuppressedColors(GlobalOptions.isNoColor(cliCall))
                .build()
        );

        Console.printlnVerbose("execute command: " + Strings.listing(cliCall.getArgs(), " "));
        try {
            cli.execute(cliCall);
        } catch (RuntimeException e) {

            ConsoleWriter.internalError(e.getMessage());
            if (GlobalOptions.isStacktrace(cliCall)) {
                Console.printStackTrace(e);
            } else {
                Console.println("Consider calling command with --stacktrace global option.");
            }
            System.exit(EXIT_STATUS_INTERNAL_ERROR);

        } catch (CommandExecutorException e) {

            if (e.getCause() instanceof PowerSocketApiException powerSocketApiException) {

                ConsoleWriter.error(powerSocketApiException.getMessage());
                if (GlobalOptions.isStacktrace(cliCall)) Console.printStackTrace(e);
                System.exit(EXIT_STATUS_API_EXCEPTION);
            } else if (e.getCause() instanceof IllegalOperationException) {
                ConsoleWriter.error("Illegal operation: " + e.getMessage());
                if (GlobalOptions.isStacktrace(cliCall)) Console.printStackTrace(e);
                System.exit(EXIT_STATUS_ILLEGAL_OPERATION);

            } else {
                if (e.getMessage() != null) {
                    ConsoleWriter.error(e.getMessage());
                } else {
                    Throwable cause = e.getCause();
                    if (cause.getMessage() != null) {
                        ConsoleWriter.error(cause.getMessage());
                    } else {
                        ConsoleWriter.error("unknown.");
                    }
                }
                if (GlobalOptions.isStacktrace(cliCall)) Console.printStackTrace(e);
                System.exit(EXIT_STATUS_INTERNAL_ERROR);
            }
        }


    }

}
