package main.modulebc;

import data.estructures.singlylinkedlist.Product;
import data.estructures.stack.Container;
import data.estructures.singlylinkedlist.ProductSimpleList;

import java.util.Scanner;

public class ContainerContent extends ProductSimpleList {


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
