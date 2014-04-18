/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku;

import exceptions.EmptyHistoryException;
import exceptions.InvalidPlayException;
import exceptions.OutOfBoundException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adrien
 */
public class PlateauTest {
    
    public PlateauTest() {
    }

    /**
     * Test of jouer method, of class Plateau.
     */
    @Test
    public void testJouer() throws Exception {
        System.out.println("jouer");
        
        Coup[] coups = new Coup[]
        {
            new Coup(1, new Position(0, 0)),
            new Coup(1, new Position(4, 0)),
            new Coup(1, new Position(0, 4)),
            new Coup(1, new Position(4, 4)),
            new Coup(1, new Position(2, 2))
        };
        Plateau instance = new Plateau(5, 5);
        instance.initialiser();
        try
        {
            for(Coup c : coups)
                instance.jouer(c);
        }
        catch(InvalidPlayException ex)
        {
            fail("Le coup joué devait être valide!");
        }
        
        coups = new Coup[]
        {
            new Coup(1, new Position(-1, 0)),
            new Coup(1, new Position(0, -1)),
            new Coup(1, new Position(-1, -1)),
            new Coup(1, new Position(5, 0)),
            new Coup(1, new Position(0, 5)),
            new Coup(1, new Position(5, 5)),
            new Coup(1, new Position(-1, 5)),
            new Coup(1, new Position(5, -1))
        };
        instance.initialiser();
        for(Coup c : coups)
        {
            try
            {
                instance.jouer(c);
                fail("Le coup joué ne devait pas être valide!");
            }
            catch(OutOfBoundException ex)
            { }
        }
    }

    /**
     * Test of getSituation method, of class Plateau.
     */
    @Test
    public void testGetSituation() throws OutOfBoundException {
        System.out.println("getSituation");
        Coup[] coups = new Coup[]
        {
            new Coup(1, new Position(0, 0)),
            new Coup(2, new Position(4, 0)),
            new Coup(3, new Position(0, 4)),
            new Coup(4, new Position(4, 4)),
            new Coup(5, new Position(2, 2))
        };
        Plateau instance = new Plateau(5, 5);
        instance.initialiser(coups);
        
        ArrayList<Coup> result = instance.getSituation();
        assertEquals(coups.length, result.size());
        for(int i = 0; i < coups.length; i++)
            if(!coups[i].pos.equals(result.get(i).pos) || coups[i].id != result.get(i).id)
                fail("getSituation ne retourne pas les bons résultats");
    }

    /**
     * Test of etatId method, of class Plateau.
     */
    @Test
    public void testEtatId() throws OutOfBoundException {
        System.out.println("etatId");
        Coup[] coups = new Coup[]
        {
            new Coup(3, new Position(0, 0)), // !
            new Coup(2, new Position(4, 0)),
            new Coup(3, new Position(0, 4)), // !
            new Coup(4, new Position(4, 4)),
            new Coup(3, new Position(2, 2))  // !
        };
        Plateau instance = new Plateau(5, 5);
        instance.initialiser(coups);
        
        ArrayList<Position> result = instance.etatId(3);
        
        assertEquals(3, result.size());
        int index = result.size() - 1;
        for(Coup c : coups)
            if(c.id == 3)
                if(!c.pos.equals(result.get(index)))
                    fail("getSituation ne retourne pas les bons résultats");
                else
                    index--;
    }

    /**
     * Test of annuler method, of class Plateau.
     */
    @Test
    public void testAnnuler() throws Exception {
        System.out.println("annuler");
        Coup[] coups = new Coup[]
        {
            new Coup(1, new Position(0, 0)),
            new Coup(2, new Position(4, 0)),
            new Coup(3, new Position(0, 4)),
            new Coup(4, new Position(4, 4)),
            new Coup(5, new Position(2, 2))
        };
        Plateau instance = new Plateau(5, 5);
        instance.initialiser(coups);
        instance.annuler();
        ArrayList<Coup> result = instance.getSituation();
        assertEquals(coups.length - 1, result.size());
        for(int i = 0; i < coups.length - 1; i++)
            if(!coups[i].pos.equals(result.get(i).pos) || coups[i].id != result.get(i).id)
                fail("getSituation ne retourne pas les bons résultats");
        
        instance.annuler();
        instance.annuler();
        instance.annuler();
        instance.annuler();
        try
        {
            instance.annuler();
            fail("On ne doit pas pouvoir annuler un coup si il n'y a plus de coup dans l'historique!");
        }
        catch(EmptyHistoryException ex)
        { }
    }

    /**
     * Test of getLongueur method, of class Plateau.
     */
    @Test
    public void testGetLongueur() throws OutOfBoundException {
        System.out.println("getLongueur");
        Plateau instance = new Plateau(6, 1);
        assertEquals(6, instance.getLongueur());
    }

    /**
     * Test of getLargeur method, of class Plateau.
     */
    @Test
    public void testGetLargeur() throws OutOfBoundException {
        System.out.println("getLargeur");
        Plateau instance = new Plateau(6, 1);
        assertEquals(1, instance.getLargeur());
    }

    /**
     * Test of isValidPosition method, of class Plateau.
     */
    @Test
    public void testIsValidPosition() throws OutOfBoundException {
        System.out.println("isValidPosition");
        Plateau instance = new Plateau(5, 5);
        instance.initialiser();
        
        assertFalse(instance.isValidPosition(-1, 2));
        assertFalse(instance.isValidPosition(2, -1));
        assertFalse(instance.isValidPosition(5, 2));
        assertFalse(instance.isValidPosition(2, 5));
        
        assertTrue(instance.isValidPosition(0, 0));
        assertTrue(instance.isValidPosition(4, 0));
        assertTrue(instance.isValidPosition(0, 4));
        assertTrue(instance.isValidPosition(4, 4));
    }

    /**
     * Test of isEmptyPosition method, of class Plateau.
     */
    @Test
    public void testIsEmptyPosition() throws OutOfBoundException, InvalidPlayException {
        System.out.println("isEmptyPosition");
        Plateau instance = new Plateau(5, 5);
        instance.initialiser();
        
        instance.jouer(new Coup(1, new Position(1, 1)));
        
        assertFalse(instance.isEmptyPosition(1, 1));
        assertTrue(instance.isEmptyPosition(1, 0));
        assertTrue(instance.isEmptyPosition(0, 1));
    }

    /**
     * Test of isFull method, of class Plateau.
     */
    @Test
    public void testIsFull() throws OutOfBoundException, InvalidPlayException {
        System.out.println("isFull");
        Plateau instance = new Plateau(1, 2);
        instance.initialiser();
        
        assertFalse(instance.isFull());
        instance.jouer(new Coup(1, new Position(0, 0)));
        assertFalse(instance.isFull());
        instance.jouer(new Coup(1, new Position(0, 1)));
        assertTrue(instance.isFull());
    }

    /**
     * Test of getDernierId method, of class Plateau.
     */
    @Test
    public void testGetDernierId() throws OutOfBoundException, InvalidPlayException {
        System.out.println("getDernierId");
        Plateau instance = new Plateau(1, 2);
        instance.initialiser();
        
        instance.jouer(new Coup(1, new Position(0, 0)));
        assertEquals(1, instance.getDernierId());
        instance.jouer(new Coup(2, new Position(0, 1)));
        assertEquals(2, instance.getDernierId());
    }
    
}
