package main.modulebc;

import data.estructures.singlylinkedlist.Product;
import data.estructures.stack.Container;
import data.estructures.singlylinkedlist.ProductSimpleList;

import java.util.Scanner;

public class ContainerContent extends ProductSimpleList {

    /** Sin parametros de entrada ni salida.
     Cada que se negistra un nuevo producto, se pide el ingreso de la información completa,
     si la lista está vacía se establece como el inicio y el final, si no se establce como un nuevo nodo final ajustando
     el dato de siguiente que tiene el previo nodo anterior*/
    @Override
    public void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Registro de Producto ---");
        System.out.print("Nombre del producto: ");
        String name = scanner.nextLine();
        System.out.print("Peso (en kg): ");
        float weight = scanner.nextFloat();
        scanner.nextLine();

        Product newP = new Product(name, weight);
        if (start == null) {
            start = end = newP;
        } else {
            end.setNext(newP);
            end = newP;
        }

    }
    /** Retorna el calculo del peso total de los productos, regresando el resultado en un float*/
    @Override
    public float calculateWeight() {
        float total=0;
        Product current = start;
        while (current != null){
            total += current.getWeight();
            current = current.getNext();

        }
        return total;
    }
    /** Recibe un string con el identificador de un producto y regresa en un booleano si lo encontró o no en la lista
     Funciona recorriendo toda la lista en orden comparando los identificadores de los producto, hasta regresar a l inicio o hallar el producto*/
    public boolean findProduct(String name) {
        if (start == null) {
            return false;
        }

        Product current = start;
        while (current != null) {
            if (current.getNameProduct().equalsIgnoreCase(name)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
}
