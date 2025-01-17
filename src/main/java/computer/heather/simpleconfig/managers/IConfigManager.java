package computer.heather.simpleconfig.managers;

import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.function.BiConsumer;

import javax.annotation.Nullable;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;
import computer.heather.simpleconfig.types.BaseConfigType;

public interface IConfigManager {
    
    /**
     * Register a config option to the given manager.
     * @param entry the config entry to register.
     * @return the manager being referred to. Allows for chaining.
     */
    public IConfigManager register(BaseConfigType<?> entry);

    /**
     * Set the file used by the config manager.
     * @param location the location of the config file on disk. Should be relative, but it is up to managers to enforce this.
     * @return the manager being referred to. Allows for chaining.
     */
    public IConfigManager setConfigLocation(Path location);

    /**
     * A helper method for {@link #load(BiConsumer)}, so you don't need to provide <code>null</code> manually. <br>
     * See its description for more info.
     */
    public default void load() throws FileNotFoundException, BaseValidationException {
        load(null);
    }

    /**
     * Attempt to load the config and throw if it can't be done.
     * @param errorHandler 
     *     an optional error handler. Should throw {@link BaseValidationException}. <br>
     *     The provided {@link BaseConfigType} is the one that produced the error, you can call its validate method to recreate that error for yourself. <br>
     *     The provided string is the config value that failed to load.
     * @throws FileNotFoundException if the file doesn't exist to load from.
     * @throws BaseValidationException if a config option failed to verify when loading.
     */
    public void load(@Nullable BiConsumer<BaseConfigType<?>, String> errorHandler) throws FileNotFoundException, BaseValidationException;

    /**
     * Save the config.
     * @throws AccessDeniedException if for some reason the file can't be written to disk.
     */
    public void save() throws AccessDeniedException;

    /**
     * A helper method for {@link #loadOrCreate(BiConsumer)}, so you don't need to provide <code>null</code> manually. <br>
     * See its description for more info.
     */
    public default void loadOrCreate() throws AccessDeniedException, BaseValidationException {
        loadOrCreate(null);
    }

    /**
     * Attempts to load the config. If none exists, automatically create one.
     * @param errorHandler 
     *     an optional error handler. Should throw {@link BaseValidationException}. <br>
     *     The provided {@link BaseConfigType} is the one that produced the error, you can call its validate method to recreate that error for yourself. <br>
     *     The provided string is the config value that failed to load.
     * @throws AccessDeniedException if for some reason the file can't be written to disk.
     * @throws BaseValidationException if a config option failed to verify when loading.
     */
    public void loadOrCreate(@Nullable BiConsumer<BaseConfigType<?>, String> errorHandler) throws AccessDeniedException, BaseValidationException;

}
