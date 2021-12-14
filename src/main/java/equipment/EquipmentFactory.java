package equipment;

import data_generator.CommonDataGenerator;
import data_generator.TestDataGenerator;
import dot.DotStyliseFactory;
import hardware.processing.ProcessorFactory;
import hardware.storage.MemoryFactory;
import listener.equipment.CommonEquipmentListener;
import listener.equipment.FogListener;

import java.util.*;

public class EquipmentFactory {

    private static final Set<EquipmentInterface> fogNods = new HashSet<>();

    public static EquipmentInterface getCommonServer() {
        return new CommonServer(
                MemoryFactory.hdd500(),
                0.7,
                ProcessorFactory.getPM128(),
                0.7,
                new CommonEquipmentListener(),
                DotStyliseFactory.getServerDotNode()
        );
    }

    public static EquipmentInterface getCommonFog() {
        CommonFog fog = new CommonFog(
                MemoryFactory.hdd500ForFogNog(),
                0.7,
                ProcessorFactory.getPM128(),
                0.7,
                new FogListener(),
                DotStyliseFactory.getFogDotNode()
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
        return new CommonCluster(equipments, new CommonEquipmentListener(), DotStyliseFactory.getClusterDotNode());
    }

    public static EquipmentInterface getCommonDevice() {
        return new CommonDevice(new CommonDataGenerator(), new CommonEquipmentListener(), DotStyliseFactory.getDeviceDotNode());
    }

    public static EquipmentInterface getTestDevice() {
        return new CommonDevice(new TestDataGenerator(), new CommonEquipmentListener(), DotStyliseFactory.getDeviceDotNode());
    }
}
