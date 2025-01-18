package computer.heather.simpleconfig.types;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;

public class FakeConfigType extends BaseConfigType<Object> {
    
    public FakeConfigType(String key) {
        super(key, null);
    }

    @Override
    public void validate(String in) throws BaseValidationException {
        throw new UnsupportedOperationException("Unsupported method 'validate'");
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
