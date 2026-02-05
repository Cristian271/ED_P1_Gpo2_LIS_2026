package main.moduled;
import java.util.Scanner;
import data.estructures.doublylinkedlist.*;

public class Distribution extends DoubleList {
    protected int cont;
    public Distribution(){
        cont = 0;
    }



    @Override
    public void insertEnd(String nameStop) {
        if(isEmpty()){
            start = end = new StopBus(nameStop);
            cont++;
        } else{
            StopBus temp = new StopBus(nameStop, end, null);
            end.setNext(temp);
            end = temp;
            cont++;
        }
    }

    @Override
    public void insertBetween(String one, String two, String nameStop) {
        if(isEmpty() || cont <= 1){
            System.out.println("No hay suficientes paradas");
        } else{
            StopBus current = start;
            int who = -1; // 1 -> Encontró al nodo uno primero; 2 -> encontró al nodo dos primero
            while(true){
                if(current.getNameStop().equals(one)){
                    who = 1;
                    break;
                } else if(current.getNameStop().equals(two)){
                    who = 2;
                    break;
                }
                current = current.getNext();
            }

            switch (who){
                case -1:
                    System.out.println("No se pudo añadir la parada, error en alguno de los datos");
                    break;
                case 1:
                    if(current.getNext().getNameStop().equals(two)){
                        StopBus temp = new StopBus(nameStop, current, current.getNext());
                        current.getNext().setPrevious(temp);
                        current.setNext(temp);
                    } else if(current.getPrevious().getNameStop().equals(two)){
                        StopBus temp = new StopBus(nameStop, current.getPrevious(), current);
                        current.getPrevious().setNext(temp);
                        current.setPrevious(temp);
                    } else{
                        System.out.println("No se pudo añadir, error en el nombre de la parada 2");
                    }
                    break;
                case 2:
                    if(current.getNext().getNameStop().equals(one)){
                        StopBus temp = new StopBus(nameStop, current, current.getNext());
                        current.getNext().setPrevious(temp);
                        current.setNext(temp);
                    } else if(current.getPrevious().getNameStop().equals(one)){
                        StopBus temp = new StopBus(nameStop, current.getPrevious(), current);
                        current.getPrevious().setNext(temp);
                        current.setPrevious(temp);
                    } else{
                        System.out.println("No se pudo añadir, error en el nombre de la parada 1");
                    }

            }

        }
    }

    @Override
    public String deleteStop(String name) {
        String deleted = null;
        if(isEmpty()){
            System.out.println("No hay paradas registradas");
        } else {
            StopBus current = start;
            while (current != null && !current.getNameStop().equals(name)) {
                current = current.getNext();
            }

            if(current != null && current.getNameStop().equals(name)){ // Validamos si realmente lo encontró, o llego al final
                deleted = current.getNameStop();
                current.getNext().setPrevious(current.getPrevious());
                current.getPrevious().setNext(current.getNext());
            }
        }

        return deleted;
    }

    @Override
    public void goNext() {
        position = position.getNext();
    }

    @Override
    public void goBack() {
        position = position.getPrevious();
    }

    @Override
    public boolean isEmpty() {
        return start == null;
    }


    public static void menu(){
        System.out.println("----------------------------------------------------");
        System.out.println("---------- MENU DE RUTAS DE DISTRIBUCIÓN  ----------");
        System.out.println("----------------------------------------------------");
        System.out.println("1) Agregar nueva parada final");
        System.out.println("2) Insertar parada entre destinos");
        System.out.println("3) Eliminar parada");
        System.out.println("4) Ir a la siguiente parada");
        System.out.println("5) Ir a la parada anterior");
        System.out.println("6) Volver al menú principal");
    }

    public static void main(String[] args) {
        Distribution list = new Distribution();
        list.insertEnd("Parada1");
        list.insertBetween("Parada1", "Parada3", "Parada2");
        list.insertEnd("Parada3");
        list.insertEnd("Parada4");
        list.insertEnd("Parada5");
        list.insertEnd("Parada7");
        list.insertBetween("Parada5", "Parada7", "Parada6");
        list.insertBetween("Parada7", "Parada5", "Parada6.5");
        list.deleteStop("Parada6");
        list.insertBetween("Parada7", "Parada5", "Parada6.5");
        list.imprimir();
        menu();
    }
}
