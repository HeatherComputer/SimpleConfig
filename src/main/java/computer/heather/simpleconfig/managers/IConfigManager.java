package computer.heather.simpleconfig.managers;

import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

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
     * @param location the location of the config file on disk. Absolute or relative.
     * @return the manager being referred to. Allows for chaining.
     */
    public IConfigManager setConfigLocation(Path location);

    /**
     * Attempt to load the config and throw if it can't be done.
     * @throws FileNotFoundException if the file doesn't exist to load from.
     * @throws BaseValidationException if a config option failed to verify when loading.
     */
    public void load() throws FileNotFoundException, BaseValidationException;

    /**
     * Save the config.
     * @throws AccessDeniedException if for some reason the file can't be written to disk.
     */
    public void save() throws AccessDeniedException;

    /**
     * Attempts to load the config. If none exists, 
     * @throws AccessDeniedException if for some reason the file can't be written to disk.
     * @throws BaseValidationException if a config option failed to verify when loading.
     */
    public void loadOrCreate() throws AccessDeniedException, BaseValidationException;


}
