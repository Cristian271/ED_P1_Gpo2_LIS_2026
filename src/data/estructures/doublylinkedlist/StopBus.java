package data.estructures.doublylinkedlist;

public class StopBus {
    protected StopBus next;
    protected StopBus previous;
    protected String nameStop;

    public StopBus(String nameStop){
        this.nameStop = nameStop;
        next = previous = null;
    }

    public StopBus(String nameStop, StopBus previous, StopBus next){
        this.next = next;
        this.previous = previous;
        this.nameStop = nameStop;
    }

    public StopBus getNext(){
        return next;
    }

    public StopBus getPrevious(){
        return previous;
    }

    public String getNameStop(){
        return nameStop;
    }

    public void setNext(StopBus next){
        this.next = next;
    }

    public void setPrevious(StopBus previous){
        this.previous = previous;
    }

    public void setNameStop(String nameStop){
        this.nameStop = nameStop;
    }


}
