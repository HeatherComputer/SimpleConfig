package computer.heather.simpleconfig.types;

import computer.heather.simpleconfig.validation.ValidationResult;


/**
 * @param <T> the Java class this type represents.
 */
public abstract class BaseConfigType<T> {


    /**
     * @param in the input string from the config file.
     * @return a {@link ValidationResult} showing if the input is valid.
     */
    public abstract ValidationResult validate(String in);

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

}
