package dot;

import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;

import java.util.HashMap;
import java.util.Map;

public class DotNode implements DotStylizeInterface {


    protected String label;
    protected String shape;
    protected String color;

    public DotNode(String label, String shape, String color) {
        this.label = label;
        this.shape = shape;
        this.color = color;
    }

    @Override
    public Map<String, Attribute> getStyle() {
        Map<String, Attribute> map = new HashMap<>();
        map.put("label", DefaultAttribute.createAttribute(label));
        map.put("shape", DefaultAttribute.createAttribute(shape));
        map.put("color", DefaultAttribute.createAttribute(color));
        return map;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }
}
