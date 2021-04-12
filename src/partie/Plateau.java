package partie;

import java.util.ArrayList;
import java.util.List;

import caracteristiquesPiece.*;

/**
* Représentation du plateau de jeu, et actions sur la partie
*/
public class Plateau {
	private List<Piece> listPiecesDispo = new ArrayList<Piece>();
	private Piece plateau[][] = new Piece[4][4]; 
	private Joueur gagnant;
	private boolean tourHumain;
	
	/**
	* 
	*/
	public Plateau() {
		listPiecesDispo.add(new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE));
		listPiecesDispo.add(new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.PLEINE));
		listPiecesDispo.add(new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.HAUTE, PleineOuCreuse.CREUSE));
		listPiecesDispo.add(new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.HAUTE, PleineOuCreuse.PLEINE));
		listPiecesDispo.add(new Piece(Couleur.BLANCHE, Forme.RONDE, Hauteur.BASSE, PleineOuCreuse.CREUSE));
		listPiecesDispo.add(new Piece(Couleur.BLANCHE, Forme.RONDE, Hauteur.BASSE, PleineOuCreuse.PLEINE));
		listPiecesDispo.add(new Piece(Couleur.BLANCHE, Forme.RONDE, Hauteur.HAUTE, PleineOuCreuse.CREUSE));
		listPiecesDispo.add(new Piece(Couleur.BLANCHE, Forme.RONDE, Hauteur.HAUTE, PleineOuCreuse.PLEINE));
		listPiecesDispo.add(new Piece(Couleur.NOIRE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE));
		listPiecesDispo.add(new Piece(Couleur.NOIRE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.PLEINE));
		listPiecesDispo.add(new Piece(Couleur.NOIRE, Forme.CARREE, Hauteur.HAUTE, PleineOuCreuse.CREUSE));
		listPiecesDispo.add(new Piece(Couleur.NOIRE, Forme.CARREE, Hauteur.HAUTE, PleineOuCreuse.PLEINE));
		listPiecesDispo.add(new Piece(Couleur.NOIRE, Forme.RONDE, Hauteur.BASSE, PleineOuCreuse.CREUSE));
		listPiecesDispo.add(new Piece(Couleur.NOIRE, Forme.RONDE, Hauteur.BASSE, PleineOuCreuse.PLEINE));
		listPiecesDispo.add(new Piece(Couleur.NOIRE, Forme.RONDE, Hauteur.HAUTE, PleineOuCreuse.CREUSE));
		listPiecesDispo.add(new Piece(Couleur.NOIRE, Forme.RONDE, Hauteur.HAUTE, PleineOuCreuse.PLEINE));
		
		// Le plateau est initialisé avec des pièces dont la case n'existe pas (1 <= i,j <= 4)
		// C'est pour éviter les erreurs relatives aux cases qui vaudraient null
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau.length; j++) {
				plateau[i][j] = new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE);
				plateau[i][j].attribuerPosition(new Position(0,0));
			}
		}
		
		gagnant = null;
		tourHumain = true;
	}
	
	/**
	* Accesseur du gagnant de la partie
	* @return retourne le joueur gagnant
	*/
	public Joueur getGagnant() {
		return gagnant;
	}
	
	/**
	* Acceusseur du plateau
	* @return retourne le plateau
	*/
	public Piece[][] getPlateau() {
		return plateau;
	}
	
	/**
	* Acceusseur de la liste des pièces dispo
	* @return retourne la liste des pièces dispo
	*/
	public List<Piece> getListPiecesDispo() {
		return listPiecesDispo;
	}
	
	/**
	* Acceusseur du tour
	* @return retourne vrai si c'est le tour de l'humain, faux sinon
	*/
	public boolean getTourHumain() {
		return tourHumain;
	}
	
	/**
	* Modification du plateau
	* @param plateau un plateau
	*/
	public void setPlateau(Piece[][] plateau) {
		this.plateau = plateau;
	}
	
	/**
	* Modificateur du tour humain
	* @param tourHumain un tour (vrai pour tour de l'humain / faux pour l'IA)
	*/
	public void setTourHumain(boolean tourHumain) {
		this.tourHumain = tourHumain;
	}
	
	/**
	* Affiche le plateau de la partie en cours
	*/
	public void affichagePlateau() {
		for( int i=0; i<plateau.length; i++){
			for( int j=0; j<plateau.length; j++){
				System.out.print(plateau[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	* Supprime la pièce sélectionnée par le joueur de la liste des pièces disponibles
	* @param p : Pièce choisi par le joueur
	* @return true si la pièce est disponible, false sinon
	*/
	public boolean supprimerPiece(Piece p) {
		if(listPiecesDispo.contains(p)) {
			listPiecesDispo.remove(p);
			return true;
		}
		return false;
	}
	
	/**
	* Une pièce est déposée sur le plateau
	* @param p Pièce à poser
	* @param j : Joueur qui pose la pièce
	* @return true si la case choisie est disponible, false sinon
	*/
	public boolean remplirPlateau(Piece p, Joueur j) {
		if(plateau[p.getPos().getI()-1][p.getPos().getJ()-1].getPos().equals(new Position(0,0))) {
			plateau[p.getPos().getI()-1][p.getPos().getJ()-1] = p;
			if(aGagne()) gagnant = j;
			return true;
		}
		return false;
	}
	
	/**
	* Permet de savoir si un joueur a gagné la partie
	* @return true si un joueur a remporté la partie, false sinon
	*/
	public boolean aGagne() {
		return victoireDiagonale() || victoireLigne() || victoireColonne();
	}
	
	/**
	* Permet de savoir si le plateau est rempli et donc de savoir si la partie est terminée
	* @return true si le plateau est rempli, false sinon 
	*/
	public boolean plateauRempli() {
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau.length; j++) {
				if(plateau[i][j].getPos().equals(new Position(0,0))) return false;
			}
		}
		return true;
	}
	
	/**
	* Vérifie si 4 pièce ayant une caractéristique commune sont alignées en diagonale. Cela revient
	* à vérifier si la partie a été remportée par l'un des deux joueurs
	* @return true si un joueur a remporté la partie, false sinon
	*/
	public boolean victoireDiagonale() {
		int compteurCouleur=0, compteurForme=0, compteurHauteur=0, compteurPleineCreuse=0;
		boolean couleur=true, forme=true, hauteur=true, pleineCreuse=true;
		
		for(int i=0; i<plateau.length-1; i++) {
			if(couleur) {
				if(plateau[i+1][i+1].getPos().equals(new Position(0,0)) || 
				plateau[i][i].getCouleur() != plateau[i+1][i+1].getCouleur()) {
					couleur = false;
				}
				else compteurCouleur++;
			}
			
			if(forme) {
				if(plateau[i+1][i+1].getPos().equals(new Position(0,0)) || 
				plateau[i][i].getForme() != plateau[i+1][i+1].getForme()) {
					forme = false;
				}
				else compteurForme++;
			}
			
			if(hauteur) {
				if(plateau[i+1][i+1].getPos().equals(new Position(0,0)) ||
				plateau[i][i].getHauteur() != plateau[i+1][i+1].getHauteur()) {
					hauteur = false;
				}
				else compteurHauteur++;
			}
			
			if(pleineCreuse) {
				if(plateau[i+1][i+1].getPos().equals(new Position(0,0)) ||
				plateau[i][i].getPleineOuCreuse() != plateau[i+1][i+1].getPleineOuCreuse()) {
					pleineCreuse = false;
				}
				else compteurPleineCreuse++;
			}
		}
		
		if(compteurCouleur==plateau.length-1 || compteurForme==plateau.length-1 || compteurHauteur==plateau.length-1 || 
		compteurPleineCreuse==plateau.length-1) {
			return true;
		}
		
		else {
			compteurCouleur=0; compteurForme=0; compteurHauteur=0; compteurPleineCreuse=0;
			couleur=true; forme=true; hauteur=true; pleineCreuse=true;
			
			for(int i=plateau.length-1, j=0; j<plateau.length-1; i--, j++) {
				if(couleur) {
					if(plateau[i-1][j+1].getPos().equals(new Position(0,0)) || 
					plateau[i][j].getCouleur() != plateau[i-1][j+1].getCouleur()) {
						couleur = false;
					}
					else compteurCouleur++;
				}
				
				if(forme) {
					if(plateau[i-1][j+1].getPos().equals(new Position(0,0)) || 
					plateau[i][j].getForme() != plateau[i-1][j+1].getForme()) {
						forme = false;
					}
					else compteurForme++;
				}
				
				if(hauteur) {
					if(plateau[i-1][j+1].getPos().equals(new Position(0,0)) ||
					plateau[i][j].getHauteur() != plateau[i-1][j+1].getHauteur()) {
						hauteur = false;
					}
					else compteurHauteur++;
				}
				
				if(pleineCreuse) {
					if(plateau[i-1][j+1].getPos().equals(new Position(0,0)) ||
					plateau[i][j].getPleineOuCreuse() != plateau[i-1][j+1].getPleineOuCreuse()) {
						pleineCreuse = false;
					}
					else compteurPleineCreuse++;
				}
			}
		}
		
		if(compteurCouleur==plateau.length-1 || compteurForme==plateau.length-1 || compteurHauteur==plateau.length-1 || 
		compteurPleineCreuse==plateau.length-1) {
			return true;
		}
		else return false;
	}
	
	
	/**
	* Vérifie si 4 pièce ayant une caractéristique commune sont alignées en ligne. Cela revient
	* à vérifier si la partie a été remportée par l'un des deux joueurs 
	* @return true si un joueur a remporté la partie, false sinon
	*/
	public boolean victoireLigne() {
		int compteurCouleur=0, compteurForme=0, compteurHauteur=0, compteurPleineCreuse=0;
		boolean couleur=true, forme=true, hauteur=true, pleineCreuse=true;
		
		for(int i=0; i<plateau.length; i++) {
			
			for(int j=0; j<plateau.length-1; j++) {
				
				if(couleur) {
					if(plateau[i][j+1].getPos().equals(new Position(0,0)) || 
					plateau[i][j].getCouleur() != plateau[i][j+1].getCouleur()) {
						couleur = false;
					}
					else compteurCouleur++;
				}
				
				if(forme) {
					if(plateau[i][j+1].getPos().equals(new Position(0,0)) || 
					plateau[i][j].getForme() != plateau[i][j+1].getForme()) {
						forme = false;
					}
					else compteurForme++;
				}
				
				if(hauteur) {
					if(plateau[i][j+1].getPos().equals(new Position(0,0)) ||
					plateau[i][j].getHauteur() != plateau[i][j+1].getHauteur()) {
						hauteur = false;
					}
					else compteurHauteur++;
				}
				
				if(pleineCreuse) {
					if(plateau[i][j+1].getPos().equals(new Position(0,0)) ||
					plateau[i][j].getPleineOuCreuse() != plateau[i][j+1].getPleineOuCreuse()) {
						pleineCreuse = false;
					}
					else compteurPleineCreuse++;
				}
			}
		}
		
		if(compteurCouleur==plateau.length-1 || compteurForme==plateau.length-1 || compteurHauteur==plateau.length-1 || 
		compteurPleineCreuse==plateau.length-1) {
			return true;
		}
		else return false;
	}
	
	/**
	* Vérifie si 4 pièce ayant une caractéristique commune sont alignées en colonne. Cela revient
	* à vérifier si la partie a été remportée par l'un des deux joueurs 
	* @return true si un joueur a remporté la partie, false sinon
	*/
	public boolean victoireColonne() {
		int compteurCouleur=0, compteurForme=0, compteurHauteur=0, compteurPleineCreuse=0;
		boolean couleur=true, forme=true, hauteur=true, pleineCreuse=true;
		
		for(int j=0; j<plateau.length; j++) {
			
			for(int i=0; i<plateau.length-1; i++) {
				
				if(couleur) {
					if(plateau[i+1][j].getPos().equals(new Position(0,0)) || 
					plateau[i][j].getCouleur() != plateau[i+1][j].getCouleur()) {
						couleur = false;
					}
					else compteurCouleur++;
				}
				
				if(forme) {
					if(plateau[i+1][j].getPos().equals(new Position(0,0)) || 
					plateau[i][j].getForme() != plateau[i+1][j].getForme()) {
						forme = false;
					}
					else compteurForme++;
				}
				
				if(hauteur) {
					if(plateau[i+1][j].getPos().equals(new Position(0,0)) ||
					plateau[i][j].getHauteur() != plateau[i+1][j].getHauteur()) {
						hauteur = false;
					}
					else compteurHauteur++;
				}
				
				if(pleineCreuse) {
					if(plateau[i+1][j].getPos().equals(new Position(0,0)) ||
					plateau[i][j].getPleineOuCreuse() != plateau[i+1][j].getPleineOuCreuse()) {
						pleineCreuse = false;
					}
					else compteurPleineCreuse++;
				}
			}
		}
		
		if(compteurCouleur==plateau.length-1 || compteurForme==plateau.length-1 || compteurHauteur==plateau.length-1 || 
		compteurPleineCreuse==plateau.length-1) {
			return true;
		}
		else return false;
	}
}