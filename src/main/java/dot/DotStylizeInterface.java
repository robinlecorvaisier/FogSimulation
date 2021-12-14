package dot;

import org.jgrapht.nio.Attribute;

import java.util.Map;

public interface DotStylizeInterface {

    Map<String, Attribute> getStyle();

    void setLabel(String label);

}
