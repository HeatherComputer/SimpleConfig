package computer.heather.simpleconfig.exceptions.validation;

/**
 * For when a value is empty / not found in the config.
 */
public class MissingValueException extends BaseValidationException {

    /**
     * @param key
     */
    public MissingValueException(String key) {
        super("Config is missing entry for key [" + key + "]!");
    }

}
