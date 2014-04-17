/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeugomoku.torique.plus;

import gomoku.Coup;
import gomoku.PlateauGomoKu;
import gomoku.PlateauGomoKuTorique;
import java.util.ArrayList;
import jeugomoku.JeuDeGomokuFactory;
import jeugomoku.JeuDePlateau;
import players.JoueurHumain;
import players.JoueurMonteCarlo;

/**
 *
 * @author p1002239
 */
public class JeuDeGomokuPlusToriqueFactory extends JeuDeGomokuFactory
{
    @Override
    protected JeuDePlateau CreerPartie(ArrayList<Coup> situation) throws Exception
    {
        JeuDePlateau jdp = new JeuDeGomokuPlusTorique();
        PlateauGomoKuTorique pgk = new PlateauGomoKuTorique();
        if(situation == null || situation.isEmpty())
            pgk.initialiser();
        else
            pgk.initialiser(situation);

        jdp.setPlateau(pgk);

        return jdp;
    }
}
