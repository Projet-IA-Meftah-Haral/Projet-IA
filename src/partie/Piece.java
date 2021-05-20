package partie;

import caracteristiquesPiece.*;

public class Piece {
	private Couleur couleur;
	private Forme forme;
	private Hauteur hauteur;
	private PleineOuCreuse pleineOuCreuse;
	
	public Piece(Couleur c, Forme f, Hauteur h, PleineOuCreuse p) {
		couleur = c;
		forme = f;
		hauteur = h;
		pleineOuCreuse = p;
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
		return "(" + couleur + "," + forme + "," + hauteur + "," + pleineOuCreuse + ")";
	}
}