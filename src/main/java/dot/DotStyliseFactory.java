package dot;

public class DotStyliseFactory {


    private static String shape = "record";

    public static DotStylizeInterface getClusterDotNode() {
        return new DotNode("", shape, "blue");
    }

    public static DotStylizeInterface getServerDotNode() {
        return new DotNode("", shape, "green");
    }

    public static DotStylizeInterface getFogDotNode() {
        return new DotNode("", shape, "orange");
    }

    public static DotStylizeInterface getDeviceDotNode() {
        return new DotNode("", shape, "purple");
    }

}
