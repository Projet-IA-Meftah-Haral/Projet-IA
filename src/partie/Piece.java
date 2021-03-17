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

	@Override
	public boolean equals(Object obj) {
		Piece p = (Piece) obj;

		if (this == p) {
			return true;
		}

		return (this.couleur.equals(p.couleur) && this.forme.equals(p.forme) && this.hauteur.equals(p.hauteur) 
				&& this.pleineOuCreuse.equals(p.pleineOuCreuse) && this.pos.equals(p.pos));
	}
	
	public boolean caracteristiqueEnCommun(Piece p) {
		return (couleur==p.couleur || forme==p.forme || hauteur==p.hauteur || pleineOuCreuse==p.pleineOuCreuse);
	}
}