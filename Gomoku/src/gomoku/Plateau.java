/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author p1002239
 */
public class Plateau
{
    protected final int CASE_VIDE = 0;
    
    protected final int longueur;
    protected final int largeur;
    protected int[][] etatPlateau;
    
    private Stack<Coup> coups;
    
    public Plateau(int _longueur, int _largeur) throws OutOfBoundException
    {
        if(_longueur < 0)
            throw new OutOfBoundException("La longueur du plateau doit être au moins égale à 0.");
        if(_largeur < 0)
            throw new OutOfBoundException("La largeur du plateau doit être au moins égale à 0.");
        
        longueur = _longueur;
        largeur = _largeur;
        
        etatPlateau = new int[longueur][largeur];
        coups = new Stack<>();
    }
    
    public void initialiser()
    {
        for(int x = 0; x < longueur; x++)
            for(int y = 0; y < largeur; y++)
                etatPlateau[x][y] = CASE_VIDE;
    }
    public void initialiser(Coup[] listCoups)
    {
        initialiser();
        
        for(Coup c : listCoups)
            etatPlateau[c.pos.x][c.pos.y] = c.id;
    }
    public void initialiser(Iterable<Coup> listCoups)
    {
        initialiser();
        
        for(Coup c : listCoups)
            etatPlateau[c.pos.x][c.pos.y] = c.id;
    }
    
    public void jouer(Coup coup) throws OutOfBoundException
    {
        if(!isValidPosition(coup.pos))
            throw new OutOfBoundException("Le coup doit se trouver dans les limites du plateau.");
        
        etatPlateau[coup.pos.x][coup.pos.y] = coup.id;
        coups.push(coup);
    }
    
    public ArrayList<Position> etatId(int id)
    {
        ArrayList<Position> list = new ArrayList<>();
        
        Stack<Coup> coupsTemp = (Stack<Coup>)coups.clone();
        
        while(!coupsTemp.empty())
        {
            Coup coup = coupsTemp.pop();
                if(etatPlateau[coup.pos.x][coup.pos.y] == id)
                    list.add(new Position(coup.pos.x, coup.pos.y));
        }
        
        /*
        for(int x = 0; x < largeur; x++)
            for(int y = 0; y < largeur; y++)
                if(etatPlateau[x][y] == id)
                    list.add(new Position(x, y));
        */
        
        return list;
    }
    
    public Coup annuler() throws EmptyHistoryException
    {
        if(coups.empty())
            throw new EmptyHistoryException();
        
        Coup lastCoup = coups.pop();
        
        etatPlateau[lastCoup.pos.x][lastCoup.pos.y] = CASE_VIDE;
        
        return lastCoup;
    }
    
    public int getLongueur()
    {
        return longueur;
    }
    
    public int getLargeur()
    {
        return largeur;
    }
    
    protected boolean isValidPosition(Position pos)
    {
        return pos.x >= 0 && pos.x < longueur &&
                pos.y >= 0 && pos.y < largeur;
    }
    
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        
        for(int y = 0; y < largeur; y++)
        {
            for(int x = 0; x < longueur; x++)
            {
                str.append(etatPlateau[x][y]);
                str.append(" ");
            }
            
            str.append(System.getProperty("line.separator"));
        }
        
        return str.toString();
    }
}
