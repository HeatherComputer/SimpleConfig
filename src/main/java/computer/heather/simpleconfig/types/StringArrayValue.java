package computer.heather.simpleconfig.types;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;
import computer.heather.simpleconfig.exceptions.validation.InvalidTypeException;
import computer.heather.simpleconfig.exceptions.validation.MissingValueException;
import computer.heather.simpleconfig.exceptions.validation.OutOfRangeException;
import computer.heather.simpleconfig.managers.IConfigManager;


/**
 * A string array value. Comma-separated.
 */
public class StringArrayValue extends BaseConfigType<String[]> {

    private String[] value;
    private String range = "Comma-separated list with at least one entry!";

    public StringArrayValue(String key, String[] defaultValue, IConfigManager manager) {
        super(key, manager);
        this.value = defaultValue;
    }

    @Override
    public void validate(String in) throws BaseValidationException {       
        if (in == null) throw new MissingValueException(this.key);     
        if (in.length() == 0) throw new OutOfRangeException(range, in);
        String[] values = in.split(",");
        if (values.length == 0) throw new InvalidTypeException(range, in);

        return;
    }

    @Override
    public String[] get() {
        return value;
    }

    @Override
    public void load(String in) {
        value = in.split(",");
    }

    @Override
    public String save() {
        return String.join(",", value);
    }
    
}
