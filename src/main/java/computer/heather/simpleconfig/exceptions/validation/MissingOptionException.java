package computer.heather.simpleconfig.exceptions.validation;

import computer.heather.simpleconfig.types.FakeConfigType;

/**
 * For when a key exists in the config but is not registered to the handler.
 * Thrown by {@link FakeConfigType}!
 */
public class MissingOptionException extends BaseValidationException {

    /**
     * @param key the key that existed in the config file but wasn't registered.
     */
    public MissingOptionException(String key) {
        super("No registered config option for key [" + key + "]!");
    }

}
