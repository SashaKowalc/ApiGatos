package org.example;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        int optMenu = -1;
        String [] buttons = {"1. Ver gatos", "2. Ver favoritos", " 3. Salir"};

        do {
            //Menu principal
            String option = (String) JOptionPane.showInputDialog(null, "Gatitos Java", "Menu principal", JOptionPane.INFORMATION_MESSAGE,
                    null, buttons, buttons[0]);

            //Validamos que opcion selecciona el usuario
            for (int i = 0; i < buttons.length ; i++) {
                if(option.equals(buttons[i])) {
                    optMenu = i;
                }
            }

            switch (optMenu) {
                case 0:
                    GatosService.verGatos();
                    break;
                case 1:
                    Gatos gato = new Gatos();
                    GatosService.verFavoritos(gato.getApiKey());
                    break;
                default:
                    break;
            }
        } while (optMenu != 1);


    }
}