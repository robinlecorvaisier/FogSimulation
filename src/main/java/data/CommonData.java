package data;

import equipment.EquipmentInterface;
import listener.data.DataListenerInterface;

public class CommonData implements DataInterface, Comparable<DataInterface> {

    private final double size;
    private DataListenerInterface dataListener;
    private int expiration;
    private int expirationDate;
    private EquipmentInterface destination;
    private int creationDate;

    public CommonData(double size, int expirationDate, DataListenerInterface dataListener, EquipmentInterface destination) {
        this.dataListener = dataListener;
        this.size = size;
        this.expiration = expirationDate;
        this.expirationDate = expirationDate + dataListener.getIteration();
        this.destination = destination;
        this.creationDate = dataListener.getIteration();
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public int getPriority() {
        return expirationDate - dataListener.getIteration();
    }

    @Override
    public void process() {
        dataListener.onDataProcessed(this);
    }

    @Override
    public boolean isExpired() {
        return getPriority() <= 0;
    }

    @Override
    public void expire() {
        dataListener.onDataExpired(this);
    }

    @Override
    public void lost() {
        dataListener.onDataLost(this);
    }

    @Override
    public int compareTo(DataInterface data) {
        return Integer.compare(this.getPriority(), data.getPriority());
    }

    @Override
    public EquipmentInterface getDestination() {
        return destination;
    }

    @Override
    public void setDestination(EquipmentInterface destination) {
        this.destination = destination;
    }

    @Override
    public double getIterationHasBeenProcess() {
        return (this.dataListener.getIteration() + 2)  - (this.creationDate + 1);
    }
}
