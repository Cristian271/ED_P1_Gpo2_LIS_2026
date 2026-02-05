package data.estructures.queue;

public interface Queue {
    public void enqueue();
    public BusQueue dequeue();
    public int size();
    public BusQueue front();
    public boolean isEmpty();
}
