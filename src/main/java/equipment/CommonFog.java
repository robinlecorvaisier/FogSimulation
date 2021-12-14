package equipment;

import data.DataInterface;
import dot.DotStylizeInterface;
import hardware.processing.ProcessorInterface;
import hardware.storage.StorageInterface;
import listener.equipment.EquipmentListenerInterface;
import listener.equipment.FogListener;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ManyToManyShortestPathsAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraManyToManyShortestPaths;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashSet;
import java.util.Set;

public class CommonFog extends CommonServer {


    public CommonFog(StorageInterface storage, double percentageStorageThreshold, ProcessorInterface processor, double percentageProcessorThreshold, EquipmentListenerInterface equipmentListener, DotStylizeInterface dotStyle) {
        super(storage, percentageStorageThreshold, processor, percentageProcessorThreshold, equipmentListener, dotStyle);
    }

    @Override
    protected void putInCPUCache() {
        while (this.hasData() && processor.getPercentageStorage() < percentageProcessorThreshold) {
            DataInterface dataRead = storage.read();
            if (!processor.charge(dataRead)) {
                storage.write(dataRead);
                break;
            }
        }

        while (hasData()) {
            DataInterface dataRead = storage.read();
            if (!hasToProcess(dataRead)) {
                storage.write(dataRead);
                return;
            }
            if (!processor.chargeToFogTransmit(dataRead)) {
                storage.write(dataRead);
                return;
            }
        }
    }

    @Override
    protected void processCPU(Graph<EquipmentInterface, DefaultEdge> equipmentGraph) {

        while (processor.hasDataToFog()) {
            DataInterface dataToFog = processor.transmitToFog();
            EquipmentInterface nearestFog = findTheNearestFog(equipmentGraph);
            this.transmitData(nearestFog, dataToFog);
            FogListener fogListener = (FogListener) equipmentListener;
            fogListener.onDataFogTransmit(dataToFog);
        }

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

    protected EquipmentInterface findTheNearestFog(Graph<EquipmentInterface, DefaultEdge> equipementGraph) {

        Set<EquipmentInterface> otherFogs = EquipmentFactory.getFogNods();
        otherFogs.remove(this);

        if (!otherFogs.isEmpty()) {
            DijkstraManyToManyShortestPaths<EquipmentInterface, DefaultEdge> dijkstra =
                    new DijkstraManyToManyShortestPaths<>(equipementGraph);
            Set<EquipmentInterface> source = new HashSet<>();
            source.add(this);

            ManyToManyShortestPathsAlgorithm.ManyToManyShortestPaths<EquipmentInterface, DefaultEdge> shortestPaths =
                    dijkstra.getManyToManyPaths(source, otherFogs);

            EquipmentInterface nextFog = shortestPaths.getTargets().iterator().next();

            if (shortestPaths.getTargets().iterator().hasNext()) {
                nextFog = shortestPaths.getTargets().iterator().next();
            }
            return nextFog;
        }

        return getNextEquipment(equipementGraph);
    }
}
