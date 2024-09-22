//
// Programación orientada a objetos
// Sección 20
// Laboratorio 2 Arreglos y Excepciones
// 
// Hugo Méndez - 241265
//

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Inicializar el scanner
        
        // Creación de las secciones
        Seccion electronicos = new Seccion("Electrónicos", 200000);
        Seccion ropa = new Seccion("Ropa", 150000);
        Seccion alimentos = new Seccion("Alimentos", 250000);
        Seccion maquinaria = new Seccion("Maquinaria", 300000);

        boolean salir = false; //Control para el menú

        while (salir == false) {
            //Opciones del menú
            System.out.println("---- Gestión de Contenedores ----");
            System.out.println("1. Agregar contenedor");
            System.out.println("2. Mostrar contenedores por sección");
            System.out.println("3. Mover contenedor entre secciones");
            System.out.println("4. Calcular peso total por sección");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt(); //Leer opción
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Agregar contenedor
                    System.out.println("Ingrese el identificador del contenedor:");
                    String id = scanner.nextLine();
                    System.out.println("Ingrese el nombre del producto:");
                    String producto = scanner.nextLine();
                    System.out.println("Ingrese el peso del contenedor (kg):");
                    double peso = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Ingrese el destino del contenedor:");
                    String destino = scanner.nextLine();
                    System.out.println("Ingrese la sección (electronicos, ropa, alimentos, maquinaria):");
                    String seccion = scanner.nextLine().toLowerCase();

                    Contenedor nuevoContenedor = new Contenedor(id, producto, peso, destino); //Crear nuevo contenedor
                    boolean agregado = false; //Variable para comprobar si se pudo agregar el contenedor

                    switch (seccion) {
                        case "electronicos":
                            agregado = electronicos.agregarContenedor(nuevoContenedor);
                            break;
                        case "ropa":
                            agregado = ropa.agregarContenedor(nuevoContenedor);
                            break;
                        case "alimentos":
                            agregado = alimentos.agregarContenedor(nuevoContenedor);
                            break;
                        case "maquinaria":
                            agregado = maquinaria.agregarContenedor(nuevoContenedor);
                            break;
                        default:
                            System.out.println("Sección inválida."); //Mensaje de error por si se ingresa una opción inválida
                    }

                    if (agregado) {
                        System.out.println("Contenedor agregado exitosamente.");
                    } else {
                        System.out.println("No se pudo agregar el contenedor. Verifique el peso o el espacio.");
                    }
                    break;

                case 2:
                    // Mostrar contenedores por sección
                    System.out.println("Ingrese la sección a listar: Electrónicos: 1 |  Ropa: 2 |  Alimentos: 3 | Maquinaria: 4");
                    int listarSeccion = scanner.nextInt(); //Leer sección a mostrar

                    switch (listarSeccion) {
                        case 1: //Electrónicos
                            System.out.println("Contenedores en la sección de electrónicos:");
                            System.out.println(electronicos.listarContenedores());
                            break;
                        case 2: //Ropa
                            System.out.println("Contenedores en la sección de ropa:");
                            System.out.println(ropa.listarContenedores());
                            break;
                        case 3: //Alimentos
                            System.out.println("Contenedores en la sección de alimentos:");
                            System.out.println(alimentos.listarContenedores());
                            break;
                        case 4: //Maquinaria
                            System.out.println("Contenedores en la sección de maquinaria:");
                            System.out.println(maquinaria.listarContenedores());
                            break;
                        default:
                            System.out.println("Sección inválida."); //Mensaje de error por si se ingresa una opción inválida
                    }
                    break;

                case 3:
                    // Mover contenedor entre secciones
                    System.out.println("Ingrese el identificador del contenedor a mover:");
                    String idMover = scanner.nextLine(); //Contenedor a mover
                    System.out.println("Ingrese la sección de origen: Electrónicos: 1 |  Ropa: 2 |  Alimentos: 3 | Maquinaria: 4");
                    int seccionOrigen = scanner.nextInt(); //Origen de donde se mueve
                    System.out.println("Ingrese la sección de destino: Electrónicos: 1 |  Ropa: 2 |  Alimentos: 3 | Maquinaria: 4");
                    int seccionDestino = scanner.nextInt(); //Destino a donde se mueve

                    Seccion origen = null; //Declarar el origen para luego utilizarlo
                    Seccion destinoo = null; //Declarar el destino para luego utilizarlo

                    switch (seccionOrigen) {
                        case 1:  //Electronicos
                            origen = electronicos; 
                            break;
                        case 2: //Ropa
                            origen = ropa; 
                            break;
                        case 3: //Alimentos
                        origen = alimentos; 
                        break;
                        case 4: //Maquinaria
                            origen = maquinaria; 
                            break;
                        default: System.out.println("Sección de origen inválida."); break; //Mensaje de error por si se ingresa una opción inválida
                    }

                    switch (seccionDestino) {
                        case 1: //Electrónicos
                            destinoo = electronicos; 
                            break;
                        case 2: //Ropa
                            destinoo = ropa; 
                            break;
                        case 3: //Alimentos
                            destinoo = alimentos; 
                            break;
                        case 4: //Maquinaria
                            destinoo = maquinaria; 
                            break;
                        default: System.out.println("Sección de destino inválida."); break; //Mensaje de error por si se ingresa una opción inválida
                    }

                    if (origen != null && destinoo != null) { //Verificar que si se modificó el origen y destino para que no sea null
                        Contenedor contenedorMover = null; //Declarar el contenedor a mover para luego utilizarlo
                        for (int i = 0; i < origen.getContenedores().length; i++) { //Obtner fila del contenedor
                            for (int j = 0; j < origen.getContenedores()[i].length; j++) { //Obtner columna del contenedor
                                if (origen.getContenedores()[i][j] != null && origen.getContenedores()[i][j].getIdentificador().equals(idMover)) { //Encontrar el contenedor
                                    contenedorMover = origen.getContenedores()[i][j];
                                    origen.getContenedores()[i][j] = null; //Actualizar el espacio donde se encontraba el contenedor
                                    double nuevo_Peso = origen.getPesoTotal() - contenedorMover.getPeso();
                                    origen.setPesoTotal(nuevo_Peso); //Actualizar el peso de la sección de Origen
                                    break;
                                }
                            }
                        }

                        if (contenedorMover != null) {
                            boolean agregadoMover = destinoo.agregarContenedor(contenedorMover); //Verificar que si se puede mover el contenedor al destino
                            if (agregadoMover) {
                                System.out.println("Contenedor movido exitosamente."); //Confirmación que movió el contenedor a la nueva sección
                            } else {
                                System.out.println("No se pudo mover el contenedor. Verifique el peso o el espacio.");
                                origen.agregarContenedor(contenedorMover); // Regresar el contenedor a la sección original si no se pudo mover
                            }
                        } else {
                            System.out.println("Contenedor no encontrado en la sección de origen."); //El contenedor no está en la sección de origen
                        }
                    }
                    break;

                case 4:
                    // Calcular peso total por sección
                    System.out.println("Ingrese la sección para calcular el peso total: Electronicos: 1 |  Ropa: 2 |  Alimentos: 3 | Maquinaria: 4");
                    int seccionPeso = scanner.nextInt();

                    switch (seccionPeso) {
                        case 1: //Electrónicos
                            System.out.println("Peso total en la sección de electrónicos: " + electronicos.getPesoTotal() + " kg");
                            break;
                        case 2: //Ropa
                            System.out.println("Peso total en la sección de ropa: " + ropa.getPesoTotal() + " kg");
                            break;
                        case 3: //Alimentos
                            System.out.println("Peso total en la sección de alimentos: " + alimentos.getPesoTotal() + " kg");
                            break;
                        case 4: //Maquinaria
                            System.out.println("Peso total en la sección de maquinaria: " + maquinaria.getPesoTotal() + " kg");
                            break;
                        default:
                            System.out.println("Sección inválida."); //Mensaje de error por si se ingresa una opción inválida
                    }
                    break;

                case 5:
                    // Salir del programa
                    salir = true; //Romper ciclo
                    System.out.println("Saliendo del sistema..."); //Mensaje de confirmación
                    scanner.close(); //Cerrar el scanner
                    break;

                default:
                    System.out.println("Opción inválida."); //Mensaje de error por si se ingresa una opción inválida
            }
        }
        System.out.println("Programa Terminado exitosamente"); //Confirmación que el programa terminó
    }
}
