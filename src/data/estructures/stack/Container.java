package data.estructures.stack;

import main.modulebc.ContainerContent;

public class Container {
    protected Container next;
    protected String id;
    protected ContainerContent list;

    public Container(String id) {
        this.id = id;
        this.next = null;
        this.list = new ContainerContent();
    }

    public Container getNext() {
        return next;
    }

    public void setNext(Container next) {
        this.next = next;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ContainerContent getList() {
        return list;
    }

    public void setList(ContainerContent list) {
        this.list = list;
    }
}
