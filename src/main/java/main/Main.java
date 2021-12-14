package main;

import data.DataFactory;
import equipment.EquipmentFactory;
import network.NetworkFactory;
import network.NetworkInterface;

import javax.xml.crypto.Data;

public class Main {

    public static void main(String[] args) {

        for (int y = 0; y < 10000; y++) {
            NetworkInterface network = NetworkFactory.demoWithRandomNetwork();
            for (int i = 0; i < 100; i++) {
                network.iterate();
            }
            Output.edit(DataFactory.getDataListener().getDataExpiredCount(), 0, "random");
            EquipmentFactory.clear();
            DataFactory.clear();
        }

        for (int y = 0; y < 10000; y++) {
            NetworkInterface network = NetworkFactory.demoWithFogsNetwork();
            for (int i = 0; i < 100; i++) {
                network.iterate();
            }
            Output.edit(DataFactory.getDataListener().getDataExpiredCount(), 0, "fogGraph");
            EquipmentFactory.clear();
            DataFactory.clear();
        }

        for (int y = 0; y < 10000; y++) {
            NetworkInterface network = NetworkFactory.demoNoFogsNetwork();
            for (int i = 0; i < 100; i++) {
                network.iterate();
            }
            Output.edit(DataFactory.getDataListener().getDataExpiredCount(), 0, "NoFogs");
            EquipmentFactory.clear();
            DataFactory.clear();
        }
    }
}
