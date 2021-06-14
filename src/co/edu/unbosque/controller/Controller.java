package co.edu.unbosque.controller;

import co.edu.unbosque.model.LogicaSolitario;
import co.edu.unbosque.model.Nodo;
import co.edu.unbosque.model.RamaYPoda;

import java.util.Scanner;

/**
 * @author Gabriel Blanco; Sebastian Moncaleano
 * @version 1.0
 * Clase controlador para unir la vista con el modelo
 */
public class Controller  {

    private final Scanner sc = new Scanner(System.in);

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
                e.printStackTrace();
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
        LogicaSolitario ls = new LogicaSolitario();
    }

    public void ramapoda() {
        System.out.print("Ingresa la posición de la fila: ");
        var posicionFila = sc.nextInt();
        System.out.print("Ingresa la posición de la columna: ");
        var posicionColumna = sc.nextInt();
        System.out.print("Ingresa el destino de las filas: ");
        var posDestinoFilas = sc.nextInt();
        System.out.print("Ingresa el destino de las columnas: ");
        var posDestinoColumnas = sc.nextInt();
        System.out.print("Ingresa el ancho: ");
        var ancho = sc.nextInt();
        System.out.print("Ingresa el alto: ");
        var alto = sc.nextInt();
        System.out.print("Ingresa el salto en P: ");
        var saltoP = sc.nextInt();
        System.out.print("Ingresa el salto en Q: ");
        var saltoQ = sc.nextInt();
        Nodo nodo = new Nodo(posicionFila,posicionColumna);
        RamaYPoda rama = new RamaYPoda(posDestinoFilas,posDestinoColumnas,nodo,ancho,alto,saltoP,saltoQ);
    }
}

class APLMain {
    public static void main(String[] args) {
        Controller c = new Controller();
        c.consola();
    }
}
