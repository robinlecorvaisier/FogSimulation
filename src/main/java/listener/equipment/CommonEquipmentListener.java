package listener.equipment;

import data.DataInterface;

public class CommonEquipmentListener implements EquipmentListenerInterface {

    private int dataReceiptCount;
    private double dataReceiptSize;

    private int dataTransmitCount;
    private double dataTransmitSize;

    private int dataProcessedCount;
    private double dataProcessedSize;

    private int expiredDataCount;

    @Override
    public int getDataReceiptCount() {
        return dataReceiptCount;
    }

    @Override
    public double getDataReceiptSize() {
        return dataReceiptSize;
    }

    @Override
    public int getDataTransmitCount() {
        return dataTransmitCount;
    }

    @Override
    public double getDataTransmitSize() {
        return dataTransmitSize;
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
    public int getDataExpiredCount() { return expiredDataCount; }

    @Override
    public void onDataReceipt(DataInterface data) {
        dataReceiptCount++;
        dataReceiptSize += data.getSize();
    }

    @Override
    public void onDataTransmit(DataInterface data) {
        dataTransmitCount++;
        dataTransmitSize += data.getSize();
    }

    @Override
    public void onDataExpired(DataInterface data) {
        expiredDataCount++;
    }

    @Override
    public void onDataProcessed(DataInterface data) {
        dataProcessedCount++;
        dataProcessedSize += data.getSize();
    }

    @Override
    public String toString() {
        return "\\nReceipt data : " + dataReceiptCount + "(" + dataReceiptSize + ")" +
                "\\nTransmit data : " + dataTransmitCount + "(" + dataTransmitSize + ")" +
                "\\nProcessed data :" + dataProcessedCount + "(" + dataProcessedSize + ")" +
                "\\nExpired data :" + expiredDataCount;
    }
}
