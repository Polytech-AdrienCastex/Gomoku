/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku;

import exceptions.OutOfBoundException;
import exceptions.EmptyHistoryException;
import exceptions.InvalidPlayException;
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
        
        dernierId = 0;
        
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
        
        if(listCoups != null)
            for(Coup c : listCoups)
                etatPlateau[c.pos.x][c.pos.y] = c.id;
    }
    public void initialiser(Iterable<Coup> listCoups)
    {
        initialiser();
        
        if(listCoups != null)
            for(Coup c : listCoups)
                etatPlateau[c.pos.x][c.pos.y] = c.id;
    }
    
    public void jouer(Coup coup) throws OutOfBoundException, InvalidPlayException
    {
        if(!isValidPosition(coup.pos))
            throw new OutOfBoundException("Le coup doit se trouver dans les limites du plateau.");
        if(!isEmptyPosition(coup.pos))
            throw new InvalidPlayException(coup);
        
        dernierId = coup.id;
        
        etatPlateau[coup.pos.x][coup.pos.y] = coup.id;
        coups.push(coup);
    }
    
    public ArrayList<Coup> getSituation()
    {
        ArrayList<Coup> list = new ArrayList<>();
        
        Stack<Coup> coupsTemp = (Stack<Coup>)coups.clone();
        
        while(!coupsTemp.empty())
        {
            Coup coup = coupsTemp.pop();
            list.add(new Coup(coup.id, new Position(coup.pos.x, coup.pos.y)));
        }
        
        return list;
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
    
    public boolean isValidPosition(Position pos)
    {
        return isValidPosition(pos.x, pos.y);
    }
    public boolean isValidPosition(int x, int y)
    {
        return x >= 0 && x < longueur &&
                y >= 0 && y < largeur;
    }
    
    public boolean isEmptyPosition(Position pos)
    {
        return isEmptyPosition(pos.x, pos.y);
    }
    public boolean isEmptyPosition(int x, int y)
    {
        return etatPlateau[x][y] == CASE_VIDE;
    }
    
    public boolean isFull()
    {
        for(int y = 0; y < largeur; y++)
            for(int x = 0; x < longueur; x++)
                if(isEmptyPosition(x, y))
                    return false; // Pleateau plein
        return true; // Plateau non plein
    }
    
    
    private int dernierId;
    public int getDernierId()
    {
        return dernierId;
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
