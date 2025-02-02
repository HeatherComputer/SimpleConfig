package computer.heather.simpleconfig;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.io.TempDir;

import computer.heather.simpleconfig.exceptions.validation.MissingValueException;
import computer.heather.simpleconfig.managers.IConfigManager;
import computer.heather.simpleconfig.managers.PremadePropertiesManager;
import computer.heather.simpleconfig.types.BooleanValue;
import computer.heather.simpleconfig.types.FloatValue;
import computer.heather.simpleconfig.types.FreeStringValue;
import computer.heather.simpleconfig.types.LongValue;
import computer.heather.simpleconfig.types.StringArrayValue;
import computer.heather.simpleconfig.types.ValidatedStringValue;


/**
 * Generic test class to be subclassed by manager-specific tests.
 */
@TestMethodOrder(OrderAnnotation.class)
public abstract class GenericManagerTests {
    
    
    /**
     * A temporary directory we can use for our tests.
     */
    @TempDir
    Path tempDir;

    //I would final this manager, but when we're resetting that'd get in the way.
    /**
     * The manager to test.
     */
    IConfigManager testManager;


    /**
     * Reset the test manager. Must be implemented!
     */
    abstract void resetManager();


    //Our test config options.
    BooleanValue testBooleanValue;
    FloatValue testFloatValue;
    FreeStringValue testFreeStringValue;
    LongValue testLongValue;
    StringArrayValue testStringArrayValue;
    ValidatedStringValue testValidatedStringValue;


    void initTestOptions() {
        testBooleanValue = new BooleanValue("config.boolean.test", false);
        testFloatValue = new FloatValue("config.float.test", 0F, -1F, 1F);
        testFreeStringValue = new FreeStringValue("config.freestring.test", "Hello World!");
        testLongValue = new LongValue("config.long.test", 0L, -1L, 1L);
        testStringArrayValue = new StringArrayValue("config.stringarray.test", new String[]{"one", "two", "three"});
        testValidatedStringValue = new ValidatedStringValue("config.validatedstring.test", "one", new String[]{"one", "two", "three"});

        testManager.register(testBooleanValue, testFloatValue, testFreeStringValue, testLongValue, testStringArrayValue, testValidatedStringValue);
    }

    @BeforeEach
    void reset() {
        resetManager();
        initTestOptions();
    }


    /**
     * First, we test that attempting to load from a nonexistent file errors.
     */
    @Test
    @Order(1)
    void loadWithoutFileFails() {
        assertThrows(FileNotFoundException.class, testManager::load);
    }

    

    /**
     * Next, let's test that loading a config file with a missing key errors, and that an error handler safetly ignores it.
     */
    @Test 
    @Order(2)
    void testMissingKey() {

        //Create ourselves.
        assertDoesNotThrow(() -> writeFileForTest("test-missingvalue.properties"));

        //Now, test this.
        assertThrows(MissingValueException.class, testManager::load);

        //Now we give it an error handler that does nothing. This shouldn't throw at all.
        assertDoesNotThrow(() -> testManager.load((type, string, e) -> {assertInstanceOf(MissingValueException.class, e);}));
    }



    /**
     * We need ways to write files, so a helper method should be nice I'd say.
     * @throws IOException if this fails, tests can't run, so we just throw any error we get.
     */
    void writeFileForTest(String sourceName) throws IOException {

        File file = tempDir.resolve("test.properties").toFile();

        //Creates the file if it doesn't exist.
        file.createNewFile();
        file.setWritable(true);

        String premadeText;
        //Read the premade properties file.
        InputStream is = PremadePropertiesManager.class.getClassLoader().getResourceAsStream(sourceName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        premadeText = reader.lines().collect(Collectors.joining("\n"));
        reader.close();

        //Write the file to disk.
        FileWriter writer = new FileWriter(file);
        writer.write(premadeText);
        writer.close();

    }




    


}
