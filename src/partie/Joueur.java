package partie;

import java.util.InputMismatchException;
import java.util.Scanner;

import caracteristiquesPiece.*;

public class Joueur {
	
	private String nom;
	
	public Joueur(String n) {
		nom = n;
	}

	public String getNom() {
		return nom;
	}

	/**
	 * Demande au joueur quelle piece il veut fournir à l'adversaire
	 * @param sc le scanner d'E/S
	 * @return la piece qu'il a choisit pour son adversaire
	 */
	public Piece choixPiece(Plateau plateau, Scanner sc) {
		Couleur couleur = null;
		Forme forme = null;
		Hauteur hauteur = null;
		PleineOuCreuse pleineOuCreuse = null;
		Piece piece = null;
		boolean pieceDispo = false;
		
		System.out.println(nom.toUpperCase() + ", veuillez entrer les caracteristiques de la piece s'il-vous-plait.");
		System.out.println();
		
		while(!pieceDispo) {
			String c="", f="", h="", p="";
			
			while(!c.equals("B") && !c.equals("N")) {
				try {
					System.out.print("Couleur (B pour blanche, N pour noire) : ");
					c = sc.nextLine();
					switch(c) {
						case "B" : 
							couleur = Couleur.BLANCHE;
							break;
						case "N" : 
							couleur = Couleur.NOIRE;
							break;
						default : System.out.println("VEUILLEZ ENTRER 'B' OU 'N' S'IL-VOUS-PLAIT.");
					}
				}	
				catch(InputMismatchException e) {
					System.out.println("VEUILLEZ ENTRER 'B' OU 'N' S'IL-VOUS-PLAIT.");
				}
			}
			
			while(!f.equals("R") && !f.equals("C")) {
				try {
					System.out.print("Forme (R pour ronde, C pour carrée) : ");
					f = sc.nextLine();
					switch(f) {
						case "R" : 
							forme = Forme.RONDE;
							break;
						case "C" : 
							forme = Forme.CARREE;
							break;
						default : System.out.println("VEUILLEZ ENTRER 'R' OU 'C' S'IL-VOUS-PLAIT.");
					}
				}
				catch(InputMismatchException e) {
					System.out.println("VEUILLEZ ENTRER 'R' OU 'C' S'IL-VOUS-PLAIT.");
				}
			}
			
			while(!h.equals("H") && !h.equals("B")) {
				try {
					System.out.print("Hauteur (H pour haute, B pour basse) : ");
					h = sc.nextLine();
					switch(h) {
						case "H" : 
							hauteur = Hauteur.HAUTE;
							break;
						case "B" : 
							hauteur = Hauteur.BASSE;
							break;
						default : System.out.println("VEUILLEZ ENTRER 'H' OU 'B' S'IL-VOUS-PLAIT.");
					}
				}
				catch(InputMismatchException e) {
					System.out.println("VEUILLEZ ENTRER 'H' OU 'B' S'IL-VOUS-PLAIT.");
				}
			}
			
			while(!p.equals("P") && !p.equals("C")) {
				try {
					System.out.print("Pleine ou creuse (P pour pleine, C pour creuse) ? ");
					p = sc.nextLine();
					switch(p) {
						case "P" : 
							pleineOuCreuse = PleineOuCreuse.PLEINE;
							break;
						case "C" : 
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
			if(plateau.supprimerPiece(piece)) pieceDispo = true;
			else {
				System.out.println();
				System.out.println("CETTE PIÈCE N'EST PLUS DISPONIBLE, VEUILLEZ EN CHOISIR UNE AUTRE S'IL-VOUS-PLAÎT.");
				System.out.println();
			}
		}
		
		return piece;
	}
	
	/**
	 * Demande au joueur après avoir reçu sa piece, où il veut la deposer
	 * @param piece que l'adversaire lui a donnée
	 * @param sc le scanner d'E/S
	 */
	public void deposerPiece(Piece piece, Plateau plateau, Scanner sc) {
		System.out.println("");
		System.out.println(nom.toUpperCase() + ", à quelle position voulez-vous placer cette piece (plateau 4x4) ?");
		
		boolean caseDispo = false;
		
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

			piece.attribuerPosition(new Position(i,j));
			if(plateau.remplirPlateau(piece, this)) caseDispo = true;
			else System.out.println("CETTE CASE EST DÉJÀ PRISE, VEUILLEZ EN CHOISIR UNE AUTRE S'IL-VOUS-PLAÎT.");
		}
	}
}	