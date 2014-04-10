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
public class Coup
{
    public Position pos;
    public int id;
    
    public Coup(int _id, Position _pos)
    {
        this.id = _id;
        this.pos = _pos;
    }
    
    @Override
    public String toString()
    {
        return "[" + this.id + " " + this.pos + "]";
    }
}
