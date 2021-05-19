package partie;

import caracteristiquesPiece.*;

public class Piece {
	private Couleur couleur;
	private Forme forme;
	private Hauteur hauteur;
	private PleineOuCreuse pleineOuCreuse;
	private boolean caseVide;
	
	public Piece(Couleur c, Forme f, Hauteur h, PleineOuCreuse p) {
		couleur = c;
		forme = f;
		hauteur = h;
		pleineOuCreuse = p;
		caseVide = false;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public Forme getForme() {
		return forme;
	}

	public Hauteur getHauteur() {
		return hauteur;
	}

	public PleineOuCreuse getPleineOuCreuse() {
		return pleineOuCreuse;
	}
	
	public boolean getCaseVide() {
		return caseVide;
	}
	
	public void setCaseVide(boolean b) {
		caseVide = b;
	}

	@Override
	public boolean equals(Object obj) {
		Piece p = (Piece) obj;

		if (this == p) {
			return true;
		}

		return (this.couleur.equals(p.couleur) && this.forme.equals(p.forme) && this.hauteur.equals(p.hauteur) 
				&& this.pleineOuCreuse.equals(p.pleineOuCreuse));
	}
	
	@Override
	public String toString() {
		// Les pièces avec caseVide true sont les pièces occupant les cases censées être vides du plateau
		if(caseVide) return "vide";
		return "(" + couleur + "," + forme + "," + hauteur + "," + pleineOuCreuse + ")";
	}
}