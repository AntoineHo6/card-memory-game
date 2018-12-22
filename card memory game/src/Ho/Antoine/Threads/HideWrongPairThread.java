/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ho.Antoine.Threads;

import Ho.Antoine.Controller.JeuController;
import Ho.Antoine.Model.JeuModel;
import Ho.Antoine.View.JeuView;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antoine Ho
 */
public class HideWrongPairThread extends Thread {
    // Attributes
    private JeuView view;
    private JeuModel model;
    private JeuController.GameButtonListener bl;

    // Constructeur
    public HideWrongPairThread(JeuView view, JeuModel model,
            JeuController.GameButtonListener bl) {
        this.view = view;
        this.model = model;
        this.bl = bl;
    }

    @Override
    public void run() {
        // Desactive les bouttons
        for (int i = 0; i < view.buttonsHide.length; i++) {
            for (int j = 0; j < view.buttonsHide[0].length; j++) {
                view.buttonsHide[i][j].removeActionListener(bl);
            }
        }

        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(JeuModel.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        model.hideChosenPair();

        // Reactive les bouttons
        for (int i = 0; i < view.buttonsHide.length; i++) {
            for (int j = 0; j < view.buttonsHide[0].length; j++) {
                view.buttonsHide[i][j].addActionListener(bl);
            }
        }
        
        // Rétablit l'état des boutons clické a Enabled pour que le jeux
        // puisse continuer a marcher.
        model.buttonsPressed[0].setEnabled(true);
        model.buttonsPressed[1].setEnabled(true);
    }
}
