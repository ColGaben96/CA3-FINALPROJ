package co.edu.unbosque.controller;

import java.util.Scanner;

/**
 * @author Gabriel Blanco; Sebastian Moncaleano
 * @version 1.0
 * Clase controlador para unir la vista con el modelo
 */
public class Controller  {

    private Scanner sc = new Scanner(System.in);

    public void consola() {
        System.out.println("""
                ------------------------------------------------------
                Proyecto Final - Sebastian Moncaleano y Gabriel Blanco
                ------------------------------------------------------
                """);
        ayuda();
        boolean active = true;
        while(active) {
            try {
                System.out.print("$> ");
                var opcion = sc.next();
                switch (opcion) {
                    default -> System.out.println("Ingresa una opción válida");
                    case "0" -> active = false;
                    case "1" -> backtracking();
                    case "2" -> ramapoda();
                    case "3" -> ayuda();
                }
            } catch (Exception e) {
                System.out.println("Error fatal. Saliendo");
            }
        }
    }

    public void ayuda() {
        System.out.println("""
                Opción\tDescripción
                0\t Salir
                1\t Backtracking
                2\t Rama y Poda
                3\t Mostrar Esta Ayuda
                """);
    }

    public void backtracking() {

    }

    public void ramapoda() {

    }
}

class APLMain {
    public static void main(String[] args) {
        Controller c = new Controller();
        c.consola();
    }
}
