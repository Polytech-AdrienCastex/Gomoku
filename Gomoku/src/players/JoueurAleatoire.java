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
import java.util.ArrayList;

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
        ArrayList<Position> choix_possibles = etatJeu.etatId(0);
        
        if(choix_possibles.isEmpty())
            throw new InvalidPlayException("Le plateau est plein.");
        
        int index = Utilitaire.monRandom(0, choix_possibles.size());
        Position choix = choix_possibles.get(index);
        
        return new Coup(getId(), choix);
    }
    
}
