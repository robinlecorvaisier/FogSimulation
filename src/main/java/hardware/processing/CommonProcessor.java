package hardware.processing;

import data.DataInterface;
import hardware.storage.StorageInterface;

import java.util.Iterator;
import java.util.Set;

public class CommonProcessor implements ProcessorInterface {

    StorageInterface memory;
    Set<DataInterface> dataToFog;

    public CommonProcessor(StorageInterface memory, Set<DataInterface> dataToFog) {
        this.memory = memory;
        this.dataToFog = dataToFog;
    }

    @Override
    public DataInterface transmit() {
        return memory.read();
    }

    @Override
    public void process(DataInterface data) {
        data.process();
    }

    @Override
    public boolean charge(DataInterface data) {
        return memory.write(data);
    }

    @Override
    public double getPercentageStorage() {
        return memory.getPercentageStorage();
    }

    @Override
    public double getNewPercentageStorage(DataInterface data) {
        return memory.getNewPercentageStorage(data);
    }

    @Override
    public boolean hasData() {
        return !memory.isEmpty();
    }

    @Override
    public boolean chargeToFogTransmit(DataInterface data) {
        boolean isCharge = charge(data);
        if (isCharge) {
            dataToFog.add(data);
        }
        return isCharge;
    }

    @Override
    public DataInterface transmitToFog() {
        Iterator<DataInterface> it = dataToFog.iterator();
        DataInterface data = it.next();
        dataToFog.remove(data);
        return memory.read(data);
    }

    @Override
    public boolean hasDataToFog() {
        return !dataToFog.isEmpty();
    }
}
