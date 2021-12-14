package equipment;

import data.DataInterface;
import dot.DotStylizeInterface;
import listener.equipment.EquipmentListenerInterface;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;

public class CommonCluster extends EquipmentCommon implements EquipmentInterface {

    private final ArrayList<EquipmentInterface> equipments;

    public CommonCluster(ArrayList<EquipmentInterface> equipments, EquipmentListenerInterface equipmentListener, DotStylizeInterface dotStyle) {
        super(equipmentListener, dotStyle);
        this.equipments = equipments;
    }

    @Override
    public boolean receiptData(DataInterface data) {
        super.receiptData(data);
        processData(data);
        return true;
    }

    @Override
    public void action(Graph<EquipmentInterface, DefaultEdge> equipmentGraph) {
    }

    @Override
    public boolean hasData() {
        for (EquipmentInterface equipment : equipments) {
            if (equipment.hasData()) {
                return true;
            }
        }
        return false;
    }
}
