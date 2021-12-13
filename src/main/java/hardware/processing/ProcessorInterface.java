package hardware.processing;

import data.DataInterface;

public interface ProcessorInterface {

    DataInterface transmit();

    void process(DataInterface data);

    boolean charge(DataInterface data);

    double getPercentageStorage();

    double getNewPercentageStorage(DataInterface data);

    boolean hasData();
}
