package equipment;

import data.DataInterface;
import listener.equipment.EquipmentListenerInterface;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Set;

public abstract class EquipmentCommon implements EquipmentInterface {

    private String equipmentName;

    protected EquipmentListenerInterface equipmentListener;

    public EquipmentCommon(EquipmentListenerInterface equipmentListener) {
        this.equipmentName = this.getClass().getSimpleName();
        this.equipmentListener = equipmentListener;
    }

    @Override
    public String toString() {
        return equipmentName + equipmentListener;
    }

    @Override
    public String getName() {
        return equipmentName;
    }

    @Override
    public boolean transmitData(EquipmentInterface equipmentReceptor, DataInterface data) {
        equipmentListener.onDataTransmit(data);
        return equipmentReceptor.receiptData(data);
    }

    @Override
    public boolean receiptData(DataInterface data) {
        equipmentListener.onDataReceipt(data);
        return true;
    }

    @Override
    public void setName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    protected EquipmentInterface getNextEquipment(Graph<EquipmentInterface, DefaultEdge> network) {
        Set<DefaultEdge> outgoingEdges = network.outgoingEdgesOf(this);
        if (outgoingEdges.iterator().hasNext()) {
            return network.getEdgeTarget(outgoingEdges.iterator().next());
        }
        return null;
    }

    @Override
    public EquipmentListenerInterface getEquipmentDataListener() {
        return equipmentListener;
    }
}
