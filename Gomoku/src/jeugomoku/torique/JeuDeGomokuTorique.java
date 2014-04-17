/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeugomoku.torique;

import exceptions.InvalidPlayException;
import exceptions.OutOfBoundException;
import gomoku.Coup;
import gomoku.PlateauGomoKu;
import gomoku.Position;
import jeugomoku.JeuDeGomoku;
import players.Joueur;
import players.JoueurHumain;

/**
 *
 * @author p1002239
 */
public class JeuDeGomokuTorique extends JeuDeGomoku
{
    public JeuDeGomokuTorique()
    {
        super();
    }
    
    @Override
    public boolean partieTerminee() throws OutOfBoundException
    {
        if(plateau instanceof PlateauGomoKu)
        { // Plateau GomoKu
            PlateauGomoKu pgk = (PlateauGomoKu)plateau;
            
            if(joueurCourant == null)
                joueurCourant = joueurSuivant();
            int idToCheck = joueurCourant.getId();
            
            for(int x = 0; x < plateau.getLongueur(); x++)
                for(int y = 0; y < plateau.getLargeur(); y++)
                {
                    // Vérification des colonnes
                    if(pgk.CheckColonneId(new Position(x, y), 5, idToCheck))
                        return true;

                    // Vérification des lignes
                    if(pgk.CheckLigneId(new Position(x, y), 5, idToCheck))
                        return true;
                }
        }
        
        return false;
    }
}
