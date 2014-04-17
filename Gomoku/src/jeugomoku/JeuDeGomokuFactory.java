/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeugomoku;

import exceptions.OutOfBoundException;
import gomoku.Coup;
import gomoku.PlateauGomoKu;
import java.util.ArrayList;
import players.JoueurAleatoire;
import players.JoueurHumain;
import players.JoueurMonteCarlo;

/**
 *
 * @author p1002239
 */
public class JeuDeGomokuFactory implements JeuDePlateauFactory
{
    protected JeuDePlateau CreerPartie(ArrayList<Coup> situation) throws Exception
    {
        JeuDePlateau jdp = new JeuDeGomoku();
        PlateauGomoKu pgk = new PlateauGomoKu(9, 9);
        if(situation == null || situation.isEmpty())
            pgk.initialiser();
        else
            pgk.initialiser(situation);

        jdp.setPlateau(pgk);

        return jdp;
    }
    
    @Override
    public JeuDePlateau CreerPartieHumainVSHumain(ArrayList<Coup> situation) throws Exception
    {
        JeuDePlateau jdp = CreerPartie(situation);

        jdp.setJoueur(1, new JoueurHumain(1));
        jdp.setJoueur(2, new JoueurHumain(2));

        return jdp;
    }
    
    @Override
    public JeuDePlateau CreerPartieHumainVSAleatoire(ArrayList<Coup> situation) throws Exception
    {
        JeuDePlateau jdp = CreerPartie(situation);

        jdp.setJoueur(1, new JoueurHumain(1));
        jdp.setJoueur(2, new JoueurAleatoire(2));

        return jdp;
    }
    
    @Override
    public JeuDePlateau CreerPartieAleatoireVSAleatoire(ArrayList<Coup> situation) throws Exception
    {
        JeuDePlateau jdp = CreerPartie(situation);

        jdp.setJoueur(1, new JoueurAleatoire(1));
        jdp.setJoueur(2, new JoueurAleatoire(2));

        return jdp;
    }
    
    @Override
    public JeuDePlateau CreerPartieHumainVSMonteCarlo(ArrayList<Coup> situation) throws Exception
    {
        JeuDePlateau jdp = CreerPartie(situation);

        jdp.setJoueur(1, new JoueurHumain(1));
        jdp.setJoueur(2, new JoueurMonteCarlo(2, 1000, this));

        return jdp;
    }
}
