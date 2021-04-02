import java.util.NoSuchElementException;
import java.util.Scanner;

import partie.Joueur;
import partie.Plateau;

public class Main {
    public static void main(String[] args) throws Exception {
    	
    	System.out.println("Bienvenue sur Quarto !");
    	System.out.println();

        try (Scanner sc = new Scanner(System.in);) {
            System.out.print("Joueur 1, quel est votre nom ? ");
            Joueur joueur1 = new Joueur(sc.nextLine());

            System.out.print("Joueur 2, quel est votre nom ? ");
            Joueur joueur2 = new Joueur(sc.nextLine());

            double tour_joueur = Math.random(); //Au 1er tour, le joueur qui commence est choisi aléatoirement
            
            Plateau plateau = new Plateau();
            
            boolean finPartie = false;

            while (!finPartie) {
            	System.out.println();
                plateau.affichagePlateau();
                System.out.println();

                if (tour_joueur < 0.5) {
                    joueur2.deposerPiece(joueur1.choixPiece(plateau, sc), plateau, sc);
                    
                    if(plateau.aGagne()) {
                    	System.out.println();
                    	plateau.affichagePlateau();
                    	System.out.println();
                    	System.out.println(plateau.getGagnant().getNom() + " a gagné !");
                    	finPartie = true;
                    }
                    
                    if(plateau.plateauRempli()) {
                    	System.out.println();
                    	System.out.println("Le plateau est rempli, pas de vainqueur !");
                    	finPartie = true;
                    }

                    tour_joueur = 1;
                } else {
                    joueur1.deposerPiece(joueur2.choixPiece(plateau, sc), plateau, sc);
                    
                    if(plateau.aGagne()) {
                    	System.out.println();
                    	plateau.affichagePlateau();
                    	System.out.println();
                    	System.out.println(plateau.getGagnant().getNom() + " a gagné !");
                    	finPartie = true;
                    }
                    
                    if(plateau.plateauRempli()) {
                    	System.out.println();
                    	System.out.println("Le plateau est rempli, pas de vainqueur !");
                    	finPartie = true;
                    }

                    tour_joueur = 0;
                }
            }
        } catch (NoSuchElementException e) {
            System.err.println("Problème d'E/S, l'élément n'existe pas");
        } catch (IllegalStateException e) {
            System.err.println("Le scanner est déjà fermé");
        }
        
    }
}