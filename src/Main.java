import java.util.NoSuchElementException;
import java.util.Scanner;

import algorithmes.JeuRepresentation;
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
            
            //JeuRepresentation.setHumain(new Joueur(sc.nextLine())); | Pour l'instant ne sert pas (car pas d'ia dans le projet)
            //Donc on garde pour l'instant le jeu avec les 2 joueur humain

            double premier_tour = Math.random(); //Au 1er tour, le joueur qui commence est choisi aléatoirement
            
            Plateau plateau = new Plateau();

            if (premier_tour >= 0.5) { //Si le 1er coup est sup ou égal à 0.5, le 1er tour est donné à l'IA, sinon c'est l'humain qui joue
                plateau.setTourHumain(false);;
            }

            while (!JeuRepresentation.test_terminal(plateau)) {
            	System.out.println();
                plateau.affichagePlateau();
                System.out.println();

                if (plateau.getTourHumain()) {
                    /**
                     * Ici c'est le tour de l'humain
                     */
                    joueur2.deposerPiece(joueur1.choixPiece(plateau, sc), plateau, sc);
                    
                    if(plateau.aGagne()) {
                    	System.out.println();
                    	plateau.affichagePlateau();
                    	System.out.println();
                    	System.out.println(plateau.getGagnant().getNom() + " a gagné !");
                    }
                    
                    if(plateau.plateauRempli()) {
                    	System.out.println();
                    	System.out.println("Le plateau est rempli, pas de vainqueur !");
                    }

                    plateau.setTourHumain(false);
                } else {
                    /**
                     * Ici c'est le tour de l'IA
                     */
                    joueur1.deposerPiece(joueur2.choixPiece(plateau, sc), plateau, sc);
                    
                    if(plateau.aGagne()) {
                    	System.out.println();
                    	plateau.affichagePlateau();
                    	System.out.println();
                    	System.out.println(plateau.getGagnant().getNom() + " a gagné !");
                    }
                    
                    if(plateau.plateauRempli()) {
                    	System.out.println();
                    	System.out.println("Le plateau est rempli, pas de vainqueur !");
                    }

                    plateau.setTourHumain(true);
                }
            }
        } catch (NoSuchElementException e) {
            System.err.println("Problème d'E/S, l'élément n'existe pas");
        } catch (IllegalStateException e) {
            System.err.println("Le scanner est déjà fermé");
        }
        
    }
}