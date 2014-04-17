/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package players;

import exceptions.InvalidPlayException;
import gomoku.Coup;
import gomoku.Plateau;

/**
 *
 * @author p1002239
 */
public abstract class Joueur
{
    public Joueur(int _id)
    {
        this.id = _id;
    }
    
    protected final int id;
    
    public int getId()
    {
        return id;
    }
    
    public abstract Coup genererCoup(Plateau etatJeu) throws InvalidPlayException;
    
    @Override
    public String toString()
    {
        return "Joueur n°" + getId();
    }
}
