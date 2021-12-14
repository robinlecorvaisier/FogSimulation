package hardware.processing;

import hardware.storage.MemoryFactory;

import java.util.HashSet;

public class ProcessorFactory {

    public static ProcessorInterface getPM128() {
        return new CommonProcessor(MemoryFactory.ram128(), new HashSet<>());
    }

}
