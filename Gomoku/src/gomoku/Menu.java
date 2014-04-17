/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku;

import exceptions.InvalidPlayException;
import exceptions.OutOfBoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import jeugomoku.JeuDeGomokuFactory;
import jeugomoku.JeuDePlateau;
import players.Joueur;

/**
 *
 * @author p1002239
 */
public class Menu implements Runnable
{
    private class Menus
    {
        public static final int HvsH = 1;
        public static final int HvsMC = 2;
        public static final int HvsAl = 3;
        public static final int AlvsAl = 4;
        public static final int Test = 5;
        public static final int Fermer = 6;
        
        public static final int MAX = 6;
    }
    
    public Menu()
    {
        String big = "Jouer une partie : ";
        
        menus = new String[]
        {
            big + "Humain vs Humain",
            big + "Humain vs IA Monte Carlo",
            big + "Humain vs IA Aléatoire",
            big + "IA Aléatoire vs IA Aléatoire",
            
            "Procedure de test",
            
            "Fermer"
        };
    }
    
    private final String[] menus;

    @Override
    public void run()
    {
        Scanner scan = new Scanner(System.in);
        
        int selectedMenu = 0;
        boolean loop;
        
        do
        {
            PrintTitle("GOMOKU");
            for(int i = 0; i < menus.length; i++)
                System.out.println((i + 1) + "/ " + menus[i]);

            do
            {
                loop = true;
                try
                {
                    System.out.print("Entrez le numéro du menu : ");
                    selectedMenu = scan.nextInt();
                    
                    if(selectedMenu > 0 && selectedMenu <= Menus.MAX)
                        loop = false;
                    else
                        System.out.println("Il faut entrer un numéro du menu!");
                }
                catch(InputMismatchException ex)
                {
                    System.out.println("Il faut entrer le numéro du menu!");
                }
            } while(loop);

            ExecuteMenu(selectedMenu);
        } while(selectedMenu != Menus.Fermer);
        
        System.out.println("Au revoir!");
    }
    
    private void PrintTitle(String value)
    {
        System.out.println("    *********** " + value + " ***********");
    }
    
    private void ExecuteMenu(int selectedMenu)
    {
        try
        {
            switch(selectedMenu)
            {
                case Menus.AlvsAl:
                    PrintTitle("PARTIE IA VS IA");
                    AfficherJoueurVainqueur(
                            new JeuDeGomokuFactory().
                                    CreerPartieAleatoireVSAleatoire(null).
                                    jouerPartie());
                    break;
                    
                case Menus.HvsMC:
                    PrintTitle("PARTIE Humain VS IA Monte Carlo");
                    AfficherJoueurVainqueur(
                            new JeuDeGomokuFactory().
                                    CreerPartieHumainVSMonteCarlo(null).
                                    jouerPartie());
                    break;
                    
                case Menus.HvsAl:
                    PrintTitle("PARTIE Humain VS IA");
                    AfficherJoueurVainqueur(
                            new JeuDeGomokuFactory().
                                    CreerPartieHumainVSAleatoire(null).
                                    jouerPartie());
                    break;
                    
                case Menus.HvsH:
                    PrintTitle("PARTIE Humain VS Humain");
                    AfficherJoueurVainqueur(
                            new JeuDeGomokuFactory().
                                    CreerPartieHumainVSHumain(null).
                                    jouerPartie());
                    break;
                    
                case Menus.Test:
                    PrintTitle("TEST");
                    Test();
                    break;
            }
        }
        catch(Exception ex)
        {
            System.out.println("/!\\");
            System.out.println("Une erreur s'est produite pendant l'execution du programme...");
        }
    }
    
    private void AfficherJoueurVainqueur(Joueur joueurVainqueur)
    {
        System.out.print("Fin du match : ");
        
        if(joueurVainqueur == null)
            System.out.println("Match nul!");
        else
            System.out.println("Joueur n°" + joueurVainqueur.getId() + " gagne!");
    }
    
    
    private void Test() throws Exception
    {
        // (1)
        PrintTitle("Creation standard avec deux coups");
        Plateau p = new Plateau(9, 9);
        
        // (2)
        p.jouer(new Coup(1, new Position(7, 8)));
        p.jouer(new Coup(2, new Position(0, 0)));
        System.out.println(p);
        
        // (3)
        PrintTitle("Jouer une partie Humain vs IA");
        JeuDeGomokuFactory jgf = new JeuDeGomokuFactory();
        JeuDePlateau jdp = jgf.CreerPartieHumainVSAleatoire(null);
        Joueur joueurVainqueur = jdp.jouerPartie();
        AfficherJoueurVainqueur(joueurVainqueur);
        
        // (4)
        PrintTitle("Creation de partie avec la configuration courante");
        JeuDeGomokuFactory jgf2 = new JeuDeGomokuFactory();
        JeuDePlateau jdp2 = jgf2.CreerPartieHumainVSAleatoire(jdp.getSituation());
        System.out.println(jdp2);
    }
}