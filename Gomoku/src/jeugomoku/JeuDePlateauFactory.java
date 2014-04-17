/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeugomoku;

import gomoku.Coup;
import java.util.ArrayList;

/**
 *
 * @author p1002239
 */
public interface JeuDePlateauFactory
{
    public JeuDePlateau CreerPartieHumainVSHumain(ArrayList<Coup> situation) throws Exception;
    public JeuDePlateau CreerPartieHumainVSAleatoire(ArrayList<Coup> situation) throws Exception;
    public JeuDePlateau CreerPartieAleatoireVSAleatoire(ArrayList<Coup> situation) throws Exception;
    public JeuDePlateau CreerPartieHumainVSMonteCarlo(ArrayList<Coup> situation) throws Exception;
}
