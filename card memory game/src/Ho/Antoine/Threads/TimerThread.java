/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ho.Antoine.Threads;

import Ho.Antoine.Model.JeuModel;
import Ho.Antoine.View.JeuView;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antoine Ho
 */
public class TimerThread extends Thread {
    // Attributes
    private JeuView view;
    private JeuModel model;
    
    // Constructeur
    public TimerThread(JeuView view, JeuModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void run() {
        // Decremente le temps a chaque seconde.
        while (model.temps > 0) {
            model.decrementeTemps(view.getTempsLabel());

            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(JeuModel.class.getName())
                        .log(Level.SEVERE, null, ex);
            }

            if (model.victory) {
                return;
            }
        }
        // Le joueur n'a plus de temps.
        view.loserScreen();
    }
}
