package computer.heather.simpleconfig.types;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;
import computer.heather.simpleconfig.exceptions.validation.InvalidTypeException;
import computer.heather.simpleconfig.exceptions.validation.MissingValueException;
import computer.heather.simpleconfig.exceptions.validation.OutOfRangeException;
import computer.heather.simpleconfig.managers.IConfigManager;


/**
 * A LongValue config type.
 */
public class LongValue extends BaseConfigType<Long> {

    private long value;
    private long min;
    private long max;
    private String range;

    public LongValue(String key, long defaultValue, long min, long max, IConfigManager manager) {
        super(key, manager);
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.range = min + "-" + max;  
    }

    @Override
    public void validate(String in) throws BaseValidationException {       
        if (in == null) throw new MissingValueException(this.key);     
        long i;
        try {
            i = Long.parseLong(in);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException("int", in);
        }

        if (i < min || i > max) throw new OutOfRangeException(range, in);

        return;
    }

    @Override
    public Long get() {
        return value;
    }

    @Override
    public void load(String in) {
        value = Long.parseLong(in);
    }

    @Override
    public String save() {
        return Long.toString(value);
    }
    
}
