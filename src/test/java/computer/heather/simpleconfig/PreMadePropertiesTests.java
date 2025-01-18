/*
 * This source file was generated by the Gradle 'init' task
 */
package computer.heather.simpleconfig;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import computer.heather.simpleconfig.managers.IConfigManager;
import computer.heather.simpleconfig.managers.PremadePropertiesManager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.nio.file.Path;

class PreMadePropertiesTests {

    /**
     * A temporary directory we can use for our tests.
     */
    @TempDir
    static Path tempDir;

    /**
     * First, we test that attempting to load from a nonexistent file errors.
     */
    @Test 
    @Order(1)
    void loadWithoutFileFails() {
        IConfigManager testManager = new PremadePropertiesManager().setConfigLocation(tempDir.resolve("test.properties"));
        assertThrows(FileNotFoundException.class, testManager::load);
    }

}
