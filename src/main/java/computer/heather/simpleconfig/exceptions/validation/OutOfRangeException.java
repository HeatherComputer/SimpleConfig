package computer.heather.simpleconfig.exceptions.validation;

/**
 * For when a value is not within allowed values.
 */
public class OutOfRangeException extends BaseValidationException {
    
    /**
     * @param allowedRange The allowed range for the config option in question.
     * @param value The actual given value.
     */
    public OutOfRangeException(String allowedRange, String value) {
        super("Value not within allowed ranges! Expected [" + allowedRange + "], got [" + value + "]!");
    }

}
