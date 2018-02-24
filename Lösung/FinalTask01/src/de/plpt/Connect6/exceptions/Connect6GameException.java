package de.plpt.Connect6.exceptions;

public class Connect6GameException extends Exception {

    //region constructor

    /**
     * Initialize a new Connect6GameException
     *
     * @param message Message of Exception
     */
    public Connect6GameException(String message) {
        super(message);
    }

    /**
     * Initialize a new Connect6GameException
     *
     * @param message Message of Exception
     * @param cause   Exception cause
     */
    public Connect6GameException(String message, Throwable cause) {
        super(message);
        super.initCause(cause);

    }
    //endregion
}
