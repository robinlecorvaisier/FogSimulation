package network;

import equipment.EquipmentInterface;
import graphviz.GraphVizFactory;
import listener.equipment.EquipmentListenerInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

class CommonNetworkTest {

    @Test
    void testToString() throws IOException {

        NetworkInterface network = NetworkFactory.commonTestNetwork();
        network.setNetworkName("test network");
        System.out.println(network);
        System.out.println(network.getDot());

        for (int i = 0; i < 30; i++) {
            network.iterate();
        }

        GraphVizFactory.generateSvg(network);
    }

    @Test
    void testHugeNetwork() throws IOException {

        NetworkInterface network = NetworkFactory.hugeTestWithFogNetwork();

        for (int i = 0; i < 100; i++) {
            network.iterate();
        }

        GraphVizFactory.generateSvg(network);
    }

    @Test
    void testDemoWithFogNetwork() throws IOException {

        NetworkInterface network = NetworkFactory.demoWithFogsNetwork();

        for (int i = 0; i < 100; i++) {
            network.iterate();
        }

        GraphVizFactory.generateSvg(network);
    }

    @Test
    void testDemoWithoutFogNetwork() throws IOException {

        NetworkInterface network = NetworkFactory.demoNoFogsNetwork();

        for (int i = 0; i < 100; i++) {
            network.iterate();
        }

        GraphVizFactory.generateSvg(network);
    }

    @Test
    void simpleIteration() throws IOException {
        NetworkInterface network = NetworkFactory.simpleTestNetwork();
        for (int i = 0; i < 3; i++) {
            network.iterate();
        }

        double[][] expectedValues = new double[][]{
                new double[]{0, 0, 3, 300, 0, 0, 0},
                new double[]{3, 300, 3, 300, 0, 0, 0},
                new double[]{3, 300, 0, 0, 0, 0, 0},
        };

        GraphVizFactory.generateSvg(network);
        Iterator<EquipmentInterface> it = network.getNodes().iterator();
        for (int i = 0; i < 3; i++) {
            checkEquipmentListener(
                    it.next().getEquipmentDataListener(),
                    expectedValues[i]
            );
        }
    }

    @Test
    void simpleIterationTest() throws IOException {
        NetworkInterface network = NetworkFactory.simpleTestNetwork();
        for (int i = 0; i < 3; i++) {
            network.iterate();
        }
    }


    private void checkEquipmentListener(EquipmentListenerInterface equipmentListener, double[] expectedValues) {

        double[] actualValues = new double[]{
                equipmentListener.getDataReceiptCount(),
                equipmentListener.getDataReceiptSize(),
                equipmentListener.getDataTransmitCount(),
                equipmentListener.getDataTransmitSize(),
                equipmentListener.getDataProcessedCount(),
                equipmentListener.getDataProcessedSize(),
                equipmentListener.getDataExpiredCount(),
        };

        Assertions.assertArrayEquals(expectedValues, actualValues);

    }
}