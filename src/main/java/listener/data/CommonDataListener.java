package listener.data;

import data.DataInterface;

public class CommonDataListener implements DataListenerInterface {

    private int iteration;

    private int dataProcessedCount;
    private double dataProcessedSize;

    private int dataLostCount;
    private double dataLostSize;

    private int dataExpiredCount;
    private double dataExpiredSize;

    private int iterationHasBeenProcess;

    @Override
    public void onIterationChange() {
        iteration++;
    }

    @Override
    public int getIteration() {
        return iteration;
    }

    @Override
    public void onDataProcessed(DataInterface data) {
        dataProcessedCount++;
        dataProcessedSize += data.getSize();
        iterationHasBeenProcess += data.getIterationHasBeenProcess();
    }

    @Override
    public void onDataLost(DataInterface data) {
        dataLostCount++;
        dataLostSize += data.getSize();
    }

    @Override
    public void onDataExpired(DataInterface data) {
        dataExpiredCount++;
        dataExpiredSize += data.getSize();
    }

    @Override
    public int getDataProcessedCount() {
        return dataProcessedCount;
    }

    @Override
    public double getDataProcessedSize() {
        return dataProcessedSize;
    }

    @Override
    public int getDataLostCount() {
        return dataLostCount;
    }

    @Override
    public double getDataLostSize() {
        return dataLostSize;
    }

    @Override
    public int getDataExpiredCount() {
        return dataExpiredCount;
    }

    @Override
    public double getDataExpiredSize() {
        return dataExpiredSize;
    }

    @Override
    public double getAverageDataProcessedByIteration() {
        return (iterationHasBeenProcess/(double)dataProcessedCount);
    }

    @Override
    public String toString() {
        return "\\nData lost : " + dataLostCount + "(" + dataLostSize + ")" +
                "\\nData Expired : " + dataExpiredCount + "(" + dataExpiredSize + ")" +
                "\\nData Processed :" + dataProcessedCount + "(" + dataProcessedSize + ")" +
                "\\nAverage iteration to process : " + (iterationHasBeenProcess/(double)dataProcessedCount);
    }
}
