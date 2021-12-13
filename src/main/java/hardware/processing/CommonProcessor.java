package hardware.processing;

import data.DataInterface;
import hardware.storage.StorageInterface;

public class CommonProcessor implements ProcessorInterface {

    StorageInterface memory;

    public CommonProcessor(StorageInterface memory) {
        this.memory = memory;
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
}
