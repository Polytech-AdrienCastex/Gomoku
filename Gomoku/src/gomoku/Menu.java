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
import jeugomoku.JeuDeGomokuPlusFactory;
import jeugomoku.JeuDePlateau;
import jeugomoku.JeuDePlateauFactory;
import players.Joueur;

/**
 *
 * @author p1002239
 */
public class Menu implements Runnable
{
    private class Menus
    {
        public static final int ChangerMode = 1;
        public static final int HvsH = 2;
        public static final int HvsMC = 3;
        public static final int HvsAl = 4;
        public static final int AlvsAl = 5;
        public static final int Test = 6;
        public static final int Fermer = 7;
        
        public static final int MAX = 7;
    }
    
    private class Modes
    {
        public static final int GomokuStandard = 1;
        public static final int GomokuPlus = 2;
        
        public static final int MAX = 2;
    }
    
    private int mode;
    
    public Menu()
    {
        String big = "Jouer une partie : ";
        
        menus = new String[]
        {
            "Changer de mode de jeu [%MODE%]",
            
            big + "Humain vs Humain",
            big + "Humain vs IA Monte Carlo",
            big + "Humain vs IA Aléatoire",
            big + "IA Aléatoire vs IA Aléatoire",
            
            "Procedure de test",
            
            "Fermer"
        };
        
        modes = new String[]
        {
            "Gomoku Standard",
            "Gomoku +"
        };
        
        mode = 1;
        factory = new JeuDeGomokuFactory();
    }
    
    private final String[] menus;
    private final String[] modes;
    private JeuDePlateauFactory factory;

    @Override
    public void run()
    {
        int selectedMenu;
        boolean loop;
        
        do
        {
            PrintTitle("GOMOKU");
            afficherMenus(menus);
            
            selectedMenu = getValue(Menus.MAX);

            ExecuteMenu(selectedMenu);
        } while(selectedMenu != Menus.Fermer);
        
        System.out.println("Au revoir!");
    }
    
    private void PrintTitle(String value)
    {
        System.out.println("    *********** " + value + " ***********");
    }
    
    private void afficherMenus(String[] menus)
    {
        for(int i = 0; i < menus.length; i++)
            System.out.println((i + 1) + "/ " + menus[i].
                    replace("%MODE%", modes[mode - 1]));
    }
    private int getValue(int max)
    {
        Scanner scan = new Scanner(System.in);
        
        boolean loop;
        int selectedValue = 1;
        
        do
        {
            loop = true;
            try
            {
                System.out.print("Entrez le numéro : ");
                selectedValue = scan.nextInt();

                if(selectedValue > 0 && selectedValue <= Menus.MAX)
                    loop = false;
                else
                    throw new InputMismatchException();
            }
            catch(InputMismatchException ex)
            {
                System.out.println("Il faut entrer un numéro compris entre 1 et " + max + "!");
            }
        } while(loop);
        
        return selectedValue;
    }
    
    private void ExecuteMenu(int selectedMenu)
    {
        try
        {
            switch(selectedMenu)
            {
                case Menus.ChangerMode:
                    PrintTitle("CHANGER DE MODE");
                    System.out.println("Mode courrant : " + modes[mode - 1]);
                    afficherMenus(modes);
                    mode = getValue(Modes.MAX);
                    switch(mode)
                    {
                        case Modes.GomokuStandard:
                            factory = new JeuDeGomokuFactory();
                            break;
                            
                        case Modes.GomokuPlus:
                            factory = new JeuDeGomokuPlusFactory();
                            break;
                    }
                    break;
                
                case Menus.AlvsAl:
                    PrintTitle("PARTIE IA VS IA");
                    AfficherJoueurVainqueur(
                            factory.
                                    CreerPartieAleatoireVSAleatoire(null).
                                    jouerPartie());
                    break;
                    
                case Menus.HvsMC:
                    PrintTitle("PARTIE Humain VS IA Monte Carlo");
                    AfficherJoueurVainqueur(
                            factory.
                                    CreerPartieHumainVSMonteCarlo(null).
                                    jouerPartie());
                    break;
                    
                case Menus.HvsAl:
                    PrintTitle("PARTIE Humain VS IA");
                    AfficherJoueurVainqueur(
                            factory.
                                    CreerPartieHumainVSAleatoire(null).
                                    jouerPartie());
                    break;
                    
                case Menus.HvsH:
                    PrintTitle("PARTIE Humain VS Humain");
                    AfficherJoueurVainqueur(
                            factory.
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
