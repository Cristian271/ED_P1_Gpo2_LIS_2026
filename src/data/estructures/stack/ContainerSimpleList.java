package data.estructures.stack;

public abstract class ContainerSimpleList implements Stack {
    protected Container start;
    protected Container end;
    //Sin entrada ni salida especifica, se encaraga de inspeccionar el contenido de un contenedor
    public abstract void inspectContainer();
    //Sin entrada, con una salida tipo Containert, se encarga de eliminar un contenedor de la lista
    public abstract Container popContainer();

}
