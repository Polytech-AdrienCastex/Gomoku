
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
    
    /**
     *
     * @return  
     */
    @Override
    public String toString()
    {
        return "(" + this.x + ", " + this.y + ")";
    }
}
