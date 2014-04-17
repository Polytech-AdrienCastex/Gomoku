/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adrien
 */
public class CoupTest {
    
    public CoupTest() {
    }

    /**
     * Test of Clone method, of class Coup.
     */
    @Test
    public void testClone() {
        System.out.println("Clone");
        Coup instance = new Coup(5, new Position(5, 3));
        Coup expResult = new Coup(5, new Position(5, 3));
        Coup result = instance.Clone();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of toString method, of class Coup.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Position pos = new Position(5,3);
        Coup instance = new Coup(1, pos);
        String expResult = "[1 " + pos + "]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
