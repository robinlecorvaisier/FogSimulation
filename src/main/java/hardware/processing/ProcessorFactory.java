package hardware.processing;

import hardware.storage.MemoryFactory;

public class ProcessorFactory {

    public static ProcessorInterface getPM128() {
        return new CommonProcessor(MemoryFactory.ram128());
    }

}
