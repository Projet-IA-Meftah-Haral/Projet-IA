import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ia.Action;
import ia.IA;
import ia.Position;
import partie.Joueur;
import partie.Partie;
import partie.Piece;
import caracteristiquesPiece.*;

public class Principale {
    public static void main(String[] args) throws Exception { 

        // Piece p = null;
        // if(p == null) System.out.println("vide");

        // Partie p = new Partie("j1", "j2", true);
        // p.remplirPlateau(new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.HAUTE, PleineOuCreuse.PLEINE), 4, 1);
        // p.afficherPlateau();
        // System.out.println(p.plateauRempli()); 

        // Partie p = new Partie("j1", "j2", true);
        // System.out.println(p);
        // p.getPlateau()[0][0] = new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE);
        // p.affichagePlateau();
        // System.out.println(p);

        // Partie etat = new Partie("humain", "ia", true);
        // Partie nouvelEtat = etat;

        // Piece[][] nouveauPlateau = etat.getPlateau();
        // nouveauPlateau[0][0] = new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE);
        // nouveauPlateau[0][0].setCaseVide();
        // nouvelEtat.setPlateau(nouveauPlateau);

        // Partie newEtat = nouvelEtat;
        
        // newEtat.affichagePlateau();

        // Action action = new Action(new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE), new Position(0,0));
        // action.getPiece().setCaseVide();
        // System.out.println(action.getPiece());

        // Partie etat = new Partie("humain", "ia", true);
        // for(Action a : etat.actionsPossibles()) System.out.println(a.getPiece() + " " + a.getI() + a.getJ());  

        // Partie etat = new Partie("humain", "ia", true);
        // for(Action a : etat.actionsPossiblesPiece(new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE))) System.out.println(a.getPiece() + " " + a.getI() + a.getJ());  
        
        // //TESTER SUCCESSEUR() ET DEFAIREACTION()
        // Partie etat = new Partie("humain", "ia", true);
        // etat.affichagePlateau();
        // System.out.println(etat.getPiecesDisponibles().size());
        // System.out.println(etat.getTourJ1());
        // Piece p = new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE);
        // etat.successeur(new Action(p, new Position(0, 0)));
        // etat.affichagePlateau();
        // System.out.println(etat.getPiecesDisponibles().size());
        // System.out.println(etat.getTourJ1());
        // etat.defaireAction(new Action(p, new Position(0, 0)));
        // etat.affichagePlateau();
        // System.out.println(etat.getPiecesDisponibles().size());
        // System.out.println(etat.getTourJ1());

        // //TESTER TROISPIECESMEMEDIAG()
        // Partie etat = new Partie("humain", "ia", true);
        // Piece p1 = new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE);
        // p1.setCaseVide(false);
        // Piece p2 = new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.PLEINE);
        // p2.setCaseVide(false);
        // Piece p3 = new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.HAUTE, PleineOuCreuse.CREUSE);
        // p3.setCaseVide(false);
        // Piece[][] nouveauPlateau = etat.getPlateau();
        // nouveauPlateau[3][0] = p1;
        // nouveauPlateau[1][2] = p2; 
        // nouveauPlateau[0][3] = p3;
        // etat.setPlateau(nouveauPlateau);
        // System.out.println(etat.troisPiecesMemeDiag(new Piece(Couleur.NOIRE, Forme.RONDE, Hauteur.BASSE, PleineOuCreuse.CREUSE)));

        // //TESTER TROISPIECESMEMELIGNE()
        // Partie etat = new Partie("humain", "ia", true);
        // Piece p1 = new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE);
        // p1.setCaseVide(false);
        // Piece p2 = new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.PLEINE);
        // p2.setCaseVide(false);
        // Piece p3 = new Piece(Couleur.NOIRE, Forme.CARREE, Hauteur.HAUTE, PleineOuCreuse.CREUSE);
        // p3.setCaseVide(false);
        // Piece[][] nouveauPlateau = etat.getPlateau();
        // nouveauPlateau[0][0] = p1;
        // nouveauPlateau[0][2] = p2; 
        // nouveauPlateau[0][3] = p3;
        // etat.setPlateau(nouveauPlateau);
        // System.out.println(etat.troisPiecesMemeLigne(new Piece(Couleur.NOIRE, Forme.RONDE, Hauteur.BASSE, PleineOuCreuse.CREUSE)));

        // //TESTER TROISPIECESMEMECOLONNE()
        // Partie etat = new Partie("humain", "ia", true);
        // Piece p1 = new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE);
        // p1.setCaseVide(false);
        // Piece p2 = new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.PLEINE);
        // p2.setCaseVide(false);
        // Piece p3 = new Piece(Couleur.NOIRE, Forme.CARREE, Hauteur.HAUTE, PleineOuCreuse.CREUSE);
        // p3.setCaseVide(false);
        // Piece[][] nouveauPlateau = etat.getPlateau();
        // nouveauPlateau[0][2] = p1;
        // nouveauPlateau[1][2] = p2; 
        // nouveauPlateau[2][2] = p3;
        // etat.setPlateau(nouveauPlateau);
        // System.out.println(etat.troisPiecesMemeColonne(new Piece(Couleur.NOIRE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE)));

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

                IA ia = new IA(partie, choixDifficulte);
                
                while(!partie.testTerminal()) {
                    System.out.println();
                    partie.afficherPlateau();
                    System.out.println();
                    
                    if(partie.getTourJ1()) {
                        Joueur.deposerPiece(ia.choixPiece(), partie, partie.getJoueur1(), sc);
                        System.out.println(partie.getPiecesDisponibles().size());

                    }
                    else {
                        ia.deposerPiece(Joueur.choixPiece(partie, partie.getJoueur1(), sc));
                        System.out.println(partie.getPiecesDisponibles().size());

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