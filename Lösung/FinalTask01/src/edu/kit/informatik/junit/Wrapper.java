package edu.kit.informatik.junit;

import de.plpt.Main;

/**
 * Wrapper class for testing. This is used to access the main Method of
 * the Java application.
 * 
 * @version 2.0
 * @author Micha Hanselmann
 * @author Lucas Alber
 */
public class Wrapper {

    private static final String[] DEFAULT_ARGS = {};
    
    /**
     * Main Class which accesses the Main class of the Application
     * @param args initialize the game
     */
    public static void main(String[] args) {
        // TODO: adjust to fit your project setup "YourMainClass.main(args);"
        Main.main(args);
    }
    
    /**
     * Reference to main class
     */
    public static void main() {
        main(DEFAULT_ARGS);
    }

}
