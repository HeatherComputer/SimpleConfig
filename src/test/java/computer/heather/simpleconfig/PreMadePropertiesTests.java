package computer.heather.simpleconfig;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import computer.heather.simpleconfig.managers.PremadePropertiesManager;

@SuppressWarnings("unused")
class PreMadePropertiesTests extends GenericManagerTests{

    @Override
    void resetManager() {
        testManager = new PremadePropertiesManager()
            .setConfigLocation(tempDir.resolve("test.properties"));
    }


}
