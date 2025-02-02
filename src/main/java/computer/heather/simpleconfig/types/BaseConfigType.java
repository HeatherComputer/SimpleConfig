package computer.heather.simpleconfig.types;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;


/**
 * @param <T> the Java class this type represents.
 */
public abstract class BaseConfigType<T> {

    /**
     * The config key used for the option in question.
     */
    public final String key;

    protected BaseConfigType(String keyIn) {
        this.key = keyIn;
    }

    /**
     * @param in the input string from the config file.
     * @throws BaseValidationException if the provided value fails to validate.
     */
    public abstract void validate(String in) throws BaseValidationException;

    /**
     * @return the value of this type's loaded config option.
     */
    public abstract T get();

    /**
     * @param in the input string from the config file.
     */
    public abstract void load(String in);

    /**
     * @return the string to store in the config.
     */
    public abstract String save();

    /**
     * @return the key for this config entry.
     */
    public String getKey() {
        return key;
    }
}
