package computer.heather.simpleconfig.types;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;
import computer.heather.simpleconfig.exceptions.validation.InvalidTypeException;
import computer.heather.simpleconfig.exceptions.validation.MissingValueException;
import computer.heather.simpleconfig.exceptions.validation.OutOfRangeException;


/**
 * A FloatValue config type.
 */
public class FloatValue extends BaseConfigType<Float> {

    private float value;
    private float min;
    private float max;
    private String range;

    public FloatValue(String key, float defaultValue, float min, float max) {
        super(key);
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.range = min + " - " + max;  
    }

    @Override
    public void validate(String in) throws BaseValidationException {       
        if (in == null) throw new MissingValueException(this.key);     
        float i;
        try {
            i = Float.parseFloat(in);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException("float", in);
        }

        if (i < min || i > max) throw new OutOfRangeException(range, in);

        return;
    }

    @Override
    public Float get() {
        return value;
    }

    @Override
    public void load(String in) {
        value = Float.parseFloat(in);
    }

    @Override
    public String save() {
        return Float.toString(value);
    }
    
}
