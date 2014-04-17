/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeugomoku.plus;

import exceptions.OutOfBoundException;
import gomoku.PlateauGomoKu;
import gomoku.Position;
import jeugomoku.JeuDeGomoku;

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
    public boolean partieTerminee() throws OutOfBoundException
    {
        if(plateau instanceof PlateauGomoKu)
        { // Plateau GomoKu
            PlateauGomoKu pgk = (PlateauGomoKu)plateau;
            
            if(joueurCourant == null)
                joueurCourant = joueurSuivant();
            int idToCheck = joueurCourant.getId();
            
            for(int x = 0; x < plateau.getLongueur() - 2; x++)
                for(int y = 1; y < plateau.getLargeur() - 1; y++)
                {
                    if(pgk.CheckLigneId(new Position(x, y), 3, idToCheck) &&
                            pgk.CheckColonneId(new Position(x + 1, y - 1), 3, idToCheck))
                        return true;
                }
        }
        
        return false;
    }
}
