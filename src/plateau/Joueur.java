package plateau;

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
	
	public String getNom() {
		return nom;
	}
	
	public void deposerPiece() {
		Scanner s = new Scanner(System.in);
		String c="", f="", h="", p="";
		Couleur couleur = null;
		Forme forme = null;
		Hauteur hauteur = null;
		PleineOuCreuse pleineOuCreuse = null;
		
		System.out.println("Veuillez entrer les caractéristiques de la pièce s'il-vous-plaît.");
		
		while(!c.equals("B") && !c.equals("N")) {
			try {
				System.out.println();
				System.out.print("Couleur (B pour blanche, N pour noire) : ");
				c = s.nextLine();
				switch(c) {
					case "B" : 
						couleur = Couleur.BLANCHE;
						break;
					case "N" : 
						couleur = Couleur.NOIRE;
						break;
					default : System.out.println("VEUILLEZ ENTRER 'B' OU 'N' S'IL-VOUS-PLAÎT.");
				}
			}	
			catch(InputMismatchException e) {
				System.out.println("VEUILLEZ ENTRER 'B' OU 'N' S'IL-VOUS-PLAÎT.");
			}
		}
		
		while(!f.equals("R") && !f.equals("C")) {
			try {
				System.out.println();
				System.out.print("Forme (R pour ronde, C pour carée) : ");
				f = s.nextLine();
				switch(f) {
					case "R" : 
						forme = Forme.CARREE;
						break;
					case "C" : 
						forme = Forme.RONDE;
						break;
					default : System.out.println("VEUILLEZ ENTRER 'R' OU 'C' S'IL-VOUS-PLAÎT.");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("VEUILLEZ ENTRER 'R' OU 'C' S'IL-VOUS-PLAÎT.");
			}
		}
		
		while(!h.equals("H") && !h.equals("B")) {
			try {
				System.out.println();
				System.out.print("Hauteur (H pour haute, B pour basse) : ");
				h = s.nextLine();
				switch(h) {
					case "H" : 
						hauteur = Hauteur.HAUTE;
						break;
					case "B" : 
						hauteur = Hauteur.BASSE;
						break;
					default : System.out.println("VEUILLEZ ENTRER 'H' OU 'B' S'IL-VOUS-PLAÎT.");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("VEUILLEZ ENTRER 'H' OU 'B' S'IL-VOUS-PLAÎT.");
			}
		}
		
		while(!p.equals("P") && !p.equals("C")) {
			try {
				System.out.println();
				System.out.print("Pleine ou creuse (P pour pleine, C pour creuse) ? ");
				p = s.nextLine();
				switch(p) {
					case "P" : 
						pleineOuCreuse = PleineOuCreuse.PLEINE;
						break;
					case "C" : 
						pleineOuCreuse = PleineOuCreuse.CREUSE;
						break;
					default : System.out.println("VEUILLEZ ENTRER 'P' OU 'C' S'IL-VOUS-PLAÎT.");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("VEUILLEZ ENTRER 'P' OU 'C' S'IL-VOUS-PLAÎT.");
			}
		}
		
		Piece piece = new Piece(couleur, forme, hauteur, pleineOuCreuse);
		
		System.out.println("");
		System.out.println("À quelle position voulez-vous placer cette pièce (plateau 4x4) ?");
		
		int x=0, y=0;
		
		while(x==0) {
			try {
				System.out.print("x = ");
				x = s.nextInt();
				if(x<1 || x>4) {
					System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 4 S'IL-VOUS-PLAÎT.");
					System.out.println();
				}
			}
			catch(InputMismatchException e) {
				System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 4 S'IL-VOUS-PLAÎT.");
				System.out.println();
			}
			finally {
				s.nextLine();
			}
		}
		
		while(y==0) {
			try {
				System.out.print("y = ");
				y = s.nextInt();
				if(y<1 || y>4) {
					System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 4 S'IL-VOUS-PLAÎT.");
					System.out.println();
				}
			}
			catch(InputMismatchException e) {
				System.out.println("VEUILLEZ ENTRER UN CHIFFRE COMPRIS ENTRE 1 ET 4 S'IL-VOUS-PLAÎT.");
				System.out.println();
			}
			finally {
				s.nextLine();
			}
		}
		
		Position pos = new Position(x,y);
		piece.attribuerPosition(pos);
		piecesJouees.add(piece);
		s.close();
	}
}	
	
