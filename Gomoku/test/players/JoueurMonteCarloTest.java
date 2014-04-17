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
import gomoku.Position;
import java.util.ArrayList;
import jeugomoku.JeuDeGomoku;
import jeugomoku.JeuDeGomokuFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adrien
 */
public class JoueurMonteCarloTest {
    
    public JoueurMonteCarloTest() {
    }

    @Test
    public void testInitialisation()
    {
        JoueurMonteCarlo jmc = new JoueurMonteCarlo(2, 1000, new JeuDeGomokuFactory());
        assertTrue(jmc.getId() == 2);
        
        PlateauGomoKu pgk = null;
        try
        {
            pgk = new PlateauGomoKu();
        }
        catch(OutOfBoundException ex)
        {
            fail("new PlateauGomoKu impossible");
        }
        
        assertNotNull(pgk);
        pgk.initialiser();
        
        Coup c = null;
        try
        {
            long startTime = System.nanoTime();
            c = jmc.genererCoup(pgk);
            long savedTime = (System.nanoTime() - startTime) / 1000000; // en ms
            System.out.println("1er avec plateau vide         : " + savedTime + "ms");
        }
        catch(InvalidPlayException ex)
        {
            fail("Impossible de générer un coup.");
        }
        assertNotNull(c);
        assertNotNull(c.pos);
        assertEquals(c.id, jmc.getId());
    }
    
    @Test
    public void testVictoirePossible() throws OutOfBoundException
    {
        JoueurMonteCarlo jmc = new JoueurMonteCarlo(2, 1000, new JeuDeGomokuFactory());
        PlateauGomoKu pgk = new PlateauGomoKu();
        
        ArrayList<Coup> coups = new ArrayList<>();
        coups.add(new Coup(2, new Position(4, 3)));
        coups.add(new Coup(2, new Position(5, 3)));
        coups.add(new Coup(2, new Position(6, 3)));
        coups.add(new Coup(2, new Position(7, 3)));
        coups.add(new Coup(1, new Position(2, 0)));
        coups.add(new Coup(1, new Position(2, 1)));
        coups.add(new Coup(1, new Position(5, 2)));
        coups.add(new Coup(1, new Position(2, 5)));
        pgk.initialiser(coups);
        
        try
        {
            long startTime = System.nanoTime();
            Coup c = jmc.genererCoup(pgk);
            long savedTime = (System.nanoTime() - startTime) / 1000000; // en ms
            System.out.println("2eme avec 8 pions deja placés : " + savedTime + "ms");
            
            assertNotNull(c);
            assertNotNull(c.pos);
            assertEquals(c.id, jmc.getId());
            
            pgk.jouer(c);
            
            JeuDeGomoku jdg = new JeuDeGomoku();
            jdg.setJoueur(2, jmc);
            jdg.setPlateau(pgk);
            
            assertTrue(jdg.partieTerminee());
        }
        catch(InvalidPlayException | OutOfBoundException ex)
        {
            fail("Impossible de générer un coup valide.");
        }
    }
    
    @Test
    public void testTerrainPlein() throws OutOfBoundException
    {
        JoueurMonteCarlo jmc = new JoueurMonteCarlo(2, 1000, new JeuDeGomokuFactory());
        PlateauGomoKu pgk = new PlateauGomoKu();
        
        ArrayList<Coup> coups_plein = new ArrayList<>();
        for(int x = 0; x < 9; x++)
            for(int y = 0; y < 9; y++)
                coups_plein.add(new Coup(1, new Position(x, y)));
        pgk.initialiser(coups_plein);
        
        Coup c = null;
        try
        {
            c = jmc.genererCoup(pgk);
            fail("Il devrait y avoir un soulèvement de l'exception \"InvalidPlayException\" lors de la génération du coup alors qu'il n'y a plus de place disponible.");
        }
        catch(InvalidPlayException ex)
        { }
        assertNull(c);
    }
}
