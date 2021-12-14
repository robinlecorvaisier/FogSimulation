package hardware.processing;

import data.DataInterface;
import equipment.EquipmentInterface;

public interface ProcessorInterface {

    DataInterface transmit();

    void process(DataInterface data);

    boolean charge(DataInterface data);

    double getPercentageStorage();

    double getNewPercentageStorage(DataInterface data);

    boolean hasData();

    boolean chargeToFogTransmit(DataInterface data);

    DataInterface transmitToFog();

    boolean hasDataToFog();
}
