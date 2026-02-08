package main.modulebc;
import data.estructures.stack.*;

public class Yard extends ContainerSimpleList{
    private int count = 0;
    private final int limit;

    public Yard(int limit) {
        this.limit = limit; // Se asigna al crear la instancia
    }
    @Override
    public void inspectContainer() {
        if (isEmpty()) {
            System.out.println("No hay contenedores para inspeccionar.");
            return;
        }
        System.out.println("Inspeccionando Contenedor ID: " + start.getId());
        System.out.println("Peso total acumulado: " + start.getList().calculateWeight() + " kg");

    }

    @Override
    public Container popContainer() {
        return pop();
    }

    @Override
    public void push(Container container) {
        if (count >= limit) {
            System.out.println("Columna de contenedores llena; (máximo: "+ limit +")");
            return;
        }
        if (isEmpty()){
            start = end = container;
        } else {
            container.setNext(start);
            start = container;
        }
        count++;

    }

    @Override
    public Container pop() {
        if (isEmpty()) {
            System.out.println("El patio está vacío.");
            return null;
        }
        Container temp = start;
        start = start.getNext();
        if (start == null) {
            end = null;
        }
        count--;

        return temp;
    }

    @Override
    public Container top() {
        return start;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return start == null;
    }

    public float calculateTotalStackWeight() {
        float sum = 0;
        Container current = start; // Recorremos desde el tope
        while (current != null) {
            sum += current.getList().calculateWeight();
            current = current.getNext();
        }
        return sum;
    }

    public static void menu() {
        System.out.println("\n----------------------------------------------------");
        System.out.println("---------- MENÚ: PATIO DE CONTENEDORES -------");
        System.out.println("----------------------------------------------------");
        System.out.println("1) Ingresar contenedor");
        System.out.println("2) Retirar para ruta");
        System.out.println("3) Ver contenedor en el tope");
        System.out.println("4) INSPECCIONAR CONTENIDO ");
        System.out.println("5) Volver al menú principal");
    }

    public static void menuInspeccion() {
        System.out.println("\n------GESTIÓN DE PRODUCTOS --------");
        System.out.println("1) Agregar producto al contenedor");
        System.out.println("2) Calcular peso total de la carga");
        System.out.println("3) Buscar producto por nombre");
        System.out.println("4) Regresar al menú del patio");
    }

}
