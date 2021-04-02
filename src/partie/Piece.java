package partie;

import caracteristiquesPiece.*;

public class Piece {
	private Couleur couleur;
	private Forme forme;
	private Hauteur hauteur;
	private PleineOuCreuse pleineOuCreuse;
	private Position pos;
	
	public Piece(Couleur c, Forme f, Hauteur h, PleineOuCreuse p) {
		couleur = c;
		forme = f;
		hauteur = h;
		pleineOuCreuse = p;
		pos = new Position(0,0);
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
	
	public Position getPos() {
		return pos;
	}
	
	public void attribuerPosition (Position p) {
		pos = p;
	}
	
	/**
	 * Vérifie si deux pièces ont une caractéristique en commun
	 * @param p : Pièce comparée à la pièce courante 
	 * @return true si les deux pièces ont une caractéristique commune, false sinon
	 */
	public boolean caracteristiqueEnCommun(Piece p) {
		// Les pièces avec la position (0,0) désignent des cases vides du plateau, elles ne peuvent donc pas avoir 
		// de caractéristique commune avec une autre pièce
		if(pos.equals(new Position(0,0)) || p.getPos().equals(new Position(0,0))) return false;
		return (couleur==p.couleur || forme==p.forme || hauteur==p.hauteur || pleineOuCreuse==p.pleineOuCreuse);
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
		if(pos.equals(new Position(0,0))) return "vide";
		return "(" + couleur + "," + forme + "," + hauteur + "," + pleineOuCreuse + ")";
	}
}