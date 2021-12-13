package network;

import data.DataFactory;
import equipment.EquipmentInterface;
import listener.data.DataListenerInterface;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CommonNetwork implements NetworkInterface {

    protected Graph<EquipmentInterface, DefaultEdge> network;
    protected String networkName;

    public CommonNetwork(Graph<EquipmentInterface, DefaultEdge> network) {
        this.network = network;
        networkName = this.getClass().getSimpleName();
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getDot() {
        DOTExporter<EquipmentInterface, DefaultEdge> exporter = new DOTExporter<>(
                v -> v.getName()
                        .replace('.', '_')
                        .replace(' ', '_')
                        .replace('[', '_')
                        .replace(']', '_')
                        .replace('@', '_')
        );
        exporter.setVertexAttributeProvider(v -> {
            Map<String, Attribute> map = new LinkedHashMap<>();
            map.put("label", DefaultAttribute.createAttribute(v.toString()));
            map.put("shape", DefaultAttribute.createAttribute("record"));
            return map;
        });
        exporter.setGraphAttributeProvider(() -> {
            Map<String, Attribute> map = new LinkedHashMap<>();
            map.put("label", DefaultAttribute.createAttribute(
                    "\"" + networkName + DataFactory.dataListenerStatus() + "\""
            ));
            return map;
        });
        Writer writer = new StringWriter();
        exporter.exportGraph(network, writer);
        return writer.toString();
    }

    @Override
    public void iterate() {
        Set<EquipmentInterface> equipments = this.network.vertexSet();
        DataListenerInterface dataListener = DataFactory.getDataListener();
        for (EquipmentInterface equipment : equipments) {
            equipment.action(network);
        }
        dataListener.onIterationChange();
    }

    @Override
    public Set<EquipmentInterface> getNodes() {
        return network.vertexSet();
    }
}
