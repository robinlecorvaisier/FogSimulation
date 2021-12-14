package data;

import listener.data.CommonDataListener;
import listener.data.DataListenerInterface;

import java.util.Random;

public class DataFactory {

    private static final Random random = new Random();
    private static final DataListenerInterface dataListener = new CommonDataListener();


    public static DataInterface getData() {
        return new CommonData(getRandomSizeValue(), getRandomExpirationDateValue(), dataListener);
    }

    private static double getRandomSizeValue() {
        double minimumSize = 10;
        double maximumSize = 50;
        return minimumSize + random.nextDouble() * (maximumSize - minimumSize);
    }

    private static int getRandomExpirationDateValue() {
        int minimumSize = 1;
        int maximumSize = 5;
        return minimumSize + random.nextInt(maximumSize - minimumSize);
    }

    public static DataInterface getDataTest(double size) {
        return new CommonData(size, 10, dataListener);
    }

    public static String dataListenerStatus() {
        return dataListener.toString();
    }

    public static DataListenerInterface getDataListener() {
        return dataListener;
    }
}
