package data.estructures.stack;

public abstract class ContainerSimpleList implements Stack {
    protected Container start;
    protected Container end;

    public abstract void inspectContainer();
    public abstract Container popContainer();

}
