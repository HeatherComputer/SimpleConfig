package computer.heather.simpleconfig.types;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;
import computer.heather.simpleconfig.managers.IConfigManager;


/**
 * @param <T> the Java class this type represents.
 */
public abstract class BaseConfigType<T> {

    /**
     * The config key used for the option in question.
     */
    public final String key;
    /**
     * The {@link IConfigManager} the option is registered to.
     */
    public final IConfigManager manager;

    protected BaseConfigType(String keyIn, IConfigManager managerIn) {
        this.key = keyIn;
        this.manager = managerIn;

        this.manager.register(this);
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
