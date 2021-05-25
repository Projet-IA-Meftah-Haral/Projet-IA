package partie;

import caracteristiquesPiece.*;

/**
 * Permet d'appliquer des méthodes à un objet Piece
 */
public class Piece {
	private Couleur couleur;
	private Forme forme;
	private Hauteur hauteur;
	private PleineOuCreuse pleineOuCreuse;
	
	/**
	 * Construit un objet Piece
	 * @param c la couleur de la pièce
	 * @param f la forme de la pièce
	 * @param h la hauteur de la pièce
	 * @param p détermine si la pièce est pleine ou creuse
	 */
	public Piece(Couleur c, Forme f, Hauteur h, PleineOuCreuse p) {
		couleur = c;
		forme = f;
		hauteur = h;
		pleineOuCreuse = p;
	}

	/**
	 * @return la couleur d'une pièce
	 */
	public Couleur getCouleur() {
		return couleur;
	}

	/**
	 * @return la forme d'une pièce
	 */
	public Forme getForme() {
		return forme;
	}

	/**
	 * @return la hauteur d'une pièce
	 */
	public Hauteur getHauteur() {
		return hauteur;
	}

	/**
	 * @return CREUSE si la pièce est creuse, PLEINE sinon
	 */
	public PleineOuCreuse getPleineOuCreuse() {
		return pleineOuCreuse;
	}

	/**
	 * Vérifie si deux pièces sont identiques
	 */
	@Override
	public boolean equals(Object obj) {
		Piece p = (Piece) obj;

		if (this == p) {
			return true;
		}

		return (this.couleur.equals(p.couleur) && this.forme.equals(p.forme) && this.hauteur.equals(p.hauteur) 
				&& this.pleineOuCreuse.equals(p.pleineOuCreuse));
	}
	
	/**
	 * Représente un objet Piece sous la forme d'un string
	 */
	@Override
	public String toString() {
		String c, f, h, p;
		
		if(couleur == Couleur.BLANCHE) c = "B";
		else c = "N";
		if(forme == Forme.CARREE) f = "C";
		else f = "R";
		if(hauteur == Hauteur.BASSE) h = "B";
		else h = "H";
		if(pleineOuCreuse == PleineOuCreuse.CREUSE) p = "C";
		else p = "P";

		return c+f+h+p;
	}
}