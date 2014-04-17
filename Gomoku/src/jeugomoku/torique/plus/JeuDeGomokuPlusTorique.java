/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeugomoku.torique.plus;

import exceptions.OutOfBoundException;
import gomoku.PlateauGomoKu;
import gomoku.Position;
import jeugomoku.plus.JeuDeGomokuPlus;

/**
 *
 * @author p1002239
 */
public class JeuDeGomokuPlusTorique extends JeuDeGomokuPlus
{
    public JeuDeGomokuPlusTorique()
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
                    if(pgk.CheckLigneId(new Position(x, y), 3, idToCheck) &&
                            pgk.CheckColonneId(new Position(x + 1, y - 1), 3, idToCheck))
                        return true;
                }
        }
        
        return false;
    }
}
