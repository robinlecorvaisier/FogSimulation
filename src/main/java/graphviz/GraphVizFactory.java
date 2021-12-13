package graphviz;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import network.NetworkInterface;

import java.io.File;
import java.io.IOException;

public class GraphVizFactory {

    private static String directoryPath = "graphVisualiser";
    private static String directorySeparator = "\\";

    public static void generateSvg(NetworkInterface network) throws IOException {
        Graphviz.fromString(network.getDot())
                .render(Format.SVG)
                .toFile(new File(directoryPath + directorySeparator + network.getNetworkName()));
    }

}
