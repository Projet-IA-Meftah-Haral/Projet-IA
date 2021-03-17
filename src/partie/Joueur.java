package partie;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import caracteristiquesPiece.*;

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
		
		System.out.println(nom.toUpperCase() + ", veuillez entrer les caracteristiques de la piece s'il-vous-plait.");
		
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
	 * Demande au joueur après avoir reçu sa piece, où il veut la deposer
	 * @param piece que l'adversaire lui a donnée
	 * @param sc le scanner d'E/S
	 */
	public void deposerPiece(Piece piece, Plateau plateau, Scanner sc) {
		System.out.println("");
		System.out.println(nom.toUpperCase() + ", à quelle position voulez-vous placer cette piece (plateau 4x4) ?");
		
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

	public boolean aGagne() {
		return ( victoireDiagonale() || vicroireLigne() || victoireColonne() );
	}
	
	/**
	 * 
	 * @return retourne false si le joueur n'a pas gagné, vrai sinon
	 */
	public boolean victoireDiagonale() {
		boolean aGagne = false;
		List<Position[]> diagonales = new ArrayList<>();
		Position[] d1 = {new Position(1,1), new Position(2,2), new Position(3,3), new Position(4,4)};
		Position[] d2 = {new Position(1,4), new Position(2,3), new Position(3,2), new Position(4,1)};
		diagonales.add(d1);
		diagonales.add(d2);
		
		List<Piece> solution = new ArrayList<>();
		for(Position[] d : diagonales) {
			boolean memePos = false;
			for(int i=0; i<d.length; i++) {
				for(Piece p : piecesJouees) {
					if(d[i].equals(p.getPos())) {
						solution.add(p);
						memePos = true;
						break;
					}
				}
				if(!memePos) break;
			}
			
			if(solution.size() == d.length) {
				aGagne = true;
				for(int i=0; i<solution.size()-1; i++) {
					for(int j=i+1; j<solution.size(); j++) {
						if(!solution.get(i).caracteristiqueEnCommun(solution.get(j))) {
							aGagne = false;
							break;
						}
					}
					if(!aGagne) break;
				}
				if(aGagne) return true;
			}
			solution.clear();
		}
		return aGagne;
	}
	
	public boolean vicroireLigne() {
		boolean aGagne = false;
		List<Piece> solution = new ArrayList<>();

		for(int i=0; i<piecesJouees.size(); i++) {
			Piece p1 = piecesJouees.get(0);
			piecesJouees.remove(0);
			solution.add(p1);
			
			for(int j=0; j<piecesJouees.size(); j++) {
				if(p1.caracteristiqueEnCommun(piecesJouees.get(j)) && 
				p1.getPos().getX() == piecesJouees.get(j).getPos().getX()) {
					solution.add(piecesJouees.get(j));
				}
				if(solution.size()==4) break;
			}
			
			if(solution.size()==4) {
				aGagne = true;
				for(int j=0; j<solution.size()-1; j++) {
					for(int k=j+1; k<solution.size(); k++) {
						if(!solution.get(j).caracteristiqueEnCommun(solution.get(k))) {
							aGagne = false;
							break;
						}
					}
					if(!aGagne) break;
				}
			}
			
			if(aGagne) return true;
			
			piecesJouees.add(p1);
			solution.clear();
		}
		return aGagne;
	}
	
	public boolean victoireColonne() {
		boolean aGagne = false;
		List<Piece> solution = new ArrayList<>();
		
		for(int i=0; i<piecesJouees.size(); i++) {
			Piece p1 = piecesJouees.get(0);
			piecesJouees.remove(0);
			solution.add(p1);
			
			for(int j=0; j<piecesJouees.size(); j++) {
				if(p1.caracteristiqueEnCommun(piecesJouees.get(j)) && 
				p1.getPos().getY() == piecesJouees.get(j).getPos().getY()) {
					solution.add(piecesJouees.get(j));
				}
				if(solution.size()==4) break;
			}
			
			if(solution.size()==4) {
				aGagne = true;
				for(int j=0; j<solution.size()-1; j++) {
					for(int k=j+1; k<solution.size(); k++) {
						if(!solution.get(j).caracteristiqueEnCommun(solution.get(k))) {
							aGagne = false;
							break;
						}
					}
					if(!aGagne) break;
				}
			}
			
			if(aGagne) return true;
			
			piecesJouees.add(p1);
			solution.clear();
		}
		
		return aGagne;
	}	
}	