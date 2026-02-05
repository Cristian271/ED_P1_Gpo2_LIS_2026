package main.modulea;
import data.estructures.queue.*;
import java.util.Scanner;
public class Reception extends QueueDoubleList {
    private int cont;

    public Reception(){
        this.cont = 0;
    }

    // Start -> El primero que sale, End -> El ultimo que sale ; Aclaración

    @Override
    public void enqueue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa el ID del camión: ");
        String nameBus = scanner.nextLine();
        if(isEmpty()){
            start = end = new BusQueue(nameBus); cont++;
        } else{
            BusQueue temp = new BusQueue(nameBus, end, null); cont++;
            end.setNext(temp);
            end = temp;

        }
    }

    @Override
    public BusQueue dequeue() {
        BusQueue deleted = null;
        if(isEmpty() || cont == 1){
            if(isEmpty()) {
                System.out.println("La cola esta vacía");
            } else{
                start = end = null;
                cont--;
            }

        } else{
            deleted = start;
            start.getNext().setPrevious(null);
            start = start.getNext();
            cont--;
        }


        return deleted;
    }

    @Override
    public int size() {
        return cont;
    }

    @Override
    public BusQueue front() {
        return start;
    }

    @Override
    public boolean isEmpty() {
        return cont == 0;
    }

    public static void menu(){
        System.out.println("----------------------------------------------------");
        System.out.println("---------------- ZONA DE RECEPCIÓN  ----------------");
        System.out.println("----------------------------------------------------");
        System.out.println("1) Registrar llegada de camión");
        System.out.println("2) Dar ingreso a patio");
        System.out.println("3) Ver proximo camión a atender");
        System.out.println("4) Visualizar todos los camiones en espera");
        System.out.println("5) Volver al menú principal");
    }

    // Esto se borra luego, es unicamente para probar
    public static void main(String[] args) {
        Reception list = new Reception();
        menu();
        list.enqueue();
        list.enqueue();
        list.enqueue();
        list.enqueue();
        list.print();
        System.out.println("Tamaño: " + list.size());
        list.dequeue();
        System.out.println("Tamaño: " + list.size());
        list.print();
        list.dequeue();
        list.dequeue();
        System.out.println("Tamaño: " + list.size());
        list.print();
        list.dequeue();
        System.out.println("Tamaño: " + list.size());
        list.dequeue();
        list.print();





    }
}
