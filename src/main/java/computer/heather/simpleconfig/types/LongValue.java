package computer.heather.simpleconfig.types;

import computer.heather.simpleconfig.managers.IConfigManager;
import computer.heather.simpleconfig.validation.ValidationResult;


/**
 * A LongValue config type.
 */
public class LongValue extends BaseConfigType<Long> {

    private long value;
    private long min;
    private long max;

    public LongValue(String key, long defaultValue, long min, long max, IConfigManager manager) {
        super(key, manager);
        this.value = defaultValue;
        this.min = min;
        this.max = max;
    }

    @Override
    public ValidationResult validate(String in) {            
        long i;
        try {
            i = Long.parseLong(in);
        } catch (NumberFormatException e) {
            return ValidationResult.INCORRECT_FORMAT;
        }

        if (i < min || i > max) return ValidationResult.OUT_OF_RANGE;

        return ValidationResult.VALID;
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
