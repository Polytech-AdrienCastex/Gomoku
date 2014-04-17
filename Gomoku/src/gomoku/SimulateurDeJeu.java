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
            SimulerJoueurs();
            /*
        try
        {
            
            PlateauGomoKu p = new PlateauGomoKu(6,7);
            p.initialiser();
            System.out.println(p);
            */
            
            /*
            System.out.println("Chargement d'une configuration initiale");
            ArrayList<Coup> positionInitiale = new ArrayList<Coup>();
            positionInitiale.add(new Coup(2, new Position(1,1)));
            positionInitiale.add(new Coup(4, new Position(1,2)));
            positionInitiale.add(new Coup(2, new Position(2,1)));
            p.initialiser(positionInitiale);
            System.out.println(p);
            System.out.println("Premier coup du joueur 4");
            p.jouer(new Coup(4, new Position(5, 6)));
            System.out.println(p);
            System.out.println("Premier coup du joueur 2");
            p.jouer(new Coup(2, new Position(3, 2)));
            System.out.println(p);
            System.out.println("Second coup du joueur 4 (invalide)");
            //p.jouer(new Coup(4, new Position(3, 9)));
            System.out.println("Second coup du joueur 4 (valide)");
            p.jouer(new Coup(4, new Position(3, 3)));
            System.out.println(p);
            System.out.println("Annulation du dernier coup jouÃ©");
            p.annuler();
            System.out.println(p);
            System.out.println("Nouveau coup du joueur 4");
            p.jouer(new Coup(4, new Position(5, 6)));
            System.out.println(p);
            Position p1=new Position(1,1);
            System.out.println("Verification Ligne (sur 2 cases)  pour le joueur 2 Ã  partir de "+p1);
            System.out.println(p.CheckLigneId(p1, 2, 2));
            System.out.println("Verification Colonne (sur 2 cases) pour le joueur 2 Ã  partir de "+p1);
            System.out.println(p.CheckColonneId(p1, 2, 2));
            Position p2=new Position(3,6);
            System.out.println("Verification Ligne (sur 2 cases)  pour le joueur 4 Ã  partir de "+p2);
            System.out.println(p.CheckLigneId(p2, 2, 4));
            Position p3=new Position(3,6);
            System.out.println("Verification Ligne (sur 2 cases) pour le joueur 4 Ã  partir de "+p3);
            System.out.println(p.CheckLigneId(p3, 2, 4));
            System.out.println("Reinitialisation du plateau");
            p.initialiser();
            System.out.println(p);*/
            
            
            
            /*
            PlateauGomoKu plateau = new PlateauGomoKu();
            plateau.initialiser(new Coup[] {new Coup(1, new Position(0, 0))});
            
            Coup c = new Coup(2, new Position(1, 0));
            plateau.jouer(c);
            
            System.out.println(plateau);*//*
        }
        catch (OutOfBoundException ex)
        {
            Logger.getLogger(SimulateurDeJeu.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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
