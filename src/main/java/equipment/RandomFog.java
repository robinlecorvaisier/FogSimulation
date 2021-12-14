package equipment;

import dot.DotStylizeInterface;
import hardware.processing.ProcessorInterface;
import hardware.storage.StorageInterface;
import listener.equipment.EquipmentListenerInterface;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Random;
import java.util.Set;

public class RandomFog extends CommonFog {
    public RandomFog(StorageInterface storage, double percentageStorageThreshold, ProcessorInterface processor, double percentageProcessorThreshold, EquipmentListenerInterface equipmentListener, DotStylizeInterface dotStyle) {
        super(storage, percentageStorageThreshold, processor, percentageProcessorThreshold, equipmentListener, dotStyle);
    }

    @Override
    protected EquipmentInterface findTheNearestFog(Graph<EquipmentInterface, DefaultEdge> equipementGraph) {
        Set<EquipmentInterface> otherFogs = EquipmentFactory.getFogNods();
        otherFogs.remove(this);

        if (!otherFogs.isEmpty()) {
            int item = new Random().nextInt(otherFogs.size());
            int i = 0;

            for(EquipmentInterface e: otherFogs){

                if(i == item){
                    return e;
                }
                i++;
            }
        }

        return EquipmentFactory.getCreateCluster();
    }
}
