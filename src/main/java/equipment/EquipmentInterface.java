package equipment;

import data.DataInterface;
import listener.equipment.EquipmentListenerInterface;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public interface EquipmentInterface {

    boolean transmitData(EquipmentInterface equipmentReceptor, DataInterface data);

    boolean receiptData(DataInterface data);

    void action(Graph<EquipmentInterface, DefaultEdge> equipmentGraph);

    boolean hasData();

    void setName(String equipmentName);

    String getName();

    EquipmentListenerInterface getEquipmentDataListener();

}
