package equipment;

import data.DataInterface;
import dot.DotStylizeInterface;
import listener.equipment.EquipmentListenerInterface;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Set;

public abstract class EquipmentCommon implements EquipmentInterface {

    private String equipmentName;

    protected EquipmentListenerInterface equipmentListener;

    private final DotStylizeInterface dotStyle;

    public EquipmentCommon(EquipmentListenerInterface equipmentListener, DotStylizeInterface dotStyle) {
        this.equipmentName = super.toString();
        this.equipmentListener = equipmentListener;
        this.dotStyle = dotStyle;
    }

    @Override
    public String toString() {
        return equipmentName + equipmentListener;
    }

    @Override
    public DotStylizeInterface getDotStylise() {
        dotStyle.setLabel(this.toString());
        return dotStyle;
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

    protected void processData(DataInterface data) {
        equipmentListener.onDataProcessed(data);
        data.process();
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
