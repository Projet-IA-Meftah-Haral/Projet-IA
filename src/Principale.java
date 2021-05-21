import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ia.IA;
import partie.Joueur;
import partie.Partie;

public class Principale {
    public static void main(String[] args) throws Exception { 

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Bienvenue sur Quarto !");
        System.out.println();
        
        int choix = 0;
        
        System.out.println("Choix de la partie :");
        while(choix<1 || choix>2) {
            try {
                System.out.println("1 : Jouer à deux");
                System.out.println("2 : Jouer contre l'ordinateur");
                System.out.print("Votre choix : ");
                choix = sc.nextInt();
                sc.nextLine();
                if(choix<1 || choix>2) {
                    System.out.println("VEUILLEZ ENTRER UN CHIFFRE ENTRE 1 ET 2 S'IL-VOUS-PLAIT.");
                }
            }	
            catch(InputMismatchException e) {
                System.out.println("VEUILLEZ ENTRER UN CHIFFRE ENTRE 1 ET 2 S'IL-VOUS-PLAIT.");
            }
        }
        
        if(choix == 1) {
            try {
                System.out.println();
                System.out.print("Joueur 1, quel est votre nom ? ");
                String joueur1 = sc.nextLine();
                
                System.out.print("Joueur 2, quel est votre nom ? "); 
                String joueur2 = sc.nextLine();
                
                double premier_tour = Math.random(); //Au 1er tour, le joueur qui commence est choisi aléatoirement
                
                boolean tourJ1;
                if(premier_tour <= 0.5) tourJ1 = false;
                else tourJ1 = true;
                
                Partie partie = new Partie(joueur1, joueur2, tourJ1);
                
                while(!partie.testTerminal()) {
                    System.out.println();
                    partie.afficherPlateau();
                    System.out.println();
                    
                    if(partie.getTourJ1()) {
                        Joueur.deposerPiece(Joueur.choixPiece(partie, partie.getJoueur2(), sc), partie, partie.getJoueur1(), sc);
                    }
                    else {
                        Joueur.deposerPiece(Joueur.choixPiece(partie, partie.getJoueur1(), sc), partie, partie.getJoueur2(), sc);
                    }
                    
                    if(partie.aGagne() && partie.getTourJ1()) {
                        System.out.println();
                        partie.afficherPlateau();
                        System.out.println();
                        System.out.println(partie.getJoueur1() + " a gagné !");
                    }
                    
                    else if(partie.aGagne() && !partie.getTourJ1()) {
                        System.out.println();
                        partie.afficherPlateau();
                        System.out.println();
                        System.out.println(partie.getJoueur2() + " a gagné !");
                    }
                    
                    else if(partie.plateauRempli()) {
                        System.out.println();
                        partie.afficherPlateau();
                        System.out.println();
                        System.out.println("Pas de vainqueur ! Le plateau est rempli.");
                    }
                    
                    partie.setTourJ1();
                }
            } 
            catch (NoSuchElementException e) {
                System.err.println("Problème d'E/S, l'élément n'existe pas");
            } 
            catch (IllegalStateException e) {
                System.err.println("Le scanner est déjà fermé");
            }
        } 
        else {
            try {
                System.out.println();
                System.out.print("Quel est votre nom ? ");
                String joueur = sc.nextLine();
                
                double premier_tour = Math.random(); //Au 1er tour, le joueur qui commence est choisi aléatoirement
                
                boolean tourJ1;
                if(premier_tour <= 0.5) tourJ1 = false;
                else tourJ1 = true;
                
                Partie partie = new Partie(joueur, "ia", tourJ1);
                
                int choixDifficulte = 0;
                System.out.println();
                System.out.println("Choix de la difficulté :");
                while(choixDifficulte<1 || choixDifficulte>3) {
                    try {
                        System.out.println("1 : Facile");
                        System.out.println("2 : Moyenne");
                        System.out.println("3 : Difficile");
                        System.out.print("Votre choix : ");
                        choixDifficulte = sc.nextInt();
                        sc.nextLine();
                        if(choixDifficulte<1 || choixDifficulte>3) {
                            System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 3 S'IL-VOUS-PLAIT.");
                        }
                    }	
                    catch(InputMismatchException e) {
                        System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 3 S'IL-VOUS-PLAIT.");
                    }
                }
                //Pour le choix 2 et 3 on utilise l'IA avec une profondeur et Minimax
                
                IA ia = new IA(partie, choixDifficulte);

                if (choixDifficulte == 2) {
                    ia = new IA(partie, 1);
                } else if (choixDifficulte == 3) {
                    ia = new IA(partie, 2);
                }

                while(!partie.testTerminal()) {
                    System.out.println();
                    partie.afficherPlateau();
                    System.out.println();
                    
                    if(partie.getTourJ1()) {
                        if (choixDifficulte == 1) { //Si niveau facile on applique l'algo random
                            Joueur.deposerPiece(ia.choixPieceRandom(), partie, partie.getJoueur1(), sc);
                        } else {
                            Joueur.deposerPiece(ia.choixPiece(), partie, partie.getJoueur1(), sc);
                        }
                    }
                    else {
                        if (choixDifficulte == 1) { //Si niveau facile on applique l'algo random
                            ia.deposerPieceRandom(Joueur.choixPiece(partie, partie.getJoueur1(), sc));
                        } else {
                            ia.deposerPiece(Joueur.choixPiece(partie, partie.getJoueur1(), sc));
                        }
                    }
                    partie = ia.getPartie();
                    
                    if(partie.aGagne() && partie.getTourJ1()) {
                        System.out.println();
                        partie.afficherPlateau();
                        System.out.println();
                        System.out.println(partie.getJoueur1() + " a gagné !");
                    }
                    
                    else if(partie.aGagne() && !partie.getTourJ1()) {
                        System.out.println();
                        partie.afficherPlateau();
                        System.out.println();
                        System.out.println("L'ordinateur a gagné !");
                    }
                    
                    else if(partie.plateauRempli() && !partie.aGagne()) {
                        System.out.println();
                        partie.afficherPlateau();
                        System.out.println();
                        System.out.println("Pas de vainqueur ! Le plateau est rempli.");
                    }
                    
                    partie.setTourJ1();
                }
            } 
            catch (NoSuchElementException e) {
                System.err.println("Problème d'E/S, l'élément n'existe pas");
            } 
            catch (IllegalStateException e) {
                System.err.println("Le scanner est déjà fermé");
            }   
        }

        sc.close();
    }
}