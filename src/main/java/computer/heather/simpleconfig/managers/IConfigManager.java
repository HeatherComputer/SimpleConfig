package computer.heather.simpleconfig.managers;

import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import computer.heather.simpleconfig.types.BaseConfigType;

public interface IConfigManager {
    
    /**
     * @param entry the config entry to register.
     * @return the manager being referred to. Allows for chaining.
     */
    public IConfigManager register(BaseConfigType<?> entry);

    /**
     * @param entry the config entry to register.
     * @return the manager being referred to. Allows for chaining.
     */
    public IConfigManager setConfigLocation(Path location);

    /**
     * Attempt to load the config and throw if it can't be done.
     * @throws FileNotFoundException if the file doesn't exist to load from.
     */
    public void load() throws FileNotFoundException;

    /**
     * Save the config.
     * @throws AccessDeniedException if for some reason the file can't be written to disk.
     */
    public void save() throws AccessDeniedException;


}
