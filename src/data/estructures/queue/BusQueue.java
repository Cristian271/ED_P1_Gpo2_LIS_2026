package data.estructures.queue;

public class BusQueue {
    protected BusQueue next;
    protected String nameBus;
    protected BusQueue previous;
    public BusQueue(String nameBus){
        this.nameBus = nameBus;
        next = null;
        previous = null;
    }

    public BusQueue (String nameBus, BusQueue previous, BusQueue next){
        this.nameBus = nameBus;
        this.previous = previous;
        this.next = next;
    }



    public BusQueue getNext(){
        return next;
    }

    public String getNameBus(){
        return nameBus;
    }

    public BusQueue getPrevious(){
        return previous;
    }

    public void setNext(BusQueue next){
        this.next = next;
    }

    public void setNameBus(String nameBus){
        this.nameBus = nameBus;
    }

    public void setPrevious(BusQueue previous){
        this.previous = previous;
    }


}
