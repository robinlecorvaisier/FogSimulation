package listener.equipment;

import data.DataInterface;

public class FogListener extends CommonEquipmentListener {

    private int dataFogTransmitCount;
    private double dataFogTransmitSize;

    public int getDataFogTransmitCount() {
        return dataFogTransmitCount;
    }

    public double getDataFogTransmitSize() {
        return dataFogTransmitSize;
    }

    public void onDataFogTransmit(DataInterface data) {
        dataFogTransmitCount++;
        dataFogTransmitSize += data.getSize();
    }

    @Override
    public String toString() {
        return super.toString() + "\\n\\nFog transmit data : " + getDataFogTransmitCount() + "(" + getDataFogTransmitSize() + ")";
    }
}
