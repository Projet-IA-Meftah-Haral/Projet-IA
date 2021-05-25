package partie;

import java.util.InputMismatchException;
import java.util.Scanner;

import caracteristiquesPiece.*;

public class Joueur {
	/**
	 * Demande au joueur de choisir une pièce pour son adversaire
	 * @param partie la partie courante
	 * @param nomJoueur le nom du joueur qui doit choisir la piece
	 * @param sc le scanner d'E/S
	 * @return la piece que le joueur a choisi pour son adversaire
	 */
	public static Piece choixPiece(Partie partie, String nomJoueur, Scanner sc) {
		Couleur couleur = null;
		Forme forme = null;
		Hauteur hauteur = null;
		PleineOuCreuse pleineOuCreuse = null;
		Piece piece = null;

		boolean pieceDispo = false;
		
		System.out.println(nomJoueur.toUpperCase() + ", veuillez entrer les caracteristiques de la piece s'il-vous-plait.");
		System.out.println();
		
		// Tant que la pièce entrée par le joueur n'est pas disponible, on lui redemande
		while(!pieceDispo) {
			String c="", f="", h="", p="";
			
			while(!c.equals("B") && !c.equals("N") && !c.equals("b") && !c.equals("n")) {
				try {
					System.out.print("Couleur (B pour blanche, N pour noire) : ");
					c = sc.nextLine();
					switch(c) {
						case "B", "b" : 
							couleur = Couleur.BLANCHE;
							break;
						case "N", "n" : 
							couleur = Couleur.NOIRE;
							break;
						default : System.out.println("VEUILLEZ ENTRER 'B' OU 'N' S'IL-VOUS-PLAIT.");
					}
				}	
				catch(InputMismatchException e) {
					System.out.println("VEUILLEZ ENTRER 'B' OU 'N' S'IL-VOUS-PLAIT.");
				}
			}
			
			while(!f.equals("R") && !f.equals("C") && !f.equals("r") && !f.equals("c")) {
				try {
					System.out.print("Forme (R pour ronde, C pour carrée) : ");
					f = sc.nextLine();
					switch(f) {
						case "R", "r" : 
							forme = Forme.RONDE;
							break;
						case "C", "c" : 
							forme = Forme.CARREE;
							break;
						default : System.out.println("VEUILLEZ ENTRER 'R' OU 'C' S'IL-VOUS-PLAIT.");
					}
				}
				catch(InputMismatchException e) {
					System.out.println("VEUILLEZ ENTRER 'R' OU 'C' S'IL-VOUS-PLAIT.");
				}
			}
			
			while(!h.equals("H") && !h.equals("B") && !h.equals("h") && !h.equals("b")) {
				try {
					System.out.print("Hauteur (H pour haute, B pour basse) : ");
					h = sc.nextLine();
					switch(h) {
						case "H", "h" : 
							hauteur = Hauteur.HAUTE;
							break;
						case "B", "b" : 
							hauteur = Hauteur.BASSE;
							break;
						default : System.out.println("VEUILLEZ ENTRER 'H' OU 'B' S'IL-VOUS-PLAIT.");
					}
				}
				catch(InputMismatchException e) {
					System.out.println("VEUILLEZ ENTRER 'H' OU 'B' S'IL-VOUS-PLAIT.");
				}
			}
			
			while(!p.equals("P") && !p.equals("C") && !p.equals("p") && !p.equals("c")) {
				try {
					System.out.print("Pleine ou creuse (P pour pleine, C pour creuse) ? ");
					p = sc.nextLine();
					switch(p) {
						case "P", "p" : 
							pleineOuCreuse = PleineOuCreuse.PLEINE;
							break;
						case "C", "c" : 
							pleineOuCreuse = PleineOuCreuse.CREUSE;
							break;
						default : System.out.println("VEUILLEZ ENTRER 'P' OU 'C' S'IL-VOUS-PLAIT.");
					}
				}
				catch(InputMismatchException e) {
					System.out.println("VEUILLEZ ENTRER 'P' OU 'C' S'IL-VOUS-PLAIT.");
				}
			}
			
			piece = new Piece(couleur, forme, hauteur, pleineOuCreuse);
			if(partie.supprimerPiece(piece)) pieceDispo = true;
			else {
				System.out.println();
				System.out.println("CETTE PIÈCE N'EST PLUS DISPONIBLE.");
				System.out.println();
			}	
		}

		return piece;
	}
	
	/**
	 * Demande au joueur où il veut déposer la pièce choisie par l'adversaire
	 * @param piece que l'adversaire lui a donnée
	 * @param partie la partie courante
	 * @param nomJoueur le nom du joueur qui doit déposer la pièce
	 * @param sc le scanner d'E/S
	 */
	public static void deposerPiece(Piece piece, Partie partie, String nomJoueur, Scanner sc) {
		System.out.println("");
		System.out.println(nomJoueur.toUpperCase() + ", à quelle position voulez-vous placer cette piece (plateau 4x4) ?");
		
		boolean caseDispo = false;
		
		// Tant que la position sélectionnée par le joueur n'est pas disponible, on lui redmande
		while(!caseDispo) {
			int i=0, j=0;
			
			while(i<1 || i>4) {
				try {
					while(i<1 || i>4) {
						System.out.print("i = ");
						i = sc.nextInt();
						if(i<1 || i>4) {
							System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 4 S'IL-VOUS-PLAIT.");
						}
					}
				}
				catch(InputMismatchException e) {
					System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 4 S'IL-VOUS-PLAIT.");
				}
				finally {
					sc.nextLine();
				}
			}
			
			while(j<1 || j>4) {
				try {
					while(j<1 || j>4) {
						System.out.print("j = ");
						j = sc.nextInt();
						if(j<1 || j>4) {
							System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 4 S'IL-VOUS-PLAIT.");
						}
					}	
				}
				catch(InputMismatchException e) {
					System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 4 S'IL-VOUS-PLAIT.");
				}
				finally {
					sc.nextLine();
				}
			}

			if(partie.remplirPlateau(piece, i, j)) caseDispo = true;
			else System.out.println("CETTE CASE EST DÉJÀ PRISE.");
		}
	}
}	