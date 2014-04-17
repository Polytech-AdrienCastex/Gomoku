/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku;

import exceptions.EmptyHistoryException;
import exceptions.InvalidPlayException;
import exceptions.OutOfBoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jeugomoku.JeuDeGomokuFactory;
import players.JoueurAleatoire;
import players.JoueurHumain;

/**
 *
 * @author p1002239
 */
public class SimulateurDeJeu
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws EmptyHistoryException, InvalidPlayException, Exception
    {
        //new Menu().run();
        SimulerJoueurs();
    }
    
    private static void SimulerJoueurs() throws Exception
    {
        ArrayList<Coup> coups = new ArrayList<Coup>();
        
        coups.add(new Coup(2, new Position(4, 3)));
        coups.add(new Coup(2, new Position(5, 3)));
        coups.add(new Coup(2, new Position(6, 3)));
        coups.add(new Coup(2, new Position(7, 3)));
        coups.add(new Coup(1, new Position(2, 0)));
        coups.add(new Coup(1, new Position(2, 1)));
        coups.add(new Coup(1, new Position(5, 2)));
        coups.add(new Coup(1, new Position(2, 5)));
        
        new JeuDeGomokuFactory().CreerPartieHumainVSMonteCarlo(coups).jouerPartie();
    }
    
}
