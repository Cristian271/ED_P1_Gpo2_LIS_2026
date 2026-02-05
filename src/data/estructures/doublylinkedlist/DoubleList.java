package data.estructures.doublylinkedlist;

public abstract class DoubleList {
    protected StopBus start;
    protected StopBus end;
    protected StopBus position;

    public abstract void insertEnd(String nameStop);
    public abstract void insertBetween(String one, String two, String nameStop);
    public abstract String deleteStop(String name);
    public abstract void goNext();
    public abstract void goBack();
    public abstract boolean isEmpty();

    public void imprimir(){
        StopBus node = start;
        while(node != null){
            System.out.println(node.getNameStop());
            node = node.getNext();
        }
    }



}
