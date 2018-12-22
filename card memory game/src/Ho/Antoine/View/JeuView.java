/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ho.Antoine.View;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Antoine Ho
 */
public class JeuView extends JFrame {

    // Attributes
    public JButton[][] buttonsHide;
    private JLabel tempsLabel;
    private int[][] imagesIndex;
    private JButton muteButton;

    // Constructeur
    public JeuView() {
        super("Jeu de mémoire");
        buttonsHide = new JButton[4][4];

        createImageIndex();
        initializeButtonsHide();
        initializeImages();
        initializeJeuView();

        tempsLabel = new JLabel("Temps restant: 89", JLabel.CENTER);
        tempsLabel.setBounds(140, 425, 105, 20);
        add(tempsLabel);
        
        muteButton = new JButton(new ImageIcon("unmuted.png"));
        muteButton.setBounds(350, 415, 40, 40);
        add(muteButton);
    }

    public JLabel getTempsLabel() {
        return tempsLabel;
    }
    
    /**
     * Créer un tableau 4x4 qui contient l'index des huits images.
     */
    private void createImageIndex() {
        imagesIndex = new int[4][4];
        // Chaque index correspond a l'image dans images[].
        int[] nbrOfTimesImgUsed = {0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < imagesIndex.length; i++) {
            for (int j = 0; j < imagesIndex[0].length; j++) {
                while (true){
                    int rand = (int) (Math.random() * 8) + 0;
                    if (nbrOfTimesImgUsed[rand] < 2) {
                        imagesIndex[i][j] = rand;
                        nbrOfTimesImgUsed[rand]++;
                        break;
                    }
                }   
            }
        }
    }

    /**
     * Initialise JeuView.
     */
    private void initializeJeuView() {
        setLayout(null);
        setSize(415, 500);
        setLocation(300, 200);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initialise les boutons qui cachent les images en dessous. Chaque 
     * boutons contient du text qui est l'index de l'image qu'il cache, donc 
     * deux boutons avec le text "2" cachent une paire.
     * 
     * Positionnement des bouttons dans le tableau en px:
     * ------------------------------------- 
     *  0,0  |  100,0  |  200,0  |  300,0  |
     * -------------------------------------
     * 0,100 | 100,100 | 200,100 | 300,100 |
     * ------------------------------------- 
     * 0,200 | 100,200 | 200,200 | 300,200 |
     * -------------------------------------
     * 0,300 | 100,300 | 200,300 | 300,300 | 
     * -------------------------------------
     */
    private void initializeButtonsHide() {
        ImageIcon vide = new ImageIcon("vide.png"); 
        int posX = 0;
        int posY = 0;
        for (int i = 0; i < buttonsHide.length; i++) {
            for (int j = 0; j < buttonsHide[0].length; j++) {
                String buttonIndex = Integer.toString(imagesIndex[i][j]);
                buttonsHide[i][j] = new JButton(buttonIndex, vide);
                // Cacher la valeur du text.
                buttonsHide[i][j].setFont(new Font("Arial", Font.PLAIN, 0));
                buttonsHide[i][j].setBounds(posX, posY, 100, 100);
                add(buttonsHide[i][j]);
                posX += 100;
            }
            posY += 100;
            posX = 0;
        }
    }
    
    /**
     * Ajoute les images selon l'ordre du tableau imagesIndex[] dans les
     * même positions que les buttonsHide.
     * (Voir le commentaire de la fonction initializeButtonsHide pour plus
     * d'information sur les positions)
     */
    private void initializeImages() {
        File imageFolder = new File("imagesMatch");
        File[] listImageFiles = imageFolder.listFiles();
        ImageIcon[] images = new ImageIcon[8];
        // Creation du tableau avec les images
        for (int i = 0; i < 8; i++) {
            if (listImageFiles[i].isFile()) {
                String imagePath = "imagesMatch/" + listImageFiles[i].getName();
                images[i] = new ImageIcon(imagePath);
            }
        }

        // Creation du array list avec les positions possibles pour les images.      
        int posX = 0;
        int posY = 0;
        for (int i = 0; i < buttonsHide.length; i++) {
            for (int j = 0; j < buttonsHide[0].length; j++) {
                int imageIndex = imagesIndex[i][j];
                add(new JLabel(images[imageIndex]))
                        .setBounds(posX, posY, 100, 100);
                posX += 100;
            }
            posY += 100;
            posX = 0;
        }
    }

    /**
     * Ajoute une fonction listener aux boutons.
     *
     * @param gameButtonListener La fonction que les boutons executes
     */
    public void addGameButtonListener(ActionListener gameButtonListener) {
        for (int i = 0; i < buttonsHide.length; i++) {
            for (int j = 0; j < buttonsHide[0].length; j++) {
                buttonsHide[i][j].addActionListener(gameButtonListener);
            }
        }
    }
    
    /**
     * Ajoute une fonction au bouton mute.
     * 
     * @param muteButtonListener 
     */
    public void addMuteButtonListener(ActionListener muteButtonListener){
        muteButton.addActionListener(muteButtonListener);
    }
    
    /**
     * Affiche un deuxième JFrame avec Neil DeGrasse Tyson.
     */
    public void victoryScreen(){
        JFrame victoryPanel = new JFrame();
        victoryPanel.setSize(208, 250);
        victoryPanel.setLocation(300, 200);
        victoryPanel.setVisible(true);
        
        JLabel win = new JLabel(new ImageIcon("victoire.png"));
        victoryPanel.add(win);
    }
    
    /**
     * Remplace l'écran avec une image pour les perdants.
     */
    public void loserScreen(){
        JLabel loss = new JLabel(new ImageIcon("aide.png"));
        getContentPane().removeAll();
        add(loss).setBounds(0, 0, 415, 500);
    }
    
    /**
     * Change le icon du muteButton selon le paramètre.
     * 
     * @param isMuted Boolean qui indique si le son est en sourdine ou pas.
     */
    public void flipMutedIcon(boolean isMuted){
        if (isMuted){
            muteButton.setIcon(new ImageIcon("unmuted.png"));
        }
        else{
            muteButton.setIcon(new ImageIcon("muted.png"));
        }
    }
}
