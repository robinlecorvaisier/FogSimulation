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
    public void action(Graph<EquipmentInterface, DefaultEdge> equipmentGraph) {
        while (this.hasData() && processor.getPercentageStorage() < percentageProcessorThreshold) {
            DataInterface dataRead = storage.read();

            if (super.isExpiredData(dataRead)) {
                continue;
            } else if(hasToProcess(dataRead)){
                dataRead.process();
                equipmentListener.onDataProcessed(dataRead);
            } else {
                EquipmentInterface nearestFog = findTheNearestFog(equipmentGraph);
                this.transmitData(nearestFog, processor.transmit());
            }

            if (!processor.charge(dataRead)) {
                storage.write(dataRead);
                return;
            }

        }


        EquipmentInterface nextEquipment = this.getNextEquipment(equipmentGraph);
        while (processor.hasData()) {
            this.transmitData(nextEquipment, processor.transmit());
        }
    }

    public boolean hasToProcess(DataInterface data){
        return data.getPriority() <= 3;
    }

    public EquipmentInterface findTheNearestFog(Graph<EquipmentInterface, DefaultEdge> equipementGraph){
        DijkstraManyToManyShortestPaths<EquipmentInterface,DefaultEdge> dijkstra =
                new DijkstraManyToManyShortestPaths<>(equipementGraph);
        Set<EquipmentInterface> source = new HashSet<>();
        source.add(this);

        ManyToManyShortestPathsAlgorithm.ManyToManyShortestPaths<EquipmentInterface, DefaultEdge> shortestPaths =
                dijkstra.getManyToManyPaths(source, EquipmentFactory.getFogNods());

        EquipmentInterface nextFog = shortestPaths.getTargets().iterator().next();

        if(nextFog.equals(this) && shortestPaths.getTargets().iterator().hasNext()){
            nextFog = shortestPaths.getTargets().iterator().next();
        }

        return nextFog;
    }
}
