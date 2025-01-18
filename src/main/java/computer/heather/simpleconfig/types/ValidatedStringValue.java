package computer.heather.simpleconfig.types;

import java.util.Arrays;
import java.util.List;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;
import computer.heather.simpleconfig.exceptions.validation.MissingValueException;
import computer.heather.simpleconfig.exceptions.validation.OutOfRangeException;
import computer.heather.simpleconfig.managers.IConfigManager;


/**
 * A string config type that only accepts certain values.
 */
public class ValidatedStringValue extends BaseConfigType<String> {

    private String value;
    private List<String> allowedStrings;
    private String range;

    public ValidatedStringValue(String key, String defaultValue, String[] allowedStrings, IConfigManager manager) {
        super(key, manager);
        this.value = defaultValue;
        this.allowedStrings = Arrays.asList(allowedStrings);
        this.range = String.join(",", allowedStrings); 
    }

    @Override
    public void validate(String in) throws BaseValidationException {       
        if (in == null) throw new MissingValueException(this.key);     
        if (!allowedStrings.contains(in)) throw new OutOfRangeException(range, in);

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
