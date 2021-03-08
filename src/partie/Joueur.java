package partie;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import caracteristiquesPiece.Couleur;
import caracteristiquesPiece.Forme;
import caracteristiquesPiece.Hauteur;
import caracteristiquesPiece.PleineOuCreuse;
import caracteristiquesPiece.Position;

public class Joueur {
	
	private String nom;
	private List<Piece> piecesJouees;
	
	public Joueur(String n) {
		nom = n;
		piecesJouees = new ArrayList<>();
	}

	/**
	 * Demande au joueur quelle piece il veut fournir à l'adversaire
	 * @param sc le scanner d'E/S
	 * @return la piece qu'il a choisit pour son adversaire
	 */
	public Piece choixPiece(Scanner sc) {
		String c="", f="", h="", p="";
		Couleur couleur = null;
		Forme forme = null;
		Hauteur hauteur = null;
		PleineOuCreuse pleineOuCreuse = null;
		
		System.out.println("Veuillez entrer les caracteristiques de la piece s'il-vous-plait.");
		
		while(!c.equals("B") && !c.equals("N")) {
			try {
				System.out.println();
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
				System.out.println();
				System.out.print("Forme (R pour ronde, C pour carrée) : ");
				f = sc.nextLine();
				switch(f) {
					case "R" : 
						forme = Forme.CARREE;
						break;
					case "C" : 
						forme = Forme.RONDE;
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
				System.out.println();
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
				System.out.println();
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
		
		return new Piece(couleur, forme, hauteur, pleineOuCreuse);
	}
	
	/**
	 * Demande au joueur après avoir recu sa piece, où il veut la deposer
	 * @param piece piece que l'adversaire lui a donne
	 * @param sc le scanner d'E/S
	 */
	public void deposerPiece(Piece piece, Scanner sc) {
		System.out.println("");
		System.out.println("A quelle position voulez-vous placer cette piece (plateau 4x4) ?");
		
		int x=0, y=0;
		
		while(x==0) {
			try {
				System.out.print("x = ");
				x = sc.nextInt();
				if(x<1 || x>4) {
					System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 4 S'IL-VOUS-PLAIT.");
					System.out.println();
				}
			}
			catch(InputMismatchException e) {
				System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 4 S'IL-VOUS-PLAIT.");
				System.out.println();
			}
			finally {
				sc.nextLine();
			}
		}
		
		while(y==0) {
			try {
				System.out.print("y = ");
				y = sc.nextInt();
				if(y<1 || y>4) {
					System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 4 S'IL-VOUS-PLAIT.");
					System.out.println();
				}
			}
			catch(InputMismatchException e) {
				System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 4 S'IL-VOUS-PLAIT.");
				System.out.println();
			}
			finally {
				sc.nextLine();
			}
		}

		Position pos = new Position(x,y);
		piece.attribuerPosition(pos);
		piecesJouees.add(piece);
	}

	public String getNom() {
		return nom;
	}

	/**
	 * 
	 * @return retourne false si le joueur n'a pas gagné, vrai sinon
	 */
	public boolean a_gg(){
		return false;
	}
}	
	
