package data_generator;

import data.DataFactory;
import data.DataInterface;

public class CommonDataGenerator implements DataGeneratorInterface {

    @Override
    public DataInterface generate() {
        return DataFactory.getData();
    }
}
