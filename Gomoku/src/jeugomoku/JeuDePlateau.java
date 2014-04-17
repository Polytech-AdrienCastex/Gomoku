/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeugomoku;

import exceptions.OutOfBoundException;
import gomoku.Coup;
import gomoku.Plateau;
import java.util.ArrayList;
import players.Joueur;

/**
 *
 * @author p1002239
 */
public abstract class JeuDePlateau
{
    public JeuDePlateau()
    {
        joueurs = new Joueur[2];
        joueurCourant = null;
        
        plateau = null;
    }
    
    
    protected Plateau plateau;
    
    protected Joueur[] joueurs;
    protected Joueur joueurCourant;
    
    
    public void setJoueur(int ordre, Joueur joueur) throws OutOfBoundException
    {
        if(ordre == 1 || ordre == 2)
            joueurs[ordre - 1] = joueur;
        else
            throw new OutOfBoundException("L'ordre du joueur doit être soit 1, soit 2 (ici ordre=" + ordre + ").");
        
        if(joueurCourant == null && ordre == 1)
            joueurCourant = joueurs[0];
    }
    
    public void setPlateau(Plateau plateau)
    {
        this.plateau = plateau;
    }
    
    protected Joueur joueurSuivant()
    {
        int id;
        
        if(joueurCourant == null)
            id = plateau.getDernierId();
        else
            id = joueurCourant.getId();
        
        if(joueurs[0] == null || joueurs[0].getId() == id)
            return joueurs[1];
        else
            return joueurs[0];
    }
    
    public boolean coupValide(Coup coup)
    {
        return plateau.isEmptyPosition(coup.pos) && plateau.isValidPosition(coup.pos);
    }
    
    public ArrayList<Coup> getSituation()
    {
        return plateau.getSituation();
    }
    
    public abstract boolean partieTerminee() throws OutOfBoundException;
    public abstract Joueur jouerPartie() throws OutOfBoundException;
}
