package computer.heather.simpleconfig.types;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;
import computer.heather.simpleconfig.exceptions.validation.MissingOptionException;

public class FakeConfigType extends BaseConfigType<Object> {
    
    public FakeConfigType(String key) {
        super(key);
    }

    @Override
    public void validate(String in) throws BaseValidationException {
        throw new MissingOptionException(this.key);
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Unsupported method 'get'");
    }

    @Override
    public void load(String in) {
        throw new UnsupportedOperationException("Unsupported method 'load'");
    }

    @Override
    public String save() {
        throw new UnsupportedOperationException("Unsupported method 'save'");
    }

}
