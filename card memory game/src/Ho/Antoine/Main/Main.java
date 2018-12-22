/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ho.Antoine.Main;

import Ho.Antoine.Controller.JeuController;
import Ho.Antoine.Model.JeuModel;
import Ho.Antoine.View.JeuView;
import javax.swing.SwingUtilities;

/**
 *
 * @author Antoine Ho
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JeuView view = new JeuView();
                JeuModel model = new JeuModel();
                JeuController controller = new JeuController(view, model);
            }
        });
    }
}
