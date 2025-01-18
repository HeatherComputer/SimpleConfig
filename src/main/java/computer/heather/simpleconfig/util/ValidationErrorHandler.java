package computer.heather.simpleconfig.util;

import java.util.function.BiConsumer;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;

/**
 * A functional interface that replicates a {@link BiConsumer}, but can throw {@link BaseValidationException}.
 */
public interface ValidationErrorHandler<T, U> {
    void accept(T t, U u) throws BaseValidationException;
}