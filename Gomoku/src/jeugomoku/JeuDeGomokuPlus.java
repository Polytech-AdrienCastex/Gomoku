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
import players.JoueurHumain;

/**
 *
 * @author p1002239
 */
public class JeuDeGomokuPlus extends JeuDeGomoku
{
    public JeuDeGomokuPlus()
    {
        super();
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public boolean partieTerminee() throws OutOfBoundException
    {
        if(plateau instanceof PlateauGomoKu)
        { // Plateau GomoKu
            PlateauGomoKu pgk = (PlateauGomoKu)plateau;
            
            if(joueurCourant == null)
                joueurCourant = joueurSuivant();
            int idToCheck = joueurCourant.getId();
            
            try
            {
                for(int x = 0; x < plateau.getLongueur() - 2; x++)
                    for(int y = 1; y < plateau.getLargeur() - 1; y++)
                    {
                        if(pgk.CheckLigneId(new Position(x, y), 3, idToCheck) &&
                                pgk.CheckColonneId(new Position(x + 1, y - 1), 3, idToCheck))
                            return true;
                    }
            }
            catch(OutOfBoundException ex) // Erreur inattendue
            {
                throw ex;
            }
        }
        
        return false;
    }
}
