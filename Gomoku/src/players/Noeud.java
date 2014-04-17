/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import gomoku.Coup;

/**
 *
 * @author A
 */
public class Noeud
{
    public Noeud(Coup _coup)
    {
        this.coup = _coup;
        
        this.nbVictoire = 0;
        this.nbSimulation = 0;
    }
    
    private final Coup coup;
    
    private int nbVictoire;
    private int nbSimulation;
    
    private double moy = -1;
    public double getMoyenne()
    {
        if(moy == -1)
            moy = (double)nbVictoire/nbSimulation;
        
        return moy;
    }
    
    public Coup getCoup()
    {
        return this.coup;
    }
    
    public void ajouterVictoire()
    {
        nbVictoire++;
        nbSimulation++;
    }
    
    public void ajouterDefaite()
    {
        nbSimulation++;
    }
}
