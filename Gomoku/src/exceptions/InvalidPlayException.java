/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

import gomoku.Coup;
import gomoku.Position;

/**
 *
 * @author p1002239
 */
public class InvalidPlayException extends Exception
{
    public InvalidPlayException(Coup coup)
    {
        this(coup.pos);
    }
    public InvalidPlayException(Position pos)
    {
        this("La case " + pos + " est deja une case jouée. Elle ne peut pas être jouée à nouveau.");
    }

    public InvalidPlayException(String msg)
    {
        super(msg);
    }
    
}
