package co.edu.unbosque.view;

import co.edu.unbosque.controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * @author Gabriel Blanco
 * @version 1.0
 * Clase donde se proyecta la vista principal.
 */
public class MainView extends JFrame implements Components {

    /**
     * Método para iniciar la vista principal
     * @param c
     */
    public void start(Controller c) {
        configComponents();
        addComponents();
        listenComponents(c);
    }
    /**
     * Método para configurar componentes
     */
    @Override
    public void configComponents() {
        this.setTitle("Proyecto Final - Sebastian Moncaleano - Gabriel Blanco");
        this.setSize(1024, 768);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Método para pintar componentes
     */
    @Override
    public void addComponents() {

    }

    /**
     * Método para escuchar componentes
     */
    public void listenComponents(Controller c) {

    }
}
