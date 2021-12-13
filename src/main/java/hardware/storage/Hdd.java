package hardware.storage;

import data.DataInterface;

import java.util.Set;

public class Hdd extends Memory {

    public Hdd(float maxStorage, Set<DataInterface> dataSet) {
        super(maxStorage, dataSet);
    }
}
