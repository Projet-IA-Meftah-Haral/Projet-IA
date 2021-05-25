package partie;

import java.util.ArrayList;
import java.util.List;

import caracteristiquesPiece.*;
import ia.Action;
import ia.Position;

/**
* Représentation du plateau de jeu et actions sur la partie
*/
public class Partie {
	private Piece plateau[][] = new Piece[4][4];
	private List<Piece> piecesDisponibles = new ArrayList<>();
	private String joueur1, joueur2;
	private boolean tourJ1;
	
	/**
	* Construit un objet Partie
	* @param j1 le joueur 1, le joueur humain s'il s'agit d'une partie contre l'IA
	* @param j2 le joueur 2, l'IA s'il s'agit d'une partie contre l'IA
	* @param tj1 détermine si c'est le tour du joueur 1 ou non
	*/
	public Partie(String j1, String j2, boolean tj1) {
		piecesDisponibles.add(new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE));
		piecesDisponibles.add(new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.PLEINE));
		piecesDisponibles.add(new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.HAUTE, PleineOuCreuse.CREUSE));
		piecesDisponibles.add(new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.HAUTE, PleineOuCreuse.PLEINE));
		piecesDisponibles.add(new Piece(Couleur.BLANCHE, Forme.RONDE, Hauteur.BASSE, PleineOuCreuse.CREUSE));
		piecesDisponibles.add(new Piece(Couleur.BLANCHE, Forme.RONDE, Hauteur.BASSE, PleineOuCreuse.PLEINE));
		piecesDisponibles.add(new Piece(Couleur.BLANCHE, Forme.RONDE, Hauteur.HAUTE, PleineOuCreuse.CREUSE));
		piecesDisponibles.add(new Piece(Couleur.BLANCHE, Forme.RONDE, Hauteur.HAUTE, PleineOuCreuse.PLEINE));
		piecesDisponibles.add(new Piece(Couleur.NOIRE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE));
		piecesDisponibles.add(new Piece(Couleur.NOIRE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.PLEINE));
		piecesDisponibles.add(new Piece(Couleur.NOIRE, Forme.CARREE, Hauteur.HAUTE, PleineOuCreuse.CREUSE));
		piecesDisponibles.add(new Piece(Couleur.NOIRE, Forme.CARREE, Hauteur.HAUTE, PleineOuCreuse.PLEINE));
		piecesDisponibles.add(new Piece(Couleur.NOIRE, Forme.RONDE, Hauteur.BASSE, PleineOuCreuse.CREUSE));
		piecesDisponibles.add(new Piece(Couleur.NOIRE, Forme.RONDE, Hauteur.BASSE, PleineOuCreuse.PLEINE));
		piecesDisponibles.add(new Piece(Couleur.NOIRE, Forme.RONDE, Hauteur.HAUTE, PleineOuCreuse.CREUSE));
		piecesDisponibles.add(new Piece(Couleur.NOIRE, Forme.RONDE, Hauteur.HAUTE, PleineOuCreuse.PLEINE));
		
		joueur1 = j1;
		joueur2 = j2;
		tourJ1 = tj1;
	}
	
	/**
	* @return le plateau
	*/
	public Piece[][] getPlateau() {
		return plateau;
	}
	
	/**
	* @return la liste des pièces disponible
	*/
	public List<Piece> getPiecesDisponibles() {
		return piecesDisponibles;
	}
	
	/**
	* @return le nom du joueur 1
	*/
	public String getJoueur1() {
		return joueur1;
	}
	
	/**
	* @return le nom du joueur 2
	*/
	public String getJoueur2() {
		return joueur2;
	}
	
	/**
	* @return true si c'est le tour du joueur 1, faux sinon
	*/
	public boolean getTourJ1() {
		return tourJ1;
	}
	
	/**
	* Change la valeur du booléen qui détermine qui doit jouer 
	*/
	public void setTourJ1() {
		tourJ1 = !tourJ1;
	}
	
	/**
	* Affiche le plateau de la partie en cours
	*/
	public void afficherPlateau() {
		// Dans les boucles on utilise plateau.length et non le chiffre 4 car on veut que le programme puisse
		// être modifier facilement si jamais quelqu'un veut qu'il y ait plus ou moins de cases
		for(int i=0; i<plateau.length; i++){
			for(int j=0; j<plateau.length; j++){
				if(plateau[i][j] == null) System.out.print("vide\t");
				else System.out.print(plateau[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	/**
	* Supprime une pièce de la liste des pièces disponibles
	* @param p la pièce à supprimer
	* @return true si la suppression s'est bien déroulée, false sinon
	*/
	public boolean supprimerPiece(Piece p) {
		for(Piece piece : piecesDisponibles) {
			if(piece.equals(p)) {
				piecesDisponibles.remove(piece);
				return true;
			}
		}
		return false;
	}
	
	/**
	* Une pièce est déposée sur le plateau
	* @param p la pièce à poser
	* @param j le joueur qui pose la pièce
	* @return true si la position choisie est vacante, false sinon
	*/
	public boolean remplirPlateau(Piece p, int i, int j) {
		if(plateau[i-1][j-1] == null) {
			plateau[i-1][j-1] = p;
			return true;
		}
		return false;	
	}
	
	/**
	* @return true si la partie est finie, false sinon
	*/
	public boolean testTerminal() {
		if(aGagne() || plateauRempli()) return true;
		else return false;
	}
	
	/**
	* Permet de savoir si le plateau est rempli
	* @return true si le plateau est rempli, false sinon 
	*/
	public boolean plateauRempli() {
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau.length; j++) {
				if(plateau[i][j] == null) return false;
			}
		}
		return true;
	}
	
	/**
	* Permet de savoir si un joueur a gagné la partie
	* @return true si un joueur a remporté la partie, false sinon
	*/
	public boolean aGagne() {
		return victoireDiagonale() || victoireLigne() || victoireColonne();
	}
	
	/**
	* Vérifie si 4 pièce ayant une caractéristique commune sont alignées en diagonale.
	* @return true si c'est le cas, false sinon
	*/
	public boolean victoireDiagonale() {
		int compteurCouleur=0, compteurForme=0, compteurHauteur=0, compteurPleineCreuse=0;
		boolean couleur=true, forme=true, hauteur=true, pleineCreuse=true;
		
		// Diagonale allant de la position (1,1) à la position (4,4)
		for(int i=0; i<plateau.length-1; i++) {
			if(couleur) {
				if(plateau[i][i] == null || plateau[i+1][i+1] == null ||
				plateau[i][i].getCouleur() != plateau[i+1][i+1].getCouleur()) {
					couleur = false;
				}
				else compteurCouleur++;
			}
			
			if(forme) {
				if(plateau[i][i]==null || plateau[i+1][i+1]==null ||
				plateau[i][i].getForme() != plateau[i+1][i+1].getForme()) {
					forme = false;
				}
				else compteurForme++;
			}
			
			if(hauteur) {
				if(plateau[i][i]==null || plateau[i+1][i+1]==null ||
				plateau[i][i].getHauteur() != plateau[i+1][i+1].getHauteur()) {
					hauteur = false;
				}
				else compteurHauteur++;
			}
			
			if(pleineCreuse) {
				if(plateau[i][i]==null || plateau[i+1][i+1]==null ||
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
			
			// Diagonale allant de la position (1,4) à la position (4,1)
			for(int i=plateau.length-1, j=0; j<plateau.length-1; i--, j++) {
				if(couleur) {
					if(plateau[i][j]==null || plateau[i-1][j+1]==null ||
					plateau[i][j].getCouleur() != plateau[i-1][j+1].getCouleur()) {
						couleur = false;
					}
					else compteurCouleur++;
				}
				
				if(forme) {
					if(plateau[i][j]==null || plateau[i-1][j+1]==null ||
					plateau[i][j].getForme() != plateau[i-1][j+1].getForme()) {
						forme = false;
					}
					else compteurForme++;
				}
				
				if(hauteur) {
					if(plateau[i][j]==null || plateau[i-1][j+1]==null ||
					plateau[i][j].getHauteur() != plateau[i-1][j+1].getHauteur()) {
						hauteur = false;
					}
					else compteurHauteur++;
				}
				
				if(pleineCreuse) {
					if(plateau[i][j]==null || plateau[i-1][j+1]==null ||
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
	* Vérifie si 4 pièce ayant une caractéristique commune sont alignées en ligne.
	* @return true si c'est le cas, false sinon
	*/
	public boolean victoireLigne() {
		int compteurCouleur=0, compteurForme=0, compteurHauteur=0, compteurPleineCreuse=0;
		boolean couleur=true, forme=true, hauteur=true, pleineCreuse=true;
		
		for(int i=0; i<plateau.length; i++) {
			
			for(int j=0; j<plateau.length-1; j++) {
				
				if(couleur) {
					if(plateau[i][j]==null || plateau[i][j+1]==null ||
					plateau[i][j].getCouleur() != plateau[i][j+1].getCouleur()) {
						couleur = false;
					}
					else compteurCouleur++;
				}
				
				if(forme) {
					if(plateau[i][j]==null || plateau[i][j+1]==null ||
					plateau[i][j].getForme() != plateau[i][j+1].getForme()) {
						forme = false;
					}
					else compteurForme++;
				}
				
				if(hauteur) {
					if(plateau[i][j]==null || plateau[i][j+1]==null ||
					plateau[i][j].getHauteur() != plateau[i][j+1].getHauteur()) {
						hauteur = false;
					}
					else compteurHauteur++;
				}
				
				if(pleineCreuse) {
					if(plateau[i][j]==null || plateau[i][j+1]==null ||
					plateau[i][j].getPleineOuCreuse() != plateau[i][j+1].getPleineOuCreuse()) {
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
				couleur = true;
				forme = true;
				hauteur = true;
				pleineCreuse = true;
				compteurCouleur = 0;
				compteurForme = 0;
				compteurHauteur = 0;
				compteurPleineCreuse = 0;
			}
		}
		
		return false;
	}
	
	/**
	* Vérifie si 4 pièce ayant une caractéristique commune sont alignées en colonne.
	* @return true si c'est le cas, false sinon
	*/
	public boolean victoireColonne() {
		int compteurCouleur=0, compteurForme=0, compteurHauteur=0, compteurPleineCreuse=0;
		boolean couleur=true, forme=true, hauteur=true, pleineCreuse=true;
		
		for(int j=0; j<plateau.length; j++) {
			
			for(int i=0; i<plateau.length-1; i++) {
				
				if(couleur) {
					if(plateau[i][j]==null || plateau[i+1][j]==null ||
					plateau[i][j].getCouleur() != plateau[i+1][j].getCouleur()) {
						couleur = false;
					}
					else compteurCouleur++;
				}
				
				if(forme) {
					if(plateau[i][j]==null || plateau[i+1][j]==null || 
					plateau[i][j].getForme() != plateau[i+1][j].getForme()) {
						forme = false;
					}
					else compteurForme++;
				}
				
				if(hauteur) {
					if(plateau[i][j]==null || plateau[i+1][j]==null ||
					plateau[i][j].getHauteur() != plateau[i+1][j].getHauteur()) {
						hauteur = false;
					}
					else compteurHauteur++;
				}
				
				if(pleineCreuse) {
					if(plateau[i][j]==null || plateau[i+1][j]==null ||
					plateau[i][j].getPleineOuCreuse() != plateau[i+1][j].getPleineOuCreuse()) {
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
				couleur = true;
				forme = true;
				hauteur = true;
				pleineCreuse = true;
				compteurCouleur = 0;
				compteurForme = 0;
				compteurHauteur = 0;
				compteurPleineCreuse = 0;
			}
		}
		
		return false;
	}
	
	/**
	* @return la liste des actions possibles en fonctions de l'état courant
	*/
	public List<Action> actionsPossibles() {
		List<Action> actions = new ArrayList<>();
		
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau.length; j++) {
				// Si la case est vide on rempli la liste des actions selon les pièces dispo et la position du plateau 
				if(plateau[i][j]==null) {
					for(Piece p : piecesDisponibles) {
						actions.add(new Action(p, new Position(i, j)));
					}
				}
			}
		}
		
		return actions;
	}
	
	/**
	* Retourne la liste des actions possibles en fonction de la pièce choisie par l'adversaire
	* @param piece en fonction de laquelle on détermine les actions possibles
	* @return la liste des actions
	*/
	public List<Action> actionsPossiblesPiece(Piece piece) {
		List<Action> actions = new ArrayList<>();
		
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau.length; j++) {
				if(plateau[i][j]==null) {
					actions.add(new Action(piece, new Position(i,j)));
				}
			}
		}
		
		return actions;
	}
	
	/**
	* Modifie l'état de la partie en fonction d'une action
	* @param action l'action que l'on veut effectuer sur le plateau
	*/
	public void successeur(Action action) {
		Piece piece = action.getPiece();
		plateau[action.getI()][action.getJ()] = piece;
		setTourJ1();
		supprimerPiece(piece);
	}
	
	/**
	* Annule une action et effectue les modifications nécessaires sur le plateau
	* @param action l'action que l'on veut annuler
	*/
	public void annulerAction(Action action) {
		plateau[action.getI()][action.getJ()] = null;
		setTourJ1();
		piecesDisponibles.add(action.getPiece());
	}
	
	/**
	* Retourne l'utilité / l'heuristique en fonction de l'état du plateau
	* @param piece la pièce qui doit être déposée si c'est le tour de l'IA  
	* @return l'utilité
	*/
	public int utilite(Piece piece) {
		// tourJ1 => tour de l'humain; !tourJ1 => tour de l'IA
		// Si c'est le tour de l'humain et que quelqu'un a déjà gagné, cela veut dire que l'IA a gagné
		// Si c'est le tour de l'IA, c'est l'humain qui a gagné
		if(aGagne()) {
			if(tourJ1) return 3;
			else return -3;
		}
		else if(plateauRempli()) return 0;
		
		if ((troisPiecesMemeColonne(piece) || troisPiecesMemeDiag(piece) || troisPiecesMemeLigne(piece))) {
			if (tourJ1) return -2;
			else return 2;
		}
		if (tourJ1) return -1;
		else return 1;
	}
	
	/**
	 * Indique s'il y a 3 pièces ayant une caractéristique commune sur une diagonale 
	 * @param piece la pièce qui doit être déposée si c'est le tour de l'IA
	 * @return true si c'est le cas, false sinon
	 */
	public boolean troisPiecesMemeDiag(Piece piece) {
		// Les listes contiennent les attributs des pièces se trouvant sur une même diagonale
		List<Couleur> couleurs = new ArrayList<>();
		List<Forme> formes = new ArrayList<>();
		List<Hauteur> hauteurs = new ArrayList<>();
		List<PleineOuCreuse> pleinesCreuses = new ArrayList<>();
		
		// Diagonale allant de la position (1,1) à (4,4)
		for(int i=0; i<plateau.length; i++) {
			if(plateau[i][i]!=null) {
				couleurs.add(plateau[i][i].getCouleur());
				formes.add(plateau[i][i].getForme());
				hauteurs.add(plateau[i][i].getHauteur());
				pleinesCreuses.add(plateau[i][i].getPleineOuCreuse());
			}	
		}	
		
		// On vérifie si il y a une liste avec trois élément, et si c'est le cas, on vérifie 
		// que ces éléments sont bien tous identiques

		boolean memeCouleur = false;
		if(couleurs.size() == plateau.length-1) {
			memeCouleur = true;
			for(int j=0; j<couleurs.size()-1; j++) {
				for(int k=j+1; k<couleurs.size(); k++) {
					if(couleurs.get(j) != couleurs.get(k)) {
						memeCouleur = false;
						break;
					}	 
				}
				if(!memeCouleur) break;
			}
		}
		if(memeCouleur && piece.getCouleur() == couleurs.get(0)) return true;
		
		boolean memeForme = false;
		if(formes.size() == plateau.length-1) {
			memeForme = true;
			for(int j=0; j<formes.size()-1; j++) {
				for(int k=j+1; k<formes.size(); k++) {
					if(formes.get(j) != formes.get(k)) {
						memeForme = false;
						break;
					}	 
				}
				if(!memeForme) break;
			}
		}
		if(memeForme && piece.getForme() == formes.get(0)) return true;
		
		boolean memeHauteur = false;
		if(hauteurs.size() == plateau.length-1) {
			memeHauteur = true;
			for(int j=0; j<hauteurs.size()-1; j++) {
				for(int k=j+1; k<hauteurs.size(); k++) {
					if(hauteurs.get(j) != hauteurs.get(k)) {
						memeHauteur = false;
						break;
					}	 
				}
				if(!memeHauteur) break;
			}
		}
		if(memeHauteur && piece.getHauteur() == hauteurs.get(0)) return true;
		
		boolean memePleineCreuse = false;
		if(pleinesCreuses.size() == plateau.length-1) {
			memePleineCreuse = true;
			for(int j=0; j<pleinesCreuses.size()-1; j++) {
				for(int k=j+1; k<pleinesCreuses.size(); k++) {
					if(pleinesCreuses.get(j) != pleinesCreuses.get(k)) {
						memePleineCreuse = false;
						break;
					}	 
				}
				if(!memePleineCreuse) break;
			}
		}
		if(memePleineCreuse && piece.getPleineOuCreuse() == pleinesCreuses.get(0)) return true;
		
		couleurs.clear();
		formes.clear();
		hauteurs.clear();
		pleinesCreuses.clear();
		
		// Diagonale allant de la position (1,4) à (4,1)
		for(int i=plateau.length-1, j=0; j<plateau.length; i--, j++) {
			if(plateau[i][j]!=null) {
				couleurs.add(plateau[i][j].getCouleur());
				formes.add(plateau[i][j].getForme());
				hauteurs.add(plateau[i][j].getHauteur());
				pleinesCreuses.add(plateau[i][j].getPleineOuCreuse());
			}	
		}	
		
		boolean mCouleur = false;
		if(couleurs.size() == plateau.length-1) {
			mCouleur = true;
			for(int j=0; j<couleurs.size()-1; j++) {
				for(int k=j+1; k<couleurs.size(); k++) {
					if(couleurs.get(j) != couleurs.get(k)) {
						mCouleur = false;
						break;
					}	 
				}
				if(!mCouleur) break;
			}
		}
		if(mCouleur && piece.getCouleur() == couleurs.get(0)) return true;
		
		boolean mForme = false;
		if(formes.size() == plateau.length-1) {
			mForme = true;
			for(int j=0; j<formes.size()-1; j++) {
				for(int k=j+1; k<formes.size(); k++) {
					if(formes.get(j) != formes.get(k)) {
						mForme = false;
						break;
					}	 
				}
				if(!mForme) break;
			}
		}
		if(mForme && piece.getForme() == formes.get(0)) return true;
		
		boolean mHauteur = false;
		if(hauteurs.size() == plateau.length-1) {
			mHauteur = true;
			for(int j=0; j<hauteurs.size()-1; j++) {
				for(int k=j+1; k<hauteurs.size(); k++) {
					if(hauteurs.get(j) != hauteurs.get(k)) {
						mHauteur = false;
						break;
					}	 
				}
				if(!mHauteur) break;
			}
		}
		if(mHauteur && piece.getHauteur() == hauteurs.get(0)) return true;
		
		boolean mPleineCreuse = false;
		if(pleinesCreuses.size() == plateau.length-1) {
			mPleineCreuse = true;
			for(int j=0; j<pleinesCreuses.size()-1; j++) {
				for(int k=j+1; k<pleinesCreuses.size(); k++) {
					if(pleinesCreuses.get(j) != pleinesCreuses.get(k)) {
						mPleineCreuse = false;
						break;
					}	 
				}
				if(!mPleineCreuse) break;
			}
		}
		if(mPleineCreuse && piece.getPleineOuCreuse() == pleinesCreuses.get(0)) return true;
		
		return false;
	}
	
	/**
	 * Indique s'il y a 3 pièces ayant une caractéristique commune sur une même ligne
	 * @param piece la pièce qui doit être déposée si c'est le tour de l'IA
	 * @return true si c'est le cas, false sinon
	 */
	public boolean troisPiecesMemeLigne(Piece piece) {
		List<Couleur> couleurs = new ArrayList<>();
		List<Forme> formes = new ArrayList<>();
		List<Hauteur> hauteurs = new ArrayList<>();
		List<PleineOuCreuse> pleinesCreuses = new ArrayList<>();
		
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau.length; j++) {
				if(plateau[i][j]!=null) {
					couleurs.add(plateau[i][j].getCouleur());
					formes.add(plateau[i][j].getForme());
					hauteurs.add(plateau[i][j].getHauteur());
					pleinesCreuses.add(plateau[i][j].getPleineOuCreuse());
				}	
			}
			
			boolean memeCouleur = false;
			if(couleurs.size() == plateau.length-1) {
				memeCouleur = true;
				for(int j=0; j<couleurs.size()-1; j++) {
					for(int k=j+1; k<couleurs.size(); k++) {
						if(couleurs.get(j) != couleurs.get(k)) {
							memeCouleur = false;
							break;
						}	 
					}
					if(!memeCouleur) break;
				}
			}
			if(memeCouleur && piece.getCouleur() == couleurs.get(0)) return true;
			
			boolean memeForme = false;
			if(formes.size() == plateau.length-1) {
				memeForme = true;
				for(int j=0; j<formes.size()-1; j++) {
					for(int k=j+1; k<formes.size(); k++) {
						if(formes.get(j) != formes.get(k)) {
							memeForme = false;
							break;
						}	 
					}
					if(!memeForme) break;
				}
			}
			if(memeForme && piece.getForme() == formes.get(0)) return true;
			
			boolean memeHauteur = false;
			if(hauteurs.size() == plateau.length-1) {
				memeHauteur = true;
				for(int j=0; j<hauteurs.size()-1; j++) {
					for(int k=j+1; k<hauteurs.size(); k++) {
						if(hauteurs.get(j) != hauteurs.get(k)) {
							memeHauteur = false;
							break;
						}	 
					}
					if(!memeHauteur) break;
				}
			}
			if(memeHauteur && piece.getHauteur() == hauteurs.get(0)) return true;
			
			boolean memePleineCreuse = false;
			if(pleinesCreuses.size() == plateau.length-1) {
				memePleineCreuse = true;
				for(int j=0; j<pleinesCreuses.size()-1; j++) {
					for(int k=j+1; k<pleinesCreuses.size(); k++) {
						if(pleinesCreuses.get(j) != pleinesCreuses.get(k)) {
							memePleineCreuse = false;
							break;
						}	 
					}
					if(!memePleineCreuse) break;
				}
			}
			if(memePleineCreuse && piece.getPleineOuCreuse() == pleinesCreuses.get(0)) return true;
			
			couleurs.clear();
			formes.clear();
			hauteurs.clear();
			pleinesCreuses.clear();
		}
		
		return false;
	}
	
	/**
	 * Indique s'il y a 3 pièces ayant une caractéristique commune sur une même colonne
	 * @param piece la pièce qui doit être déposée si c'est le tour de l'IA
	 * @return true si c'est le cas, false sinon
	 */
	public boolean troisPiecesMemeColonne(Piece piece) {
		List<Couleur> couleurs = new ArrayList<>();
		List<Forme> formes = new ArrayList<>();
		List<Hauteur> hauteurs = new ArrayList<>();
		List<PleineOuCreuse> pleinesCreuses = new ArrayList<>();
		
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau.length; j++) {
				if(plateau[j][i]!=null) {
					couleurs.add(plateau[j][i].getCouleur());
					formes.add(plateau[j][i].getForme());
					hauteurs.add(plateau[j][i].getHauteur());
					pleinesCreuses.add(plateau[j][i].getPleineOuCreuse());
				}	
			}
			
			boolean memeCouleur = false;
			if(couleurs.size() == plateau.length-1) {
				memeCouleur = true;
				for(int j=0; j<couleurs.size()-1; j++) {
					for(int k=j+1; k<couleurs.size(); k++) {
						if(couleurs.get(j) != couleurs.get(k)) {
							memeCouleur = false;
							break;
						}	 
					}
					if(!memeCouleur) break;
				}
			}
			if(memeCouleur && piece.getCouleur() == couleurs.get(0)) return true;
			
			boolean memeForme = false;
			if(formes.size() == plateau.length-1) {
				memeForme = true;
				for(int j=0; j<formes.size()-1; j++) {
					for(int k=j+1; k<formes.size(); k++) {
						if(formes.get(j) != formes.get(k)) {
							memeForme = false;
							break;
						}	 
					}
					if(!memeForme) break;
				}
			}
			if(memeForme && piece.getForme() == formes.get(0)) return true;
			
			boolean memeHauteur = false;
			if(hauteurs.size() == plateau.length-1) {
				memeHauteur = true;
				for(int j=0; j<hauteurs.size()-1; j++) {
					for(int k=j+1; k<hauteurs.size(); k++) {
						if(hauteurs.get(j) != hauteurs.get(k)) {
							memeHauteur = false;
							break;
						}	 
					}
					if(!memeHauteur) break;
				}
			}
			if(memeHauteur && piece.getHauteur() == hauteurs.get(0)) return true;
			
			boolean memePleineCreuse = false;
			if(pleinesCreuses.size() == plateau.length-1) {
				memePleineCreuse = true;
				for(int j=0; j<pleinesCreuses.size()-1; j++) {
					for(int k=j+1; k<pleinesCreuses.size(); k++) {
						if(pleinesCreuses.get(j) != pleinesCreuses.get(k)) {
							memePleineCreuse = false;
							break;
						}	 
					}
					if(!memePleineCreuse) break;
				}
			}
			if(memePleineCreuse && piece.getPleineOuCreuse() == pleinesCreuses.get(0)) return true;
			
			couleurs.clear();
			formes.clear();
			hauteurs.clear();
			pleinesCreuses.clear();
		}
		
		return false;
	}
}