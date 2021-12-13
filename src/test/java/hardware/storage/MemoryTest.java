package hardware.storage;

import data.DataFactory;
import data.DataInterface;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MemoryTest {

    @Test
    void read() {
        StorageInterface hdd = MemoryFactory.hdd500();
        DataInterface data = DataFactory.getData();

        hdd.write(data);
        DataInterface dataRead = hdd.read();
        assertEquals(dataRead, data);
    }

    @Test
    void write() {
        Memory memory = (Memory) MemoryFactory.hdd500();
        DataInterface data = DataFactory.getData();
        ArrayList<DataInterface> expectedDataList = new ArrayList<>();

        memory.write(data);
        expectedDataList.add(data);

        assertEquals(expectedDataList, memory.getDataInterfaces());
    }

    @Test
    void writeOverCharge() {
        Memory memory = (Memory) MemoryFactory.hdd500();
        DataInterface data = DataFactory.getData();
        DataInterface ToBigData = DataFactory.getDataTest(600);
        ArrayList<DataInterface> expectedDataList = new ArrayList<>();

        memory.write(data);
        memory.write(ToBigData);
        expectedDataList.add(data);

        assertEquals(expectedDataList, memory.getDataInterfaces());
    }

    @Test
    void getPercentageStorage() {
        StorageInterface hdd = MemoryFactory.hdd500();
        hdd.write(DataFactory.getDataTest(250));
        assertEquals(0.5, hdd.getPercentageStorage());
    }

    @Test
    void getNewPercentageStorage() {
        StorageInterface hdd = MemoryFactory.hdd500();
        hdd.write(DataFactory.getDataTest(250));
        hdd.write(DataFactory.getDataTest(100));
        assertEquals(0.7, hdd.getPercentageStorage());
    }

    @Test
    void getMaxStorage() {
        StorageInterface hdd = MemoryFactory.hdd500();
        assertEquals(500.0, hdd.getMaxStorage());
    }

    @Test
    void getCurrentStorage() {
        StorageInterface hdd = MemoryFactory.hdd500();
        hdd.write(DataFactory.getDataTest(50));
        hdd.write(DataFactory.getDataTest(25.10));
        hdd.write(DataFactory.getDataTest(76.35));

        assertEquals(50 + 25.10 + 76.35, hdd.getCurrentStorage());
    }

    @Test
    void testToString() {
        StorageInterface hdd = MemoryFactory.hdd500();
        assertEquals("Hdd500.0", hdd.toString());
    }
}