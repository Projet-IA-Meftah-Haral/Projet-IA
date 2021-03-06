package plateau;

import caracteristiquesPiece.Couleur;
import caracteristiquesPiece.Forme;
import caracteristiquesPiece.Hauteur;
import caracteristiquesPiece.PleineOuCreuse;
import caracteristiquesPiece.Position;

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
}
