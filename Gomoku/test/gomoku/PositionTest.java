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
public class PositionTest {
    
    public PositionTest() {
    }

    /**
     * Test of Clone method, of class Position.
     */
    @Test
    public void testClone() {
        System.out.println("Clone");
        Position instance = new Position(5,3);
        Position expResult = new Position(5,3);
        Position result = instance.Clone();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Position.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Position instance = new Position(5,3);
        String expResult = "(5, 3)";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Position.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o1 = new Menu();
        Object o2 = new Position(5,3);
        Object o3 = new Position(3,5);
        Position instance = new Position(3,5);
        assertFalse(instance.equals(o1));
        assertFalse(instance.equals(o2));
        assertTrue(instance.equals(o3));
    }

    /**
     * Test of hashCode method, of class Position.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Position instance = new Position(5, 3);
        int expResult = (5 << 8) + 3;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        System.out.println("x = " + (result >> 8));
        System.out.println("y = " + (result & 0xFF));
    }
    
}
