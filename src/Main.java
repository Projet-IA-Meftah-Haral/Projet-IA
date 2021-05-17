import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ia.IA;
import partie.Joueur;
import partie.Partie;

public class Main {
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
                    partie.affichagePlateau();
                    System.out.println();
                    
                    if(partie.getTourJ1()) {
                        Joueur.deposerPiece(Joueur.choixPiece(partie, partie.getJoueur2(), sc), partie, partie.getJoueur1(), sc);
                    }
                    else {
                        Joueur.deposerPiece(Joueur.choixPiece(partie, partie.getJoueur1(), sc), partie, partie.getJoueur2(), sc);
                    }
                    
                    if(partie.aGagne() && partie.getTourJ1()) {
                        System.out.println();
                        partie.affichagePlateau();
                        System.out.println();
                        System.out.println(partie.getJoueur1() + " a gagné !");
                    }
                    
                    else if(partie.aGagne() && !partie.getTourJ1()) {
                        System.out.println();
                        partie.affichagePlateau();
                        System.out.println();
                        System.out.println(partie.getJoueur2() + " a gagné !");
                    }
                    
                    else if(partie.plateauRempli()) {
                        System.out.println();
                        partie.affichagePlateau();
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

                IA ia;
                if(choixDifficulte == 1) {
                    ia = new IA(1, partie);
                }
                
                if(choixDifficulte == 2) {
                    ia = new IA(3, partie);
                }
                
                else {
                    ia = new IA(5, partie);
                }
                
                while(!partie.testTerminal()) {
                    System.out.println();
                    partie.affichagePlateau();
                    System.out.println();
                    
                    if(partie.getTourJ1()) {
                        Joueur.deposerPiece(ia.minimaxAlphaBetaChoixPiece(), partie, partie.getJoueur1(), sc);
                    }
                    else {
                        ia.deposerPiece(Joueur.choixPiece(partie, partie.getJoueur1(), sc));
                    }
                    
                    if(partie.aGagne() && partie.getTourJ1()) {
                        System.out.println();
                        partie.affichagePlateau();
                        System.out.println();
                        System.out.println(partie.getJoueur1() + " a gagné !");
                    }
                    
                    else if(partie.aGagne() && !partie.getTourJ1()) {
                        System.out.println();
                        partie.affichagePlateau();
                        System.out.println();
                        System.out.println(partie.getJoueur2() + " a gagné !");
                    }
                    
                    else if(partie.plateauRempli()) {
                        System.out.println();
                        partie.affichagePlateau();
                        System.out.println();
                        System.out.println("Pas de vainqueur ! Le plateau est rempli.");
                    }
                    
                    System.out.println(partie.getPiecesDisponibles());
                    
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


// public class Main {
    //     public static void main(String[] args) {
        //         System.out.println("Bienvenue sur Quarto !");    
        //         System.out.println();
        
        //         int rep = 0;
        //         Scanner sc = new Scanner(System.in);
        
        //         while(rep<1 || rep>2) {
            //             try {
                //                 System.out.println("Match en entre amis ou contre l'ordinateur ? (1 : j1 VS j2), (2 : j1 VS ia)");
                //                 rep = sc.nextInt();
                //                 if(rep<1 || rep>2) {
                    //                     System.out.println("VEUILLEZ ENTRER 1 OU 2 S'IL-VOUS-PLAIT.");
                    //                 }
                    //             }	
                    //             catch(InputMismatchException e) {
                        //                 System.out.println("VEUILLEZ ENTRER 1 OU 2 S'IL-VOUS-PLAIT.");
                        //             }
                        //         }
                        
                        //         if(rep == 1) Plateau.deuxJoueurs();
                        //         else Plateau.ia();
                        //     }
                        // }