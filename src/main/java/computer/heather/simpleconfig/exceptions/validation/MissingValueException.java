package computer.heather.simpleconfig.exceptions.validation;

/**
 * For when a value is empty / not found in the config.
 * Throw when <code>null</code> is passed to your validate method!
 */
public class MissingValueException extends BaseValidationException {

    /**
     * @param key the key that was missing from the config file.
     */
    public MissingValueException(String key) {
        super("Config is missing entry for key [" + key + "]!");
    }

}
