package de.plpt.Connect6.exceptions;

public class Connect6GameException extends Exception {

    //region constructor
    public Connect6GameException(String message) {
        super(message);
    }

    public Connect6GameException(String message, Throwable cause) {
        super(message);
        super.initCause(cause);

    }
    //endregion
}
