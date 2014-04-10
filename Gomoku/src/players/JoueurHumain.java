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
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author p1002239
 */
public class JoueurHumain extends Joueur
{
    public JoueurHumain(int _id)
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
        boolean ok = false;
        
        Scanner scan = new Scanner(System.in);
        
        do
        {
            x = getIntFromScanner(scan, "Indiquez une position x : ");
            y = getIntFromScanner(scan, "Indiquez une position y : ");

            pos = new Position(x, y);
            
            if(!etatJeu.isValidPosition(pos))
                System.out.println("La position " + pos + " est hors du plateau.");
            else
                if(!etatJeu.isEmptyPosition(pos))
                    System.out.println("La position " + pos + " est deja une position jouée.");
                else
                    ok = true;
        } while(!ok);
        
        return new Coup(getId(), pos);
    }
    
    protected int getIntFromScanner(Scanner scanner, String msg)
    {
        int value;
        
        while(true)
        {
            System.out.print(msg);
            try
            {
                value = scanner.nextInt();
                break;
            }
            catch(InputMismatchException ex)
            {
                System.out.println("Valeur entrée invalide. Ce doit être un entier.");
            }
        }
        
        return value;
    }
}
