package de.arthurpicht.powerSocketCli;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.powerSocketCli.commands.GlobalOptionsDef;

public class GlobalOptions {

    public static boolean isDebug(CliCall cliCall) {
        return cliCall.getOptionParserResultGlobal().hasOption(GlobalOptionsDef.ID_DEBUG);
    }

    public static boolean isStacktrace(CliCall cliCall) {
        return cliCall.getOptionParserResultGlobal().hasOption(GlobalOptionsDef.ID_STACKTRACE);
    }

    public static boolean isSilent(CliCall cliCall) {
        return cliCall.getOptionParserResultGlobal().hasOption(GlobalOptionsDef.ID_SILENT);
    }

    public static boolean isNoColor(CliCall cliCall) {
        return cliCall.getOptionParserResultGlobal().hasOption(GlobalOptionsDef.ID_NO_COLOR);
    }

    public static boolean isKeepTemp(CliCall cliCall) {
        return cliCall.getOptionParserResultGlobal().hasOption(GlobalOptionsDef.ID_KEEP_TEMP);
    }

}
