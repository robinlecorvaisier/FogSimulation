package equipment;

import data.DataInterface;
import hardware.processing.ProcessorInterface;
import hardware.storage.StorageInterface;
import listener.equipment.EquipmentListenerInterface;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ManyToManyShortestPathsAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraManyToManyShortestPaths;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashSet;
import java.util.Set;

public class CommonFog extends CommonServer {

    public CommonFog(StorageInterface storage, double percentageStorageThreshold, ProcessorInterface processor,
                     double percentageProcessorThreshold, EquipmentListenerInterface equipmentListener) {
        super(storage, percentageStorageThreshold, processor, percentageProcessorThreshold, equipmentListener);
    }

    @Override
    protected void putInCPUCache() {
        super.putInCPUCache();
    }

    @Override
    protected void processCPU(Graph<EquipmentInterface, DefaultEdge> equipmentGraph) {
        EquipmentInterface nextEquipment = this.getNextEquipment(equipmentGraph);

        while (processor.hasData()) {
            DataInterface dataReadFromCPU = processor.transmit();

            if (isExpiredData(dataReadFromCPU)) {
                continue;
            }

            if (hasToProcess(dataReadFromCPU)) {
                processData(dataReadFromCPU);
            }

            this.transmitData(nextEquipment, dataReadFromCPU);
        }
    }

    public boolean hasToProcess(DataInterface data) {
        return data.getPriority() <= 3;
    }

    public EquipmentInterface findTheNearestFog(Graph<EquipmentInterface, DefaultEdge> equipementGraph) {
        DijkstraManyToManyShortestPaths<EquipmentInterface, DefaultEdge> dijkstra =
                new DijkstraManyToManyShortestPaths<>(equipementGraph);
        Set<EquipmentInterface> source = new HashSet<>();
        source.add(this);

        ManyToManyShortestPathsAlgorithm.ManyToManyShortestPaths<EquipmentInterface, DefaultEdge> shortestPaths =
                dijkstra.getManyToManyPaths(source, EquipmentFactory.getFogNods());

        EquipmentInterface nextFog = shortestPaths.getTargets().iterator().next();

        if (nextFog.equals(this) && shortestPaths.getTargets().iterator().hasNext()) {
            nextFog = shortestPaths.getTargets().iterator().next();
        }

        return nextFog;
    }
}
