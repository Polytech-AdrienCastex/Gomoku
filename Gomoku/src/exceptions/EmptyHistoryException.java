/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *
 * @author p1002239
 */
public class EmptyHistoryException extends Exception
{

    public EmptyHistoryException()
    {
        super("L'historique est vide.");
    }
    
}
