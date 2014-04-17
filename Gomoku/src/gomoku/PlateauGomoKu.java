/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku;

import exceptions.OutOfBoundException;

/**
 *
 * @author p1002239
 */
public class PlateauGomoKu extends Plateau
{
    public PlateauGomoKu() throws OutOfBoundException
    {
        this(9, 9);
    }
    public PlateauGomoKu(int _longueur, int _largeur) throws OutOfBoundException
    {
        super(_longueur, _largeur);
    }
    
    public boolean CheckLigneId(Position pos, int n, int id) throws OutOfBoundException
    {
        int y = pos.y;
        
        for(int x = pos.x; x < pos.x + n; x++)
            if(super.etatPlateau[x][y] != id)
                return false;
        
        return true;
    }
    public boolean CheckColonneId(Position pos, int n, int id) throws OutOfBoundException
    {
        int x = pos.x;
        
        for(int y = pos.y; y < pos.y + n; y++)
            if(super.etatPlateau[x][y] != id)
                return false;
        
        return true;
    }
}
