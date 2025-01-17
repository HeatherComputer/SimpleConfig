package computer.heather.simpleconfig.exceptions.validation;

/**
 * For when a value cannot be parsed into the expected type.
 */
public class InvalidTypeException extends BaseValidationException {

    /**
     * @param expectedType the type expected for the relevant config option.
     * @param providedValue the string value that failed to match.
     */
    public InvalidTypeException(String expectedType, String providedValue) {
        super("Value [" + providedValue + "] is not of expected type [" + expectedType + "]!");
    }

}
