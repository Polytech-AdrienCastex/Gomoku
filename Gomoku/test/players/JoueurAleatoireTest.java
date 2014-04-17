/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package players;

import exceptions.InvalidPlayException;
import exceptions.OutOfBoundException;
import gomoku.Coup;
import gomoku.PlateauGomoKu;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adrien
 */
public class JoueurAleatoireTest {
    
    public JoueurAleatoireTest() {
    }
    
    @Test
    public void testInitialiser()
    {
        JoueurAleatoire ja = new JoueurAleatoire(5);
        assertTrue(ja.getId() == 5);
    }

    @Test
    public void testFullExecution() throws OutOfBoundException
    {
        JoueurAleatoire ja = new JoueurAleatoire(5);
        
        PlateauGomoKu pgk = new PlateauGomoKu();
        
        assertNotNull(pgk);
        pgk.initialiser();
        
        // Remplir le plateau
        int len = pgk.getLargeur() * pgk.getLongueur();
        
        Coup c;
        for(int i = 0; i < len; i++)
        {
            c = null;
            
            try
            {
                c = ja.genererCoup(pgk);
            }
            catch(InvalidPlayException ex)
            {
                fail("Impossible de générer un coup.");
            }
            
            assertNotNull(c);
            assertNotNull(c.pos);
            assertEquals(c.id, ja.getId());
            
            try
            {
                pgk.jouer(c);
            }
            catch(InvalidPlayException ex)
            {
                fail("Mauvais coup généré.");
            }
            catch(OutOfBoundException ex)
            {
                fail("Coup joué en dehors du plateau");
            }
        }
        
        // Verifier que generer coup indique bien qu'il ne peut pas jouer
        c = null;
        try
        {
            c = ja.genererCoup(pgk);
            fail("Il devrait y avoir un soulèvement de l'exception \"InvalidPlayException\" lors de la génération du coup alors qu'il n'y a plus de place disponible.");
        }
        catch(InvalidPlayException ex)
        { }
        assertNull(c);
    }
}
