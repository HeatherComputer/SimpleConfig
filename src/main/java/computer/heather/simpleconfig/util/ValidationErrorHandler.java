package computer.heather.simpleconfig.util;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;

/**
 * A functional interface that takes three inputs and can throw {@link BaseValidationException}.
 */
public interface ValidationErrorHandler<T, U, V> {
    void accept(T t, U u, V v) throws BaseValidationException;
}