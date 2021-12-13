package hardware.storage;

import data.DataInterface;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Memory implements StorageInterface {

    private final Set<DataInterface> dataInterfaces;
    private final float maxStorage;
    private double currentStorage;

    public Memory(float maxStorage, Set<DataInterface> dataSet) {
        dataInterfaces = dataSet;
        this.maxStorage = maxStorage;
    }

    @Override
    public DataInterface read() {
        Iterator<DataInterface> it = dataInterfaces.iterator();
        DataInterface dataRead = it.next();
        dataInterfaces.remove(dataRead);
        currentStorage -= dataRead.getSize();
        return dataRead;
    }

    @Override
    public boolean write(DataInterface data) {
        if (hasEnoughStorage(data)) {
            dataInterfaces.add(data);
            currentStorage += data.getSize();
            return true;
        }
        return false;
    }

    private boolean hasEnoughStorage(DataInterface data) {
        return currentStorage + data.getSize() < maxStorage;
    }

    @Override
    public double getPercentageStorage() {
        return currentStorage / maxStorage;
    }

    @Override
    public double getNewPercentageStorage(DataInterface data) {
        return (currentStorage + data.getSize()) / maxStorage;
    }

    @Override
    public double getMaxStorage() {
        return maxStorage;
    }

    @Override
    public double getCurrentStorage() {
        return currentStorage;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + maxStorage;
    }

    public Set<DataInterface> getDataInterfaces() {
        return dataInterfaces;
    }

    @Override
    public boolean isEmpty() {
        return dataInterfaces.isEmpty();
    }
}
