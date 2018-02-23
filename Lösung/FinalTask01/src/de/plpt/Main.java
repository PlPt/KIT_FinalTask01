package de.plpt;

import de.plpt.CommandProcessor.CommandProcessor;
import edu.kit.informatik.Terminal;

public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            CommandProcessor processor = new CommandProcessor(args);
            processor.processCommands();
        }
        catch (Exception x){
            Terminal.printError(String.format("[%s] : %s",x.getClass().getName(),x.getMessage()));
           // x.printStackTrace();
        }
    }
}
