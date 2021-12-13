package equipment;

import data.DataInterface;
import listener.equipment.EquipmentListenerInterface;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;

public class CommonCluster extends EquipmentCommon implements EquipmentInterface {

    private final ArrayList<EquipmentInterface> equipments;

    public CommonCluster(ArrayList<EquipmentInterface> equipments, EquipmentListenerInterface equipmentListener) {
        super(equipmentListener);
        this.equipments = equipments;
    }

    @Override
    public boolean receiptData(DataInterface data) {
        super.receiptData(data);
        for (EquipmentInterface equipment : equipments) {
            if (equipment.receiptData(data)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void action(Graph<EquipmentInterface, DefaultEdge> equipmentGraph) {
//        for (EquipmentInterface equipment : this.equipments) {
//            equipment.action(equipmentGraph);
//        }
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
