package equipment;

import data_generator.CommonDataGenerator;
import data_generator.TestDataGenerator;
import hardware.processing.ProcessorFactory;
import hardware.storage.MemoryFactory;
import listener.equipment.CommonEquipmentListener;

import java.util.*;

public class EquipmentFactory {

    private static final Set<EquipmentInterface> fogNods = new HashSet<>();

    public static EquipmentInterface getCommonServer() {
        return new CommonServer(
                MemoryFactory.hdd500(),
                0.7,
                ProcessorFactory.getPM128(),
                0.7,
                new CommonEquipmentListener()
        );
    }

    public static EquipmentInterface getCommonFog() {
        CommonFog fog = new CommonFog(
                MemoryFactory.hdd500(),
                0.7,
                ProcessorFactory.getPM128(),
                0.7,
                new CommonEquipmentListener()
        );

        fogNods.add(fog);
        return fog;
    }

    public static Set<EquipmentInterface> getFogNods() {
        return fogNods;
    }

    public static EquipmentInterface getCluster() {
        ArrayList<EquipmentInterface> equipments = new ArrayList<>();
        equipments.add(getCommonServer());
        equipments.add(getCommonServer());
        equipments.add(getCommonServer());
        return new CommonCluster(equipments, new CommonEquipmentListener());
    }

    public static EquipmentInterface getCommonDevice() {
            return new CommonDevice(new CommonDataGenerator(), new CommonEquipmentListener());
    }

    public static EquipmentInterface getTestDevice() {
        return new CommonDevice(new TestDataGenerator(), new CommonEquipmentListener());
    }
}
