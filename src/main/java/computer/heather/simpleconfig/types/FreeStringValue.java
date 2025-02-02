package computer.heather.simpleconfig.types;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;
import computer.heather.simpleconfig.exceptions.validation.MissingValueException;


/**
 * A string config type that accepts any string.
 */
public class FreeStringValue extends BaseConfigType<String> {

    private String value;

    public FreeStringValue(String key, String defaultValue) {
        super(key);
        this.value = defaultValue;
    }

    @Override
    public void validate(String in) throws BaseValidationException {       
        if (in == null) throw new MissingValueException(this.key);     

        return;
    }

    @Override
    public String get() {
        return value;
    }

    @Override
    public void load(String in) {
        value = in;
    }

    @Override
    public String save() {
        return value;
    }
    
}
