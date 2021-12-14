package hardware.storage;

import data.DataInterface;

import java.util.HashSet;
import java.util.TreeSet;

public class MemoryFactory {

    public static StorageInterface hdd500() {
        return new Hdd(500, new HashSet<>());
    }

    public static StorageInterface hdd500ForFogNog() {
        return new Hdd(500, new TreeSet<>());
    }

    public static StorageInterface ram128() {
        return new Memory(80, new HashSet<>());
    }
}
