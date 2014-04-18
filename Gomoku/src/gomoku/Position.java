
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
        // return(base 2) = 00 00 ... 00 00 x7 x6 ... x1 x0 y7 y6 ... y1 y0;
        // x = return >> 8;
        // y = return * 0xFF;
        return (x << 8) + y;
    }
}
