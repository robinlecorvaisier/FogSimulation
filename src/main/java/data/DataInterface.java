package data;

import equipment.EquipmentInterface;

public interface DataInterface extends Comparable<DataInterface> {

    double getSize();

    int getPriority();

    void process();

    boolean isExpired();

    void expire();

    void lost();

    EquipmentInterface getDestination();

    void setDestination(EquipmentInterface destination);

    double getIterationHasBeenProcess();
}
