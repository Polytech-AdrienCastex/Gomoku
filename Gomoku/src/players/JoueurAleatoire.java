/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package players;

import exceptions.InvalidPlayException;
import gomoku.Coup;
import gomoku.Plateau;
import gomoku.Position;

/**
 *
 * @author p1002239
 */
public class JoueurAleatoire extends Joueur
{
    public JoueurAleatoire(int _id)
    {
        super(_id);
    }

    @Override
    public Coup genererCoup(Plateau etatJeu) throws InvalidPlayException
    {
        if(etatJeu.isFull())
            throw new InvalidPlayException("Le plateau est plein.");
        
        int x, y;
        Position pos;
        
        do
        {
            x = Utilitaire.monRandom(0, etatJeu.getLongueur() - 1);
            y = Utilitaire.monRandom(0, etatJeu.getLargeur() - 1);

            pos = new Position(x, y);
        } while(!etatJeu.isEmptyPosition(pos));
        
        return new Coup(getId(), pos);
    }
    
}
