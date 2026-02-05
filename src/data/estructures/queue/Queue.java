package data.estructures.queue;

public interface Queue {
    public void enqueue(Object dato);
    public Object dequeue();
    public int size();
    public Object front();
    public boolean isEmpty();
}
