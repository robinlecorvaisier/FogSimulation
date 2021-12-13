package network;

import equipment.EquipmentInterface;

import java.util.Set;

public interface NetworkInterface {

    String getNetworkName();

    void setNetworkName(String networkName);

    String getDot();

    void iterate();

    Set<EquipmentInterface> getNodes();

}
