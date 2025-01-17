package computer.heather.simpleconfig.exceptions.validation;

/**
 * The basic ValidationException that the others extend from.
 */
public class BaseValidationException extends Exception {

    public BaseValidationException() {
        super();
    }

    public BaseValidationException(String string) {
        super(string);
    }
    
}
