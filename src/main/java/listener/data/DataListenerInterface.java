package listener.data;

import data.DataInterface;

public interface DataListenerInterface {

    void onIterationChange();

    int getIteration();

    void onDataProcessed(DataInterface data);

    void onDataLost(DataInterface data);

    void onDataExpired(DataInterface data);

    public int getDataProcessedCount();

    public double getDataProcessedSize();

    public int getDataLostCount();

    public double getDataLostSize();

    public int getDataExpiredCount();

    public double getDataExpiredSize();

    public double getAverageDataProcessedByIteration();

}
