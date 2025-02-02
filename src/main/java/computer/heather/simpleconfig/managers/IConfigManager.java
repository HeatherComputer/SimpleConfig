package computer.heather.simpleconfig.managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;
import computer.heather.simpleconfig.types.BaseConfigType;
import computer.heather.simpleconfig.types.FakeConfigType;
import computer.heather.simpleconfig.util.ValidationErrorHandler;

public interface IConfigManager {
    
    /**
     * Register a config option to the given manager.
     * @param entry the config entry to register.
     * @return the manager being referred to. Allows for chaining.
     */
    public IConfigManager register(BaseConfigType<?> entry);
    
    /**
     * Register a config option to the given manager.
     * @param entry the config entry to register.
     * @return the manager being referred to. Allows for chaining.
     */
    public IConfigManager register(BaseConfigType<?>... entries);

    /**
     * Set the file used by the config manager.
     * @param location the location of the config file on disk. Should be relative, but it is up to managers to enforce this.
     * @return the manager being referred to. Allows for chaining.
     */
    public IConfigManager setConfigLocation(Path location);

    /**
     * A helper method for {@link #load(ValidationErrorHandler)}, so you don't need to provide an empty {@link ValidationErrorHandler} manually. <br>
     * See its description for more info.
     */
    public default IConfigManager load() throws IOException, FileNotFoundException, BaseValidationException {
        return load((configType, value, exception) -> {throw exception;});
    }

    /**
     * Attempt to load the config and throw if it can't be done.
     * @param errorHandler 
     *     an optional error handler. Either handle the error or throw {@link BaseValidationException}. <br>
     *     The provided {@link BaseConfigType} is the one that produced the error, you can call its validate method to recreate that error for yourself. <br>
     *     The provided string is the config value that failed to load. <br>
     *     Your error handler should handle the {@link BaseConfigType} being {@link FakeConfigType}! This is to allow for "migration" code: <br>
     *      managers are allowed to detect config entries that aren't registered options and pass them to error handlers.
     * @throws FileNotFoundException if the file doesn't exist to load from.
     * @throws BaseValidationException if a config option failed to verify when loading.
     * @throws IOException 
     */
    public IConfigManager load(ValidationErrorHandler<BaseConfigType<?>, String, BaseValidationException> errorHandler) throws IOException, FileNotFoundException, BaseValidationException;

    /**
     * Save the config.
     * @return 
     * @throws IOException if for some reason the file can't be written to disk.
     */
    public IConfigManager save() throws IOException;

    /**
     * A helper method for {@link #loadOrCreate(ValidationErrorHandler)}, so you don't need to provide an empty {@link ValidationErrorHandler} manually. <br>
     * See its description for more info.
     */
    public default IConfigManager loadOrCreate() throws IOException, BaseValidationException {
        return loadOrCreate((configType, value, exception) -> {throw exception;});
    }

    /**
     * Attempts to load the config. If none exists, automatically create one.
     * @param errorHandler 
     *     an optional error handler. Either handle the error or throw {@link BaseValidationException}. <br>
     *     The provided {@link BaseConfigType} is the one that produced the error, you can call its validate method to recreate that error for yourself. <br>
     *     The provided string is the config value that failed to load. <br>
     *     Your error handler should handle the {@link BaseConfigType} being {@link FakeConfigType}! This is to allow for "migration" code: <br>
     *      managers are allowed to detect config entries that aren't registered options and pass them to error handlers.
     * @throws AccessDeniedException if for some reason the file can't be written to disk.
     * @throws BaseValidationException if a config option failed to verify when loading.
     */
    public default IConfigManager loadOrCreate(ValidationErrorHandler<BaseConfigType<?>, String, BaseValidationException> errorHandler) throws IOException, AccessDeniedException, BaseValidationException {
        try {
            return load();
        } catch (FileNotFoundException e) {
            save();
            return load();
        }
    }

}
