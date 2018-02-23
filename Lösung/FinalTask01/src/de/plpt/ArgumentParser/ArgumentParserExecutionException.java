package de.plpt.ArgumentParser;

public class ArgumentParserExecutionException extends ArgumentParserException {


    public ArgumentParserExecutionException(String message) {
        super(message);
    }

    public ArgumentParserExecutionException(String message,Throwable cause) {
        super(message,cause);
    }

    public <T extends  Exception>  T getTypedCause(){
        return (T) this.getCause();
    }
}
