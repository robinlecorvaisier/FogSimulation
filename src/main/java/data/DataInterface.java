package data;

public interface DataInterface extends Comparable<DataInterface> {

    double getSize();

    int getPriority();

    void process();

    boolean isExpired();

    void expire();

    void lost();

}
