/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku;

/**
 *
 * @author p1002239
 */
class OutOfBoundException extends Exception
{
    public OutOfBoundException(String msg)
    {
        super(msg);
    }
}
