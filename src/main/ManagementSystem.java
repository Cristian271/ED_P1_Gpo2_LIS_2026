package main;

import main.modulea.Reception;
import main.modulebc.Yard;
import main.moduled.Distribution;
import data.estructures.stack.Container;
import data.estructures.queue.BusQueue;
import java.util.Scanner;

public class ManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Reception busQueue = new Reception();
    private static Distribution route = new Distribution();
    private static Yard[] pilas; // pilas multiples
    private static int stackLimit;

    public static void main(String[] args) {
        configuracionInicial();
        int option = 0;
        do {
            menuPrincipal();
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    subMenu1();
                    break;
                case 2:
                    subMenu2();
                    break;
                case 3:
                    subMenu3();
                    break;
                case 4:
                    showGeneralReport();
                    break;
                case 5:
                    System.out.println("Programa finalizado");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (option != 5);
    }

    private static void configuracionInicial() {
        System.out.println("=== CONFIGURACIÓN INICIAL DEL PUERTO  ===");
        System.out.println("Ingrese la cantidad de contenedores (Pilas) que hay: ");
        int cant = scanner.nextInt();
        System.out.println("Defina la altura máxima de cada pila: ");
        stackLimit = scanner.nextInt();
        scanner.nextLine();

        pilas = new Yard[cant];
        for (int i = 0; i < cant; i++) {
            pilas[i] = new Yard(stackLimit);
        }
    }

    private static void menuPrincipal() {
        System.out.println("\n====================================================");
        System.out.println("   PUERTO PROGRESO SYSTEM - GESTIÓN");
        System.out.println("====================================================");
        System.out.println("[1] ZONA DE RECEPCIÓN");
        System.out.println("[2] PATIO DE CONTENEDORES");
        System.out.println("[3] LOGÍSTICA Y RUTAS ");
        System.out.println("[4] REPORTE GENERAL ");
        System.out.println("[5] SALIR");
        System.out.println("Seleccione una opción: ");
    }


    private static void subMenu1() {
        int option;
        do {
            Reception.menu();
            System.out.println("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    busQueue.enqueue();
                    break;
                case 2:
                    BusQueue bus = busQueue.dequeue();
                    if (bus != null) {
                        System.out.println("Camión " + bus.getNameBus() + " ingresando al patio");
                    }
                    break;
                case 3:
                    if (!busQueue.isEmpty()) {
                        System.out.println("Siguiente: " + busQueue.front().getNameBus());
                    } else {
                        System.out.println("No hay camiones en espera");
                    }
                    break;
                case 4:
                    busQueue.print();
                    break;
                case 5:
                    System.out.println("Volviendo ...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (option != 5);
    }

    private static void subMenu2() {
        int option;
        do {
            Yard.menu();
            System.out.println("Seleccione una opción: ");
            option = scanner.nextInt();
            if (option >= 1 && option <= 4) {
                System.out.println("ID de Pila (numero de 0 a " + (pilas.length - 1) + "): ");
                int p = scanner.nextInt();
                scanner.nextLine();
                if (p < 0 || p >= pilas.length) {
                    System.out.println("Pila no valida");
                    continue;
                }

                switch (option) {
                    case 1:
                        System.out.println("ID del nuevo contenedor: ");
                        String idC = scanner.nextLine();
                        pilas[p].push(new Container(idC));
                        break;
                    case 2:
                        Container popped = pilas[p].pop();
                        if (popped != null) {
                            System.out.println("Contenedor " + popped.getId() + " retirado");
                        }
                        break;
                    case 3:
                        if (!pilas[p].isEmpty()) {
                            System.out.println("Tope actual: " + pilas[p].top().getId());
                        }
                        break;
                    case 4:
                        inspect(p);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }
        } while (option != 5);
    }

    private static void inspect(int p) {
        if (pilas[p].isEmpty()) {
            System.out.println("No hay nada para inspeccionar en esta pila.");
            return;
        }
        Container actual = pilas[p].top();
        int option;
        do {
            System.out.println("\n--- INSPECCIONANDO: CONTENEDOR " + actual.getId() + " ---");
            Yard.menuInspeccion();
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    actual.getList().addProduct();
                    break;
                case 2:
                    System.out.println("Peso total: " + actual.getList().calculateWeight() + " kg");
                    break;
                case 3:
                    System.out.print("Producto a buscar: ");
                    String search = scanner.nextLine();
                    if (actual.getList().findProduct(search)) {
                        System.out.println("El producto SI se encuentra en el contenedor");
                    } else {
                        System.out.println("El producto NO se encuentra en este contenedor.");
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (option != 4);
    }


    private static void subMenu3() {
        int option;
        do {
            Distribution.menu();
            System.out.println("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Escriba el nombre de la nueva parada: ");
                    route.insertEnd(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("A continuación, escriba las paradas entre las que se quiere insertar ");
                    System.out.println("Parada 1: ");
                    String stop1 = scanner.nextLine();
                    System.out.println("Parada 2: ");
                    String stop2 = scanner.nextLine();
                    System.out.println("Nueva parada intermedia: ");
                    String newStop = scanner.nextLine();
                    route.insertBetween(stop1, stop2, newStop);
                    break;
                case 3:
                    System.out.println("Nombre de parada a cancelar: ");
                    route.deleteStop(scanner.nextLine());
                    break;
                case 4:
                    if (route.isEmpty()) {
                        System.out.println("No hay paradas en la ruta para simular.");
                    } else {
                        simulateRoute();
                    }
                    break;
                case 5:
                    System.out.println("Volviendo ... ");
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (option != 5);
    }


    private static void showGeneralReport() {
        System.out.println("\n[ESTADO DE RECEPCIÓN]:");
        System.out.println(">> Camiones en espera: " + busQueue.size());
        if (!busQueue.isEmpty()) {
            System.out.println(">> Próximo en turno: " + busQueue.front().getNameBus());
        }


        System.out.println("\n[ESTADO DE INVENTARIO]:");
        for (int i = 0; i < pilas.length; i++) {
            int actualSize = pilas[i].size();

            System.out.print(" >> Pila " + (char) ('A' + i) + ": [");
            for (int j = 0; j < stackLimit; j++) {
                if (j < actualSize) {
                    System.out.print("|"); // Espacio ocupado
                } else {
                    System.out.print(" "); // Espacio vacío
                }
            }
            System.out.print("] (" + actualSize + "/" + stackLimit + ")");
            float stackWeight = pilas[i].calculateTotalStackWeight();
            System.out.printf(" - Peso Total: %.2f kg", stackWeight);

            if (actualSize >= stackLimit) {
                System.out.print(" - ¡CRÍTICO: LLENA!");
            }
            System.out.println();
        }

        System.out.println("\n[ESTADO DE LOGÍSTICA]:");
        System.out.println(" >> Rutas activas: " + (route.isEmpty() ? "0" : "1"));
        System.out.println(" >> Próximo destino: " + route.showCurrentStop());
        System.out.println(" >> Total de paradas programadas: " + route.getCont());
        System.out.println("\n--Presione Enter para volver al menú principal--");
        scanner.nextLine();
    }

    private static void simulateRoute() {
        int option;
        do {
            System.out.println("\n----NAVEGACIÓN DE RUTA ----");
            System.out.println("Parada actual: " + route.showCurrentStop());
            System.out.println("1) Avanzar");
            System.out.println("2) Retroceder");
            System.out.println("3) Terminar simulación");
            System.out.print("Seleccione dirección: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    route.goNext();
                    break;
                case 2:
                    route.goBack();
                    break;
                case 3:
                    System.out.println("Simulación finalizada");
                    break;
                default:
                    System.out.println("Movimiento no válido");
                    break;
            }
        } while (option != 3);
    }

}
