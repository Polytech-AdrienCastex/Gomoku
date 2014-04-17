/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeugomoku.plus;

import jeugomoku.plus.JeuDeGomokuPlus;
import exceptions.OutOfBoundException;
import gomoku.Coup;
import gomoku.PlateauGomoKu;
import gomoku.Position;
import java.util.ArrayList;
import static org.junit.Assert.fail;
import org.junit.Test;
import players.Joueur;
import players.JoueurAleatoire;

/**
 *
 * @author Adrien
 */
public class JeuDeGomokuPlusTest {
    
    public JeuDeGomokuPlusTest() {
    }
    
    @Test
    public void testPartieTerminee() throws OutOfBoundException
    {
        JeuDeGomokuPlus jdg = new JeuDeGomokuPlus();
        
        Joueur j1 = new JoueurAleatoire(1);
        Joueur j2 = new JoueurAleatoire(2);
        jdg.setJoueur(1, j1);
        jdg.setJoueur(2, j2);
        
        ArrayList<Coup> coups = new ArrayList<>();
        coups.add(new Coup(1, new Position(6, 7)));
        coups.add(new Coup(1, new Position(7, 6)));
        coups.add(new Coup(1, new Position(7, 7)));
        coups.add(new Coup(1, new Position(7, 8)));
        coups.add(new Coup(1, new Position(8, 7)));
        coups.add(new Coup(2, new Position(5, 5)));
        
        PlateauGomoKu pgk = new PlateauGomoKu();
        pgk.initialiser(coups);
        jdg.setPlateau(pgk);
        
        Joueur joueurVainqueur = jdg.jouerPartie();
        if(joueurVainqueur != j1)
        {
            System.out.println(pgk);
            fail("Le joueur retourné doit être le joueur 1!");
        }
        
        coups.clear();
        coups.add(new Coup(2, new Position(6, 7)));
        coups.add(new Coup(2, new Position(7, 6)));
        coups.add(new Coup(2, new Position(7, 7)));
        coups.add(new Coup(2, new Position(7, 8)));
        coups.add(new Coup(2, new Position(8, 7)));
        coups.add(new Coup(1, new Position(5, 5)));
        pgk.initialiser(coups);
        
        joueurVainqueur = jdg.jouerPartie();
        if(joueurVainqueur != j2)
        {
            System.out.println(pgk);
            fail("Le joueur retourné doit être le joueur 2!");
        }
        
        coups.clear();
        int etat = 1;
        for(int x = 0; x < 9; x++)
            for(int y = 0; y < 9; y++)
            {
                coups.add(new Coup(etat, new Position(x, y)));
                if(etat == 1)
                    etat = 2;
                else
                    etat = 1;
            }
        pgk.initialiser(coups);
        
        joueurVainqueur = jdg.jouerPartie();
        if(joueurVainqueur != null)
        {
            System.out.println(pgk);
            fail("Le joueur retourné doit être null pour indiquer un match nul!");
        }
    }
}
