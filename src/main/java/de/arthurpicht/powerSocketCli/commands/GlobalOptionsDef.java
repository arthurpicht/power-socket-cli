package de.arthurpicht.powerSocketCli.commands;

import de.arthurpicht.cli.option.ManOption;
import de.arthurpicht.cli.option.OptionBuilder;
import de.arthurpicht.cli.option.Options;
import de.arthurpicht.cli.option.VersionOption;

public class GlobalOptionsDef {

    public static final String ID_DEBUG = "debug";
    public static final String ID_SILENT = "silent";
    public static final String ID_STACKTRACE = "stacktrace";
    public static final String ID_NO_COLOR = "no-color";
    public static final String ID_KEEP_TEMP = "keep-temp";

    public static Options getOptions() {
        return new Options()
                .add(new VersionOption())
                .add(new ManOption())
                .add(new OptionBuilder()
                        .withShortName('d')
                        .withLongName(ID_DEBUG)
                        .withDescription("show additional debug output")
                        .build(ID_DEBUG))
                .add(new OptionBuilder()
                        .withLongName(ID_SILENT)
                        .withDescription("omit all output")
                        .build(ID_SILENT))
                .add(new OptionBuilder()
                        .withLongName(ID_STACKTRACE)
                        .withDescription("show stacktrace on error")
                        .build(ID_STACKTRACE))
                .add(new OptionBuilder()
                        .withLongName(ID_NO_COLOR)
                        .withDescription("suppress color on console output")
                        .build(ID_NO_COLOR))
                .add(new OptionBuilder()
                        .withLongName(ID_KEEP_TEMP)
                        .withDescription("keep temporary files")
                        .build(ID_KEEP_TEMP));
    }

}
