package co.edu.unbosque.controller;

import co.edu.unbosque.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Gabriel Blanco; Sebastian Moncaleano
 * @version 1.0
 * Clase controlador para unir la vista con el modelo
 */
public class Controller implements ActionListener {
    private MainView vista = new MainView();

    /**
     * Cargar la vista principal
     */
    public void loadGUI() {
        vista.start(this);
    }
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class APLMain {
    public static void main(String[] args) {
        Controller c = new Controller();
        c.loadGUI();
    }
}
