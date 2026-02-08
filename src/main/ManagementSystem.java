package main;

import main.modulea.Reception;
import main.modulebc.Yard;
import main.moduled.Distribution;
import data.estructures.stack.Container;
import data.estructures.queue.BusQueue;
import java.util.Scanner;

public class ManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Reception reception = new Reception();
    private static Distribution distribution = new Distribution();
    private static Yard[] yardas;
    private static int stackLimit;

    public static void main(String[] args) {
        configuracionInicial();
        int option = 0;
        do {
            menuPrincipal();
            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1: receptionLogic(); break;
                    case 2: yardLogic(); break;
                    case 3: routeLogic(); break;
                    case 4: showGeneralReport(); break;
                    case 5: System.out.println("Saliendo del sistema"); break;
                    default: System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        } while (option != 5);
    }

    private static void configuracionInicial() {
        System.out.println("=== CONFIGURACIÓN DEL PUERTO PROGRESO ===");
        System.out.print("¿Cuántas columnas de contenedores (Pilas) hay?: ");
        int cantidad = Integer.parseInt(scanner.nextLine());
        System.out.print("Defina la altura máxima (X): ");
        stackLimit = Integer.parseInt(scanner.nextLine());

        yardas = new Yard[cantidad];
        for (int i = 0; i < cantidad; i++) {
            yardas[i] = new Yard(stackLimit);
        }
    }

    private static void menuPrincipal() {
        System.out.println("\n====================================================");
        System.out.println("   PUERTO PROGRESO SYSTEM - GESTIÓN");
        System.out.println("====================================================");
        System.out.println("[1] ZONA DE RECEPCIÓN (Colas)");
        System.out.println("[2] PATIO DE CONTENEDORES (Pilas)");
        System.out.println("[3] LOGÍSTICA Y RUTAS (Listas Dobles)");
        System.out.println("[4] REPORTE GENERAL");
        System.out.println("[5] SALIR");
        System.out.print("Seleccione una opción: ");
    }

    
    private static void receptionLogic() {
        int option;
        do {
            Reception.menu();
            System.out.print("Seleccione: ");
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1: reception.enqueue(); break;
                case 2:
                    BusQueue bus = reception.dequeue();
                    if (bus != null) System.out.println("Camión [" + bus.getNameBus() + "] ingresando al patio...");
                    break;
                case 3:
                    if (!reception.isEmpty()) System.out.println("Siguiente: " + reception.front().getNameBus());
                    else System.out.println("No hay camiones en espera.");
                    break;
                case 4: reception.print(); break;
            }
        } while (option != 5);
    }
    
    private static void yardLogic() {
        int option;
        do {
            Yard.menu();
            System.out.print("Seleccione: ");
            option = Integer.parseInt(scanner.nextLine());
            if (option >= 1 && option <= 4) {
                System.out.print("ID de Pila (0 a " + (yardas.length - 1) + "): ");
                int p = Integer.parseInt(scanner.nextLine());
                if (p < 0 || p >= yardas.length) { System.out.println("Pila inválida."); continue; }

                switch (option) {
                    case 1:
                        System.out.print("ID del nuevo contenedor: ");
                        yardas[p].push(new Container(scanner.nextLine()));
                        break;
                    case 2:
                        Container popped = yardas[p].pop();
                        if (popped != null) System.out.println("Contenedor [" + popped.getId() + "] retirado.");
                        break;
                    case 3:
                        if (!yardas[p].isEmpty()) System.out.println("Tope actual: " + yardas[p].top().getId());
                        break;
                    case 4: inspectionLogic(p); break;
                }
            }
        } while (option != 5);
    }

    private static void inspectionLogic(int p) {
        if (yardas[p].isEmpty()) {
            System.out.println("No hay nada que inspeccionar en esta pila.");
            return;
        }
        Container actual = yardas[p].top();
        int option;
        do {
            System.out.println("\n--- INSPECCIONANDO: " + actual.getId() + " ---");
            Yard.menuInspeccion();
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1: actual.getList().addProduct(); break;
                case 2: System.out.println("Peso Total: " + actual.getList().calculateWeight() + " kg"); break;
                case 3:
                    System.out.print("Producto a buscar: ");
                    String buscar = scanner.nextLine();
                    if (actual.getList().findProduct(buscar)) System.out.println("Producto localizado en el contenedor.");
                    else System.out.println("No se encuentra en este contenedor.");
                    break;
            }
        } while (option != 4);
    }


    private static void routeLogic() {
        int option;
        do {
            Distribution.menu();
            System.out.print("Seleccione: ");
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    System.out.print("Nueva Parada: ");
                    distribution.insertEnd(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Parada A: "); String a = scanner.nextLine();
                    System.out.print("Parada B: "); String b = scanner.nextLine();
                    System.out.print("Nueva Intermedia: "); String n = scanner.nextLine();
                    distribution.insertBetween(a, b, n);
                    break;
                case 3:
                    System.out.print("Nombre de parada a cancelar: ");
                    distribution.deleteStop(scanner.nextLine());
                    break;
                case 4:
                    if (distribution.isEmpty()) System.out.println("Ruta vacía.");
                    else distribution.goNext();
                    break;
                case 5:
                    if (distribution.isEmpty()) System.out.println("Ruta vacía.");
                    else distribution.goBack();
                    break;
            }
        } while (option != 6);
    }


    private static void showGeneralReport() {
        System.out.println("\n[ESTADO DE RECEPCIÓN]:");
        System.out.println(">> Camiones en espera: " + reception.size());
        if (!reception.isEmpty()){
            System.out.println(">> Próximo en turno: " + reception.front().getNameBus());
        }


        System.out.println("\n[ESTADO DE INVENTARIO]:");
        for (int i = 0; i < yardas.length; i++) {
            int actualSize = yardas[i].size();

            System.out.print(" >> Pila " + (char)('A' + i) + ": [");
            for (int j = 0; j < stackLimit; j++) {
                if (j < actualSize) {
                    System.out.print("|"); // Espacio ocupado
                } else {
                    System.out.print(" "); // Espacio vacío
                }
            }
            System.out.print("] (" + actualSize + "/" + stackLimit + ")");
            float stackWeight = yardas[i].calculateTotalStackWeight();
            System.out.print(" - Peso Total: " + String.format("%.2f", stackWeight) + " kg");

            if (actualSize >= stackLimit) {
                System.out.print(" - ¡CRÍTICO: LLENA!");
            }
            System.out.println();
        }

        System.out.println("\n[ESTADO DE LOGÍSTICA]:");
        System.out.println(" >> Rutas activas: " + (distribution.isEmpty() ? "0" : "1"));
        System.out.println(" >> Próximo destino: " + distribution.showCurrentStop());
        System.out.println(" >> Total de paradas programadas: " + distribution.getCont());

        System.out.println("\nPresione Enter para volver al menú principal...");
        scanner.nextLine();
    }
}
