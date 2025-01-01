package de.arthurpicht.powerSocketCli;

import de.arthurpicht.console.Console;
import de.arthurpicht.console.message.MessageBuilder;
import de.arthurpicht.console.message.format.Format;

public class ConsoleWriter {

    public static void ok(String message) {
        Console.out(new MessageBuilder()
                .addText("[OK]", Format.BRIGHT_GREEN_TEXT())
                .addText(" " + message)
                .build()
        );
    }

    public static void clearOk(String message) {
        Console.out(new MessageBuilder()
                .clearLine()
                .addText("[OK]", Format.BRIGHT_GREEN_TEXT())
                .addText(" " + message)
                .build()
        );
    }

    public static void warn(String message) {
        Console.out(new MessageBuilder()
                .addText("[WARN]", Format.BRIGHT_YELLOW_TEXT())
                .addText(" " + message)
                .build()
        );
    }

    public static void error(String message) {
        Console.out(new MessageBuilder()
                .terminatePreviousLine()
                .addText("[ERROR]", Format.BRIGHT_RED_TEXT())
                .addText(" " + message)
                .build()
        );
    }

    public static void internalError(String message) {
        Console.out(new MessageBuilder()
                .terminatePreviousLine()
                .addText("[INTERNAL ERROR]", Format.BRIGHT_RED_TEXT())
                .addText(" " + message)
                .build()
        );
    }

    public static void abort(String message) {
        Console.out(new MessageBuilder()
                .addText("[ABORT]", Format.BRIGHT_RED_TEXT())
                .addText(" " + message)
                .build()
        );
    }

    public static void highlighted(String message, String highlightedText) {
        if (!message.contains("%s")) throw new IllegalArgumentException("No placeholder found in message.");
        String pre = message.substring(0, message.indexOf("%s"));
        String post = message.substring(message.indexOf("%s") + 2);
        Console.out(new MessageBuilder()
                .addText(pre)
                .addText(highlightedText, Format.BRIGHT_WHITE_TEXT())
                .addText(post)
                .build()
        );
    }

    public static void success(String message) {
        message = message.toUpperCase();
        Console.out(new MessageBuilder()
                .addText(message, Format.BRIGHT_GREEN_TEXT())
                .build()
        );
    }

}
