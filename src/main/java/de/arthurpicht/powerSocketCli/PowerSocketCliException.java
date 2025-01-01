package de.arthurpicht.powerSocketCli;

public class PowerSocketCliException extends RuntimeException {

    public PowerSocketCliException(Throwable cause) {
        super("Error: " + cause.getMessage(), cause);
    }

    public PowerSocketCliException(String message) {
        super(message);
    }

    public PowerSocketCliException(String message, Throwable cause) {
        super(message, cause);
    }
}
