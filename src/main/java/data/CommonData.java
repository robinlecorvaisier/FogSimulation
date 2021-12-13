package data;

import listener.data.DataListenerInterface;

public class CommonData implements DataInterface {

    private final double size;
    private DataListenerInterface dataListener;
    private int expirationDate;

    public CommonData(double size, int expirationDate, DataListenerInterface dataListener) {
        this.dataListener = dataListener;
        this.size = size;
        this.expirationDate = expirationDate + dataListener.getIteration();
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
}
