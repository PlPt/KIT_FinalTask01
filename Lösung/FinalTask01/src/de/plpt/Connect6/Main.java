package de.plpt.Connect6;

import de.plpt.CommandProcessor.CommandProcessor;
import edu.kit.informatik.Terminal;

public class Main {

    /**
     * Main entry point for application.
     * Initializes and starts a new @see CommandProcessor for game
     *
     * @param args CommandLine (cli) Arguments for game
     */
    public static void main(String[] args) {
        try {
            CommandProcessor processor = new CommandProcessor(args);
            processor.processCommands();
        } catch (IllegalArgumentException x) {
            Terminal.printError(String.format("[%s] : %s", x.getClass().getName(), x.getMessage()));

        }
    }
}
