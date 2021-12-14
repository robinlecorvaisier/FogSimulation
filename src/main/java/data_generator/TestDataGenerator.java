package data_generator;

import data.DataFactory;
import data.DataInterface;

public class TestDataGenerator extends CommonDataGenerator {
    @Override
    public DataInterface generate() {
        return DataFactory.getDataTest(30);
    }
}
