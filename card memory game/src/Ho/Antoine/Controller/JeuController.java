/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ho.Antoine.Controller;

import Ho.Antoine.Model.JeuModel;
import Ho.Antoine.Threads.HideWrongPairThread;
import Ho.Antoine.Threads.TimerThread;
import Ho.Antoine.View.JeuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Antoine Ho
 */
public class JeuController {
    // Attributes
    private JeuView view;
    private JeuModel model;
    
    // Constructeurs
    public JeuController(JeuView view, JeuModel model){
        this.view = view;
        this.model = model;
        
        this.view.addGameButtonListener(new GameButtonListener());
        this.view.addMuteButtonListener(new MuteButtonListener());
        
        new TimerThread(view, model).start();
    }
    
    public class GameButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton button = (JButton) ae.getSource();
            button.setEnabled(false);
            button.setVisible(false);
            
            // Stock le bouton appuyé, sa valeur et incrémente l'info du nbr
            // de boutons clickés.
            model.buttonsPressed[model.nbrButtonPressed] = button;
            model.buttonValues[model.nbrButtonPressed] = button.getText();
            model.nbrButtonPressed++;
            
            // Si deux boutons sont appuyés, on vérifie s'il a gagné
            if(model.nbrButtonPressed == 2){
                if(model.buttonValues[0].equals(model.buttonValues[1])){
                    model.victory = model.verifyVictory(view.buttonsHide);
                    if (model.victory){
                        view.victoryScreen();
                    }
                }
                // Images do not match
                else{
                    // Pause avant de cacher la paire d'image choisi
                    new HideWrongPairThread(view, model, this).start();
                }
                
                model.nbrButtonPressed = 0;
            }
        }
    }
    
    public class MuteButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            view.flipMutedIcon(model.isMuted);
            model.flipMutedState();

        }
    }
}


