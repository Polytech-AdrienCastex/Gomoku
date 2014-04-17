/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package players;

import gomoku.Coup;
import gomoku.Position;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Adrien
 */
public class NoeudTest {
    
    public NoeudTest() {
    }

    /**
     * Test of getMoyenne method, of class Noeud.
     */
    @Test
    public void testGetMoyenne() {
        System.out.println("getMoyenne");
        
        Noeud instance = new Noeud(new Coup(1, new Position(5,3)));
        for(int i = 0; i < 6; i++)
            instance.ajouterVictoire();
        for(int i = 0; i < 10; i++)
            instance.ajouterDefaite();
        
        double expResult = 6/15;
        double result = instance.getMoyenne();
        assertEquals(expResult, result, 0.4);
    }

    /**
     * Test of getCoup method, of class Noeud.
     */
    @Test
    public void testGetCoup() {
        System.out.println("getCoup");
        Noeud instance = new Noeud(new Coup(1, new Position(5,3)));
        Coup expResult = new Coup(1, new Position(5,3));
        Coup result = instance.getCoup();
        assertEquals(expResult.toString(), result.toString());
    }
    
    /**
     * Test of ajouterVictoire method, of class Noeud.
     */
    @Test
    public void testAjouterVictoire() {
        System.out.println("ajouterVictoire");
        Noeud instance = new Noeud(new Coup(1, new Position(5,3)));
        instance.ajouterVictoire();
        instance.ajouterVictoire();
        instance.ajouterVictoire();
        double expResult = 1.0;
        assertEquals(expResult, instance.getMoyenne(), 1.0);
    }

    /**
     * Test of ajouterDefaite method, of class Noeud.
     */
    @Test
    public void testAjouterDefaite() {
        System.out.println("ajouterDefaite");
        Noeud instance = new Noeud(new Coup(1, new Position(5,3)));
        instance.ajouterDefaite();
        instance.ajouterDefaite();
        instance.ajouterDefaite();
        double expResult = 0.0;
        assertEquals(expResult, instance.getMoyenne(), 0.0);
    }
    
}
