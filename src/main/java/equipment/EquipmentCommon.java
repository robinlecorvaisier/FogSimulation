package equipment;

import data.DataInterface;
import dot.DotStylizeInterface;
import listener.equipment.EquipmentListenerInterface;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

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

    protected EquipmentInterface getNextEquipment(Graph<EquipmentInterface, DefaultEdge> network, DataInterface data) {
        EquipmentInterface destination = data.getDestination();
        return getShortestPathFirstEquipment(network, destination);
    }

    private EquipmentInterface getShortestPathFirstEquipment(Graph<EquipmentInterface, DefaultEdge> network, EquipmentInterface destination) {
        DijkstraShortestPath<EquipmentInterface, DefaultEdge> dijShtPth = new DijkstraShortestPath<>(network);
        GraphPath<EquipmentInterface, DefaultEdge> graphPath = dijShtPth.getPath(this, destination);

        List<EquipmentInterface> equipments = graphPath.getVertexList();
        equipments.remove(this);
        return equipments.get(0);
    }

    @Override
    public EquipmentListenerInterface getEquipmentDataListener() {
        return equipmentListener;
    }
}
