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
public class PlateauGomoKuTorique extends PlateauGomoKu
{
    public PlateauGomoKuTorique() throws OutOfBoundException
    {
        super();
    }
    
    @Override
    public boolean CheckLigneId(Position pos, int n, int id)
    {
        int y = getY(pos.y);
        
        for(int x = pos.x; x < pos.x + n; x++)
            if(super.etatPlateau[getX(x)][y] != id)
                return false;
        
        return true;
    }
    @Override
    public boolean CheckColonneId(Position pos, int n, int id)
    {
        int x = getX(pos.x);
        
        for(int y = pos.y; y < pos.y + n; y++)
            if(super.etatPlateau[x][getY(y)] != id)
                return false;
        
        return true;
    }
    
    private int getX(int x)
    {
        int len = etatPlateau.length;
        return (x + len) % len;
    }
    private int getY(int y)
    {
        int len = etatPlateau[0].length;
        return (y + len) % len;
    }
}
