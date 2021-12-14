package network;

import equipment.EquipmentFactory;
import equipment.EquipmentInterface;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.HashMap;
import java.util.Map;

public class NetworkFactory {


    public static NetworkInterface simpleTestNetwork() {

        Graph<EquipmentInterface, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);

        EquipmentInterface device = EquipmentFactory.getTestDevice();
        EquipmentInterface server = EquipmentFactory.getCommonServer();
        EquipmentInterface cluster = EquipmentFactory.getCluster();

        device.setName("device");
        server.setName("server");
        cluster.setName("cluster");

        graph.addVertex(device);
        graph.addVertex(server);
        graph.addVertex(cluster);

        graph.addEdge(device, server);
        graph.addEdge(server, cluster);


        NetworkInterface network = new CommonNetwork(graph);
        network.setNetworkName("simpleTestNetwork");
        return network;
    }

    public static NetworkInterface commonTestNetwork() {

        Graph<EquipmentInterface, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);


        Map<String, EquipmentInterface> vertex = new HashMap<>();

//        vertex.put("S1", EquipmentFactory.getCommonServer());
//        vertex.put("S2", EquipmentFactory.getCommonServer());
//        vertex.put("S3", EquipmentFactory.getCommonServer());
//        vertex.put("Cluster 1", EquipmentFactory.getCluster());
//        vertex.put("Device 1", EquipmentFactory.getCommonDevice());


        EquipmentInterface server1 = EquipmentFactory.getCommonServer();
        EquipmentInterface server2 = EquipmentFactory.getCommonServer();
        EquipmentInterface server3 = EquipmentFactory.getCommonServer();
        EquipmentInterface cluster = EquipmentFactory.getCluster();
        EquipmentInterface device1 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device2 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device3 = EquipmentFactory.getCommonDevice();

        server1.setName("S1");
        server2.setName("S2");
        server3.setName("S3");
        cluster.setName("Cluster 1");
        device1.setName("Device 1");
        device2.setName("Device 2");
        device3.setName("Device 3");

        graph.addVertex(device1);
        graph.addVertex(device2);
        graph.addVertex(device3);
        graph.addVertex(server2);
        graph.addVertex(server3);
        graph.addVertex(server1);
        graph.addVertex(cluster);

        graph.addEdge(server1, cluster);
        graph.addEdge(server2, server1);
        graph.addEdge(server3, server1);
        graph.addEdge(device1, server2);
        graph.addEdge(device2, server2);
        graph.addEdge(device3, server3);

        return new CommonNetwork(graph);
    }

    public static NetworkInterface hugeTestNetwork() {

        Graph<EquipmentInterface, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);

        Map<String, EquipmentInterface> vertex = new HashMap<>();

        EquipmentInterface server1 = EquipmentFactory.getCommonServer();
        EquipmentInterface server2 = EquipmentFactory.getCommonServer();
        EquipmentInterface server3 = EquipmentFactory.getCommonServer();
        EquipmentInterface server4 = EquipmentFactory.getCommonServer();
        EquipmentInterface server5 = EquipmentFactory.getCommonServer();

        EquipmentInterface cluster = EquipmentFactory.getCluster();

        EquipmentInterface device1 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device2 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device3 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device4 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device5 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device6 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device7 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device8 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device9 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device10 = EquipmentFactory.getCommonDevice();

        graph.addVertex(device1);
        graph.addVertex(device2);
        graph.addVertex(device3);
        graph.addVertex(device4);
        graph.addVertex(device5);
        graph.addVertex(device6);
        graph.addVertex(device7);
        graph.addVertex(device8);
        graph.addVertex(device9);
        graph.addVertex(device10);

        graph.addVertex(server1);
        graph.addVertex(server2);
        graph.addVertex(server3);
        graph.addVertex(server4);
        graph.addVertex(server5);

        graph.addVertex(cluster);

        graph.addEdge(server1, cluster);
        graph.addEdge(server4, cluster);
        graph.addEdge(server5, cluster);

        graph.addEdge(server2, server1);
        graph.addEdge(server3, server5);

        graph.addEdge(device1, server2);
        graph.addEdge(device2, server2);
        graph.addEdge(device3, server2);
        graph.addEdge(device4, server2);
        graph.addEdge(device5, server2);
        graph.addEdge(device6, server3);
        graph.addEdge(device7, server3);
        graph.addEdge(device8, server3);
        graph.addEdge(device9, server1);
        graph.addEdge(device10, server4);


        NetworkInterface network = new CommonNetwork(graph);
        network.setNetworkName("huge test network");
        return network;
    }

    public static NetworkInterface hugeTestWithFogNetwork() {

        Graph<EquipmentInterface, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);

        Map<String, EquipmentInterface> vertex = new HashMap<>();

        EquipmentInterface server1 = EquipmentFactory.getCommonFog();
        EquipmentInterface server2 = EquipmentFactory.getCommonFog();
        EquipmentInterface server3 = EquipmentFactory.getCommonServer();
        EquipmentInterface server4 = EquipmentFactory.getCommonFog();
        EquipmentInterface server5 = EquipmentFactory.getCommonServer();

        server1.setName("server Fog 1");
        server2.setName("server2");
        server3.setName("server3");
        server4.setName("server4");
        server5.setName("server5");


        EquipmentInterface cluster = EquipmentFactory.getCluster();

        EquipmentInterface device1 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device2 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device3 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device4 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device5 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device6 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device7 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device8 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device9 = EquipmentFactory.getCommonDevice();
        EquipmentInterface device10 = EquipmentFactory.getCommonDevice();

        graph.addVertex(device1);
        graph.addVertex(device2);
        graph.addVertex(device3);
        graph.addVertex(device4);
        graph.addVertex(device5);
        graph.addVertex(device6);
        graph.addVertex(device7);
        graph.addVertex(device8);
        graph.addVertex(device9);
        graph.addVertex(device10);

        graph.addVertex(server1);
        graph.addVertex(server2);
        graph.addVertex(server3);
        graph.addVertex(server4);
        graph.addVertex(server5);

        graph.addVertex(cluster);

        graph.addEdge(server1, cluster);
        graph.addEdge(server4, cluster);
        graph.addEdge(server5, cluster);

        graph.addEdge(server2, server1);
        graph.addEdge(server3, server5);

        graph.addEdge(device1, server2);
        graph.addEdge(device2, server2);
        graph.addEdge(device3, server2);
        graph.addEdge(device4, server2);
        graph.addEdge(device5, server2);
        graph.addEdge(device6, server3);
        graph.addEdge(device7, server3);
        graph.addEdge(device8, server3);
        graph.addEdge(device9, server1);
        graph.addEdge(device10, server4);

        //fog connexion
        graph.addEdge(server1, server4);
        graph.addEdge(server4, server1);
        graph.addEdge(server1, server2);
        graph.addEdge(server2, server1);

        NetworkInterface network = new CommonNetwork(graph);
        network.setNetworkName("huge test network with fog");
        return network;
    }

    public static NetworkInterface demoWithFogsNetwork() {

        Graph<EquipmentInterface, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);

        Map<String, EquipmentInterface> vertex = new HashMap<>();

        EquipmentInterface F1 = EquipmentFactory.getCommonFog();
        EquipmentInterface F2 = EquipmentFactory.getCommonFog();
        EquipmentInterface F3 = EquipmentFactory.getCommonFog();
        EquipmentInterface F4 = EquipmentFactory.getCommonFog();
        EquipmentInterface F5 = EquipmentFactory.getCommonFog();
        EquipmentInterface F6 = EquipmentFactory.getCommonFog();

        F1.setName("F1");
        F2.setName("F2");
        F3.setName("F3");
        F4.setName("F4");
        F5.setName("F5");
        F6.setName("F6");

        EquipmentInterface S1 = EquipmentFactory.getCommonServer();
        EquipmentInterface S2 = EquipmentFactory.getCommonServer();
        EquipmentInterface S3 = EquipmentFactory.getCommonServer();
        EquipmentInterface S4 = EquipmentFactory.getCommonServer();
        EquipmentInterface S5 = EquipmentFactory.getCommonServer();

        S1.setName("S1");
        S2.setName("S2");
        S3.setName("S3");
        S4.setName("S4");
        S5.setName("S5");

        EquipmentInterface D1 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D2 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D3 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D4 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D5 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D6 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D7 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D8 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D9 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D10 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D11 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D12 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D13 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D14 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D15 = EquipmentFactory.getCommonDevice();

        D1.setName("D1");
        D2.setName("D2");
        D3.setName("D3");
        D4.setName("D4");
        D5.setName("D5");
        D6.setName("D6");
        D7.setName("D7");
        D8.setName("D8");
        D9.setName("D9");
        D10.setName("D10");
        D11.setName("D11");
        D12.setName("D12");
        D13.setName("D13");
        D14.setName("D14");
        D15.setName("D15");

        EquipmentInterface cluster = EquipmentFactory.getCluster();
        cluster.setName("Cloud");

        graph.addVertex(D1);
        graph.addVertex(D2);
        graph.addVertex(D3);
        graph.addVertex(D4);
        graph.addVertex(D5);
        graph.addVertex(D6);
        graph.addVertex(D7);
        graph.addVertex(D8);
        graph.addVertex(D9);
        graph.addVertex(D10);
        graph.addVertex(D11);
        graph.addVertex(D12);
        graph.addVertex(D13);
        graph.addVertex(D14);
        graph.addVertex(D15);

        graph.addVertex(S1);
        graph.addVertex(S2);
        graph.addVertex(S3);
        graph.addVertex(S4);
        graph.addVertex(S5);

        graph.addVertex(F1);
        graph.addVertex(F2);
        graph.addVertex(F3);
        graph.addVertex(F4);
        graph.addVertex(F5);
        graph.addVertex(F6);

        graph.addVertex(cluster);

        graph.addEdge(D1, F1);
        graph.addEdge(D2, F2);
        graph.addEdge(D3, F2);
        graph.addEdge(D4, F2);
        graph.addEdge(D5, F3);
        graph.addEdge(D6, F3);
        graph.addEdge(D7, F3);
        graph.addEdge(D8, F3);
        graph.addEdge(D9, F4);
        graph.addEdge(D10, F4);
        graph.addEdge(D11, F5);
        graph.addEdge(D12, F5);
        graph.addEdge(D13, F5);
        graph.addEdge(D14, F6);
        graph.addEdge(D15, F6);

        graph.addEdge(F1, S1);
        graph.addEdge(S1, F1);

        graph.addEdge(F2, S1);
        graph.addEdge(S1, F2);

        graph.addEdge(F2, S2);
        graph.addEdge(S2, F2);

        graph.addEdge(S3, F4);
        graph.addEdge(F4, S3);

        graph.addEdge(F3, S2);
        graph.addEdge(S2, F3);

        graph.addEdge(F3, S4);
        graph.addEdge(S4, F3);

        graph.addEdge(S5, F4);
        graph.addEdge(F4, S5);

        graph.addEdge(S5, F5);
        graph.addEdge(F5, S5);

        graph.addEdge(F5, F6);
        graph.addEdge(F6, F5);

        graph.addEdge(S2, S3);
        graph.addEdge(S3, S2);

        graph.addEdge(S4, S5);
        graph.addEdge(S5, S4);

        graph.addEdge(F1, cluster);
        graph.addEdge(F2, cluster);
        graph.addEdge(F3, cluster);
        graph.addEdge(F4, cluster);
        graph.addEdge(F5, cluster);
        graph.addEdge(F6, cluster);

        NetworkInterface network = new CommonNetwork(graph);
        network.setNetworkName("demo network with fog nodes");
        return network;
    }

    public static NetworkInterface demoNoFogsNetwork() {

        Graph<EquipmentInterface, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);

        Map<String, EquipmentInterface> vertex = new HashMap<>();

        EquipmentInterface F1 = EquipmentFactory.getCommonServer();
        EquipmentInterface F2 = EquipmentFactory.getCommonServer();
        EquipmentInterface F3 = EquipmentFactory.getCommonServer();
        EquipmentInterface F4 = EquipmentFactory.getCommonServer();
        EquipmentInterface F5 = EquipmentFactory.getCommonServer();
        EquipmentInterface F6 = EquipmentFactory.getCommonServer();

        F1.setName("S6");
        F2.setName("S7");
        F3.setName("S8");
        F4.setName("S9");
        F5.setName("S10");
        F6.setName("S11");

        EquipmentInterface S1 = EquipmentFactory.getCommonServer();
        EquipmentInterface S2 = EquipmentFactory.getCommonServer();
        EquipmentInterface S3 = EquipmentFactory.getCommonServer();
        EquipmentInterface S4 = EquipmentFactory.getCommonServer();
        EquipmentInterface S5 = EquipmentFactory.getCommonServer();

        S1.setName("S1");
        S2.setName("S2");
        S3.setName("S3");
        S4.setName("S4");
        S5.setName("S5");

        EquipmentInterface D1 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D2 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D3 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D4 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D5 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D6 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D7 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D8 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D9 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D10 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D11 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D12 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D13 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D14 = EquipmentFactory.getCommonDevice();
        EquipmentInterface D15 = EquipmentFactory.getCommonDevice();

        D1.setName("D1");
        D2.setName("D2");
        D3.setName("D3");
        D4.setName("D4");
        D5.setName("D5");
        D6.setName("D6");
        D7.setName("D7");
        D8.setName("D8");
        D9.setName("D9");
        D10.setName("D10");
        D11.setName("D11");
        D12.setName("D12");
        D13.setName("D13");
        D14.setName("D14");
        D15.setName("D15");

        EquipmentInterface cluster = EquipmentFactory.getCluster();
        cluster.setName("Cloud");

        graph.addVertex(D1);
        graph.addVertex(D2);
        graph.addVertex(D3);
        graph.addVertex(D4);
        graph.addVertex(D5);
        graph.addVertex(D6);
        graph.addVertex(D7);
        graph.addVertex(D8);
        graph.addVertex(D9);
        graph.addVertex(D10);
        graph.addVertex(D11);
        graph.addVertex(D12);
        graph.addVertex(D13);
        graph.addVertex(D14);
        graph.addVertex(D15);

        graph.addVertex(S1);
        graph.addVertex(S2);
        graph.addVertex(S3);
        graph.addVertex(S4);
        graph.addVertex(S5);

        graph.addVertex(F1);
        graph.addVertex(F2);
        graph.addVertex(F3);
        graph.addVertex(F4);
        graph.addVertex(F5);
        graph.addVertex(F6);

        graph.addVertex(cluster);

        graph.addEdge(D1, F1);
        graph.addEdge(D2, F2);
        graph.addEdge(D3, F2);
        graph.addEdge(D4, F2);
        graph.addEdge(D5, F3);
        graph.addEdge(D6, F3);
        graph.addEdge(D7, F3);
        graph.addEdge(D8, F3);
        graph.addEdge(D9, F4);
        graph.addEdge(D10, F4);
        graph.addEdge(D11, F5);
        graph.addEdge(D12, F5);
        graph.addEdge(D13, F5);
        graph.addEdge(D14, F6);
        graph.addEdge(D15, F6);

        graph.addEdge(F1, S1);
        graph.addEdge(S1, F1);

        graph.addEdge(F2, S1);
        graph.addEdge(S1, F2);

        graph.addEdge(F2, S2);
        graph.addEdge(S2, F2);

        graph.addEdge(S3, F4);
        graph.addEdge(F4, S3);

        graph.addEdge(F3, S2);
        graph.addEdge(S2, F3);

        graph.addEdge(F3, S4);
        graph.addEdge(S4, F3);

        graph.addEdge(S5, F4);
        graph.addEdge(F4, S5);

        graph.addEdge(S5, F5);
        graph.addEdge(F5, S5);

        graph.addEdge(F5, F6);
        graph.addEdge(F6, F5);

        graph.addEdge(S2, S3);
        graph.addEdge(S3, S2);

        graph.addEdge(S4, S5);
        graph.addEdge(S5, S4);

        graph.addEdge(F1, cluster);
        graph.addEdge(F2, cluster);
        graph.addEdge(F3, cluster);
        graph.addEdge(F4, cluster);
        graph.addEdge(F5, cluster);
        graph.addEdge(F6, cluster);

        NetworkInterface network = new CommonNetwork(graph);
        network.setNetworkName("demo without fog nodes");
        return network;
    }

    private Graph<EquipmentInterface, DefaultEdge> AddVertex(
            Map<String, EquipmentInterface> vertex,
            Graph<EquipmentInterface, DefaultEdge> graph
    ) {
        for (Map.Entry<String, EquipmentInterface> entry : vertex.entrySet()) {
            EquipmentInterface equipment = entry.getValue();
            String equipmentName = entry.getKey();

            equipment.setName(equipmentName);
            graph.addVertex(equipment);
        }
        return graph;
    }

}
