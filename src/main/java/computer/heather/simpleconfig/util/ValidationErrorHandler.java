package computer.heather.simpleconfig.util;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;

public interface ValidationErrorHandler<T, U> {
    void accept(T t, U u) throws BaseValidationException;
}