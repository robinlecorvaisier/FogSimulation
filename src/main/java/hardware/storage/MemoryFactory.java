package hardware.storage;

public class MemoryFactory {

    public static StorageInterface hdd500() {
        return new Hdd(500);
    }

    public static StorageInterface ram128() {
        return new Memory(128);
    }
}
