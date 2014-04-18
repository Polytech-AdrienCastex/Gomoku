/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku;

import java.util.ArrayList;
import jeugomoku.JeuDeGomokuFactory;

/**
 *
 * @author p1002239
 */
public class SimulateurDeJeu
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
        //new Menu().run(); // Lancer l'execution du menu, et donc du jeu
        SimulerJoueurs(); // Test avec le plateau de test
    }
    
    private static void SimulerJoueurs() throws Exception
    {
        ArrayList<Coup> coups = new ArrayList<>();
        
        coups.add(new Coup(2, new Position(4, 3)));
        coups.add(new Coup(2, new Position(5, 3)));
        coups.add(new Coup(2, new Position(6, 3)));
        coups.add(new Coup(2, new Position(7, 3)));
        coups.add(new Coup(1, new Position(2, 0)));
        coups.add(new Coup(1, new Position(2, 1)));
        coups.add(new Coup(1, new Position(5, 2)));
        coups.add(new Coup(1, new Position(2, 5)));
        
        long startTime = System.nanoTime();
        new JeuDeGomokuFactory().CreerPartieHumainVSMonteCarlo(coups).jouerPartie();
        long savedTime = (System.nanoTime() - startTime) / 1000000; // en ms
        System.out.println("2eme avec 8 pions deja placés : " + savedTime + "ms");
    }
    
}
