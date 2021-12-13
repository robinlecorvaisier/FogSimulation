package hardware.storage;

import data.DataInterface;

import java.util.ArrayList;

public class Memory implements StorageInterface {

    private final ArrayList<DataInterface> dataInterfaces;
    private final float maxStorage;
    private double currentStorage;

    public Memory(float maxStorage) {
        dataInterfaces = new ArrayList<>();
        this.maxStorage = maxStorage;
    }

    @Override
    public DataInterface read() {
        DataInterface dataRead = dataInterfaces.remove(0);
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

    public ArrayList<DataInterface> getDataInterfaces() {
        return dataInterfaces;
    }

    @Override
    public boolean isEmpty() {
        return dataInterfaces.isEmpty();
    }
}
