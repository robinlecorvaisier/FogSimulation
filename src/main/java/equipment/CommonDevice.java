package equipment;

import data.DataInterface;
import data_generator.DataGeneratorInterface;
import dot.DotStylizeInterface;
import listener.equipment.EquipmentListenerInterface;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Objects;

public class CommonDevice extends EquipmentCommon implements EquipmentInterface {

    protected DataGeneratorInterface dataGenerator;

    public CommonDevice(DataGeneratorInterface dataGenerator, EquipmentListenerInterface equipmentListener, DotStylizeInterface dotStyle) {
        super(equipmentListener, dotStyle);
        this.dataGenerator = dataGenerator;
    }

    @Override
    public boolean receiptData(DataInterface data) {
        return true;
    }

    @Override
    public void action(Graph<EquipmentInterface, DefaultEdge> equipmentGraph) {
        DataInterface data = dataGenerator.generate();
        EquipmentInterface nextEquipment = getNextEquipment(equipmentGraph, data);
        if (!Objects.isNull(nextEquipment)) {
            this.transmitData(nextEquipment, data);
        }
    }

    @Override
    public boolean hasData() {
        return true;
    }
}
