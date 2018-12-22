/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ho.Antoine.Model;

import javax.swing.JButton;
import javax.swing.JLabel;


/**
 *
 * @author Antoine Ho
 */
public class JeuModel {

    public int temps;
    public int nbrButtonPressed;
    public JButton[] buttonsPressed;
    public String[] buttonValues;
    public boolean victory;
    public boolean isMuted;

    public JeuModel() {
        temps = 84;
        nbrButtonPressed = 0;
        buttonsPressed = new JButton[2];
        buttonValues = new String[2];
        victory = false;
        isMuted = false;
    }
    
    /**
     * Decremente le temps une fois.
     * 
     * @param tempsLabel C'est le temps affiché.
     */
    public void decrementeTemps(JLabel tempsLabel) {
            temps--;
            tempsLabel.setText("Temps restant: " + temps);
    }
    
    /**
     * Cache la paire d'images révélées.
     */
    public void hideChosenPair() {
        buttonsPressed[0].setVisible(true);
        buttonsPressed[1].setVisible(true);
        nbrButtonPressed = 0;
    }
    
    /**
     * Quand un bouton qui cache l'image est disabled, cela indique qu'une paire
     * d'images a été identifier. Alors si tout les boutons sont disabled, le 
     * joueur a trouvé toute les paires.
     * 
     * @param buttonsHide Le tableau des boutons qui cachent les images.
     * @return Un boolean qui indique si le joueur a gagné ou pas.
     */
    public boolean verifyVictory(JButton[][] buttonsHide){
        for (int i = 0; i < buttonsHide.length; i++) {
            for (int j = 0; j < buttonsHide[0].length; j++) {
                if (buttonsHide[i][j].isEnabled()){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Change l'état du bouton mute à son opposé.
     */
    public void flipMutedState(){
        isMuted = !isMuted;
    }
}
