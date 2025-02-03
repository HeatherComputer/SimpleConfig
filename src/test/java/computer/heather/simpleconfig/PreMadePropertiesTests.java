package computer.heather.simpleconfig;

import computer.heather.simpleconfig.managers.PremadePropertiesManager;

class PreMadePropertiesTests extends GenericManagerTests{

    @Override
    void resetManager() {
        testManager = new PremadePropertiesManager();
    }


}
