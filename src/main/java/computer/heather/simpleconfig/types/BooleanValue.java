package computer.heather.simpleconfig.types;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;
import computer.heather.simpleconfig.exceptions.validation.InvalidTypeException;
import computer.heather.simpleconfig.exceptions.validation.MissingValueException;
import computer.heather.simpleconfig.managers.IConfigManager;


/**
 * A BooleanValue config type.
 */
public class BooleanValue extends BaseConfigType<Boolean> {

    private boolean value;

    public BooleanValue(String key, boolean defaultValue, IConfigManager manager) {
        super(key, manager);
        this.value = defaultValue;
    }

    @Override
    public void validate(String in) throws BaseValidationException {       
        if (in == null) throw new MissingValueException(this.key);     

        if ((!in.toLowerCase().equals("false")) && (!in.toLowerCase().equals("true"))) throw new InvalidTypeException("boolean", in);
    }

    @Override
    public Boolean get() {
        return value;
    }

    @Override
    public void load(String in) {
        value = Boolean.parseBoolean(in);
    }

    @Override
    public String save() {
        return Boolean.toString(value);
    }
    
}
