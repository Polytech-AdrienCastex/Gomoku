/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeugomoku;

import exceptions.InvalidPlayException;
import exceptions.OutOfBoundException;
import gomoku.Coup;
import gomoku.Plateau;
import gomoku.PlateauGomoKu;
import gomoku.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;
import players.Joueur;

/**
 *
 * @author p1002239
 */
public class JeuDeGomoku extends JeuDePlateau
{
    public JeuDeGomoku()
    {
        super();
    }
    
    @Override
    public boolean partieTerminee() throws OutOfBoundException
    {
        if(plateau instanceof PlateauGomoKu)
        { // Plateau GomoKu
            PlateauGomoKu pgk = (PlateauGomoKu)plateau;
            
            try
            {
                // Vérification des colonnes
                for(int x = 0; x < plateau.getLongueur(); x++)
                    for(int y = 0; y < plateau.getLargeur() - 4; y++)
                        if(pgk.CheckColonneId(new Position(x, y), 5, joueurCourant.getId()))
                            return true;
                
                // Vérification des lignes
                for(int y = 0; y < plateau.getLargeur(); y++)
                    for(int x = 0; x < plateau.getLongueur() - 4; x++)
                        if(pgk.CheckLigneId(new Position(x, y), 5, joueurCourant.getId()))
                            return true;
            }
            catch(OutOfBoundException ex) // Erreur inattendue
            {
                throw ex;
            }
        }
        
        return false;
    }
    
    @Override
    public Joueur jouerPartie() throws OutOfBoundException
    {
        try
        {
            Coup c;
            do
            {
                if(plateau.isFull()) // Match nul
                    return null;
                
                joueurCourant = joueurSuivant();

                boolean ok = true;
                do
                {
                    c = joueurCourant.genererCoup(plateau);

                    if(coupValide(c))
                    {
                        plateau.jouer(c);
                    }
                    else
                    {
                        System.out.println("Coup non valide! Jouez à nouveau.");
                        ok = false;
                    }
                } while(!ok);

            } while(!partieTerminee());

            return joueurCourant;
        }
        catch(InvalidPlayException ex) // Fin de partie : Match nul
        {
            System.out.println(ex.getMessage());
            return null;
        }
        catch(OutOfBoundException ex) // Erreur inattendue
        {
            // - Coup joué en dehors du plateau
            // - Incapacité à vérifier si la partie est terminée
            
            throw ex;
        }
    }
}
