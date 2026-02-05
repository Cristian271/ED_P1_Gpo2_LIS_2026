package data.estructures.stack;

public interface Stack {
    public void push(Container container);
    public Container pop();
    public Container top();
    public int size();
    public boolean isEmpty();
}
