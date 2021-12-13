package listener.equipment;

import data.DataInterface;

public interface EquipmentListenerInterface {
    int getDataReceiptCount();

    double getDataReceiptSize();

    int getDataTransmitCount();

    double getDataTransmitSize();

    int getDataProcessedCount();

    double getDataProcessedSize();

    int getDataExpiredCount();

    void onDataReceipt(DataInterface data);

    void onDataTransmit(DataInterface data);

    void onDataExpired(DataInterface data);

    void onDataProcessed(DataInterface data);
}
