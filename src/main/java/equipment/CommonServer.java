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

        while (this.hasData() && processor.getPercentageStorage() < percentageProcessorThreshold) {
            DataInterface dataRead = storage.read();

            if (isExpiredData(dataRead)) {
                continue;
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

    private boolean isExpiredData(DataInterface data) {
        if (data.isExpired()) {
            data.expire();
            return true;
        }
        return false;
    }

    @Override
    public boolean receiptData(DataInterface data) {
        super.receiptData(data);
        if (!willExceedThresholdStorage(data)) {
            return storage.write(data);
        }
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
