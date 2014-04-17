
package gomoku;

/**
 * Permet de représenter une position bidimentionnelle.
 *
 * @author p1002239
 */
public class Position
{
    /**
     * 
     */
    public int x;

    /**
     *
     */
    public int y;
    
    /**
     * 
     * @param x position horizontale
     * @param y position verticale
     */
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Position Clone()
    {
        return new Position(this.x, this.y);
    }
    
    /**
     *
     * @return  
     */
    @Override
    public String toString()
    {
        return "(" + this.x + ", " + this.y + ")";
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Position)
            return ((Position)o).hashCode() == this.hashCode();
        return false;
    }
    
    @Override
    public int hashCode()
    {
        return x * 100 + y;
    }
}
