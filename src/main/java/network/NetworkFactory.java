package network;

import equipment.EquipmentFactory;
import equipment.EquipmentInterface;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
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
