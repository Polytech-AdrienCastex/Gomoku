/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import exceptions.EmptyHistoryException;
import exceptions.InvalidPlayException;
import exceptions.OutOfBoundException;
import gomoku.Coup;
import gomoku.Plateau;
import gomoku.Position;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jeugomoku.JeuDePlateau;
import jeugomoku.JeuDePlateauFactory;

/**
 *
 * @author A
 */
public class JoueurMonteCarlo extends Joueur
{
    public JoueurMonteCarlo(int _id, int _nbSimulation, JeuDePlateauFactory _factory)
    {
        super(_id);
        
        this.factory = _factory;
        
        this.nbSimulation = _nbSimulation;
    }
    
    private final int nbSimulation;
    private final JeuDePlateauFactory factory;
    
    
    /****************
    
    Thread: Sequen
    12756 : 27747 -> 2,175
    6891  : 16931 -> 2,457
    
    ****************/
    @Override
    public Coup genererCoup(Plateau etatJeu) throws InvalidPlayException
    {
        return genererCoup_Thr(etatJeu);
        //return genererCoup_Seq(etatJeu);
    }
    
    private Coup genererCoup_Thr(Plateau etatJeu) throws InvalidPlayException
    {
        Noeud meilleurCoup = null;
        ArrayList<Position> positionsPossibles = etatJeu.etatId(0);
        
        if(positionsPossibles.isEmpty())
            throw new InvalidPlayException("Le plateau est plein.");
        
        ArrayList<runtime> runtimes = new ArrayList<>();
        
        for(Position p : positionsPossibles)
        {
            Coup cCourant = new Coup(id, p);

            runtime rt = new runtime(etatJeu, cCourant);
            runtimes.add(rt);
            rt.start();
        }
        
        for(runtime rt : runtimes)
        {
            try
            {
                rt.join();
            }
            catch (InterruptedException ex)
            { }
            
            if(meilleurCoup == null || meilleurCoup.getMoyenne() < rt.getResult().getMoyenne())
                meilleurCoup = rt.getResult();
        }
        
        return meilleurCoup.getCoup();
    }
    
    private Coup genererCoup_Seq(Plateau etatJeu) throws InvalidPlayException
    {
        Noeud meilleurCoup = null;
        ArrayList<Position> positionsPossibles = etatJeu.etatId(0);
        
        if(positionsPossibles.isEmpty())
            throw new InvalidPlayException("Le plateau est plein.");
        
        try
        {
            for(Position p : positionsPossibles)
            {
                Coup cCourant = new Coup(id, p);
                
                Noeud nCourant = new Noeud(cCourant);
                etatJeu.jouer(cCourant);
                ArrayList<Coup> sit = etatJeu.getSituation();

                for(int sim = 0; sim < nbSimulation; sim++)
                { // Simulations
                    JeuDePlateau jdp = factory.CreerPartieAleatoireVSAleatoire((ArrayList<Coup>)sit.clone());
                    Joueur result = jdp.jouerPartie();

                    if(result == null || result.id != this.id)
                        nCourant.ajouterDefaite(); // L'opposant a gagné
                    else
                        nCourant.ajouterVictoire(); // L'IA a gagné
                }

                if(meilleurCoup == null || meilleurCoup.getMoyenne() < nCourant.getMoyenne())
                    meilleurCoup = nCourant;
                etatJeu.annuler();
            }
        }
        catch (OutOfBoundException | EmptyHistoryException ex)
        {
            Logger.getLogger(JoueurMonteCarlo.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex)
        {
            Logger.getLogger(JoueurMonteCarlo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return meilleurCoup.getCoup();
    }
    
    private class runtime extends Thread
    {
        public runtime(Plateau etatJeu, Coup cCourant)
        {
            this.etatJeu = etatJeu.Clone();
            this.cCourant = cCourant;
        }
        
        private Plateau etatJeu;
        private Coup cCourant;
        
        private Noeud nCourant;
        
        public Noeud getResult()
        {
            return nCourant;
        }
        
        public void run()
        {
            try
            {
                this.nCourant = new Noeud(cCourant);
                etatJeu.jouer(cCourant);
                ArrayList<Coup> situation = etatJeu.getSituation();

                for(int sim = 0; sim < nbSimulation; sim++)
                { // Simulations
                    JeuDePlateau jdp = factory.CreerPartieAleatoireVSAleatoire(situation);
                    Joueur result = jdp.jouerPartie();

                    if(result == null || result.id != id)
                        nCourant.ajouterDefaite(); // L'opposant a gagné ou match nul
                    else
                        nCourant.ajouterVictoire(); // L'IA a gagné
                }
            }
            catch (Exception ex)
            {
                nCourant.ajouterDefaite();
            }
        }
    }
    
    @Override
    public String toString()
    {
        return "IA Monte Carlo n°" + getId();
    }
}
