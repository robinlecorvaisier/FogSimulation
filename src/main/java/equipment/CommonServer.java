package equipment;

import data.DataInterface;
import hardware.processing.ProcessorInterface;
import hardware.storage.StorageInterface;
import listener.equipment.EquipmentListenerInterface;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class CommonServer extends EquipmentCommon {

    protected StorageInterface storage;
    protected double percentageStorageThreshold;

    protected ProcessorInterface processor;
    protected double percentageProcessorThreshold;

    public CommonServer(StorageInterface storage, double percentageStorageThreshold, ProcessorInterface processor, double percentageProcessorThreshold, EquipmentListenerInterface equipmentListener) {
        super(equipmentListener);
        this.storage = storage;
        this.percentageStorageThreshold = percentageStorageThreshold;
        this.processor = processor;
        this.percentageProcessorThreshold = percentageProcessorThreshold;
    }

    @Override
    public void action(Graph<EquipmentInterface, DefaultEdge> equipmentGraph) {
        putInCPUCache();
        processCPU(equipmentGraph);
    }

    protected void putInCPUCache() {
        while (this.hasData() && processor.getPercentageStorage() < percentageProcessorThreshold) {
            DataInterface dataRead = storage.read();
            if (!processor.charge(dataRead)) {
                storage.write(dataRead);
                return;
            }
        }
    }

    protected void processCPU(Graph<EquipmentInterface, DefaultEdge> equipmentGraph) {
        EquipmentInterface nextEquipment = this.getNextEquipment(equipmentGraph);

        while (processor.hasData()) {
            DataInterface dataReadFromCPU = processor.transmit();

            if (isExpiredData(dataReadFromCPU)) {
                continue;
            }
            this.transmitData(nextEquipment, dataReadFromCPU);
        }
    }

    protected boolean isExpiredData(DataInterface data) {
        if (data.isExpired()) {
            data.expire();
            equipmentListener.onDataExpired(data);
            return true;
        }
        return false;
    }

    @Override
    public boolean receiptData(DataInterface data) {
        if (!willExceedThresholdStorage(data)) {
            super.receiptData(data);
            return storage.write(data);
        }
        equipmentListener.onDataLost(data);
        data.lost();
        return false;
    }

    private boolean willExceedThresholdStorage(DataInterface data) {
        return percentageStorageThreshold < storage.getNewPercentageStorage(data);
    }

    @Override
    public boolean hasData() {
        return !storage.isEmpty();
    }
}
