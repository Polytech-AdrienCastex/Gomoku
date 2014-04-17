/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeugomoku.torique;

import gomoku.Coup;
import gomoku.PlateauGomoKuTorique;
import java.util.ArrayList;
import jeugomoku.JeuDeGomokuFactory;
import jeugomoku.JeuDePlateau;

/**
 *
 * @author p1002239
 */
public class JeuDeGomokuToriqueFactory extends JeuDeGomokuFactory
{
    @Override
    protected JeuDePlateau CreerPartie(ArrayList<Coup> situation) throws Exception
    {
        JeuDePlateau jdp = new JeuDeGomokuTorique();
        PlateauGomoKuTorique pgk = new PlateauGomoKuTorique();
        if(situation == null || situation.isEmpty())
            pgk.initialiser();
        else
            pgk.initialiser(situation);

        jdp.setPlateau(pgk);

        return jdp;
    }
}
