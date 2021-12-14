package hardware.storage;

import data.DataInterface;

public interface StorageInterface {

    DataInterface read();

    DataInterface read(DataInterface data);

    boolean write(DataInterface data);

    double getPercentageStorage();

    double getNewPercentageStorage(DataInterface data);

    double getMaxStorage();

    double getCurrentStorage();

    boolean isEmpty();
}
