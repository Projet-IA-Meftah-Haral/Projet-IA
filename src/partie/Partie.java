package partie;

import java.util.ArrayList;
import java.util.List;

import caracteristiquesPiece.*;
import ia.Action;
import ia.Position;

/**
* Représentation du plateau de jeu, et actions sur la partie
*/
public class Partie {
	private Piece plateau[][] = new Piece[4][4];
	private List<Piece> piecesDisponibles = new ArrayList<>();
	private String joueur1, joueur2;
	private boolean tourJ1;
	
	/**
	* 
	*/
	public Partie(String j1, String j2, boolean tj1) {
		// Le plateau est initialisé avec des pièces afin de ne pas provoquer d'erreur
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau.length; j++) {
				plateau[i][j] = new Piece(Couleur.BLANCHE, Forme.CARREE, Hauteur.BASSE, PleineOuCreuse.CREUSE);
				plateau[i][j].setCaseVide(true);
			}
		}
		
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
	* Acceusseur du plateau
	* @return retourne le plateau
	*/
	public Piece[][] getPlateau() {
		return plateau;
	}
	
	public List<Piece> getPiecesDisponibles() {
		return piecesDisponibles;
	}
	
	public String getJoueur1() {
		return joueur1;
	}
	
	public String getJoueur2() {
		return joueur2;
	}
	
	/**
	* Acceusseur du tour
	* @return retourne vrai si c'est le tour de l'humain, faux sinon
	*/
	public boolean getTourJ1() {
		return tourJ1;
	}
	
	/**
	* Modification du plateau
	* @param plateau un plateau
	*/
	public void setPlateau(Piece[][] plateau) {
		this.plateau = plateau;
	}

	public void setPiecesDisponibles(Piece p) {
		piecesDisponibles.remove(p);
	}
	
	/**
	* Modificateur du tour humain
	* @param tourHumain un tour (vrai pour tour de l'humain / faux pour l'IA)
	*/
	public void setTourJ1() {
		tourJ1 = !tourJ1;
	}
	
	/**
	* Affiche le plateau de la partie en cours
	*/
	public void affichagePlateau() {
		for(int i=0; i<plateau.length; i++){
			for(int j=0; j<plateau.length; j++){
				System.out.print(plateau[i][j] + " ");
			}
			System.out.println();
		}
	}
	
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
	* @param p Pièce à poser
	* @param j : Joueur qui pose la pièce
	* @return true si la case choisie est disponible, false sinon
	*/
	public boolean remplirPlateau(Piece p, int i, int j) {
		if(plateau[i-1][j-1].getCaseVide()) {
			plateau[i-1][j-1] = p;
			return true;
		}
		return false;	
	}
	
	public boolean testTerminal() {
		if(aGagne() || plateauRempli()) return true;
		else return false;
	}
	
	/**
	* Permet de savoir si le plateau est rempli et donc de savoir si la partie est terminée
	* @return true si le plateau est rempli, false sinon 
	*/
	public boolean plateauRempli() {
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau.length; j++) {
				if(plateau[i][j].getCaseVide()) return false;
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
	* Vérifie si 4 pièce ayant une caractéristique commune sont alignées en diagonale. Cela revient
	* à vérifier si la partie a été remportée par l'un des deux joueurs
	* @return true si un joueur a remporté la partie, false sinon
	*/
	public boolean victoireDiagonale() {
		int compteurCouleur=0, compteurForme=0, compteurHauteur=0, compteurPleineCreuse=0;
		boolean couleur=true, forme=true, hauteur=true, pleineCreuse=true;
		
		for(int i=0; i<plateau.length-1; i++) {
			if(couleur) {
				if(plateau[i][i].getCaseVide() || plateau[i+1][i+1].getCaseVide() ||
				plateau[i][i].getCouleur() != plateau[i+1][i+1].getCouleur()) {
					couleur = false;
				}
				else compteurCouleur++;
			}
			
			if(forme) {
				if(plateau[i][i].getCaseVide() || plateau[i+1][i+1].getCaseVide() ||
				plateau[i][i].getForme() != plateau[i+1][i+1].getForme()) {
					forme = false;
				}
				else compteurForme++;
			}
			
			if(hauteur) {
				if(plateau[i][i].getCaseVide() || plateau[i+1][i+1].getCaseVide() ||
				plateau[i][i].getHauteur() != plateau[i+1][i+1].getHauteur()) {
					hauteur = false;
				}
				else compteurHauteur++;
			}
			
			if(pleineCreuse) {
				if(plateau[i][i].getCaseVide() || plateau[i+1][i+1].getCaseVide() ||
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
					if(plateau[i][j].getCaseVide() || plateau[i-1][j+1].getCaseVide() ||
					plateau[i][j].getCouleur() != plateau[i-1][j+1].getCouleur()) {
						couleur = false;
					}
					else compteurCouleur++;
				}
				
				if(forme) {
					if(plateau[i][j].getCaseVide() || plateau[i-1][j+1].getCaseVide() ||
					plateau[i][j].getForme() != plateau[i-1][j+1].getForme()) {
						forme = false;
					}
					else compteurForme++;
				}
				
				if(hauteur) {
					if(plateau[i][j].getCaseVide() || plateau[i-1][j+1].getCaseVide() ||
					plateau[i][j].getHauteur() != plateau[i-1][j+1].getHauteur()) {
						hauteur = false;
					}
					else compteurHauteur++;
				}
				
				if(pleineCreuse) {
					if(plateau[i][j].getCaseVide() || plateau[i-1][j+1].getCaseVide() ||
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
					if(plateau[i][j].getCaseVide() || plateau[i][j+1].getCaseVide() ||
					plateau[i][j].getCouleur() != plateau[i][j+1].getCouleur()) {
						couleur = false;
					}
					else compteurCouleur++;
				}
				
				if(forme) {
					if(plateau[i][j].getCaseVide() || plateau[i][j+1].getCaseVide() ||
					plateau[i][j].getForme() != plateau[i][j+1].getForme()) {
						forme = false;
					}
					else compteurForme++;
				}
				
				if(hauteur) {
					if(plateau[i][j].getCaseVide() || plateau[i][j+1].getCaseVide() ||
					plateau[i][j].getHauteur() != plateau[i][j+1].getHauteur()) {
						hauteur = false;
					}
					else compteurHauteur++;
				}
				
				if(pleineCreuse) {
					if(plateau[i][j].getCaseVide() || plateau[i][j+1].getCaseVide() ||
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
					if(plateau[i][j].getCaseVide() || plateau[i+1][j].getCaseVide() ||
					plateau[i][j].getCouleur() != plateau[i+1][j].getCouleur()) {
						couleur = false;
					}
					else compteurCouleur++;
				}
				
				if(forme) {
					if(plateau[i][j].getCaseVide() || plateau[i+1][j].getCaseVide() || 
					plateau[i][j].getForme() != plateau[i+1][j].getForme()) {
						forme = false;
					}
					else compteurForme++;
				}
				
				if(hauteur) {
					if(plateau[i][j].getCaseVide() || plateau[i+1][j].getCaseVide() ||
					plateau[i][j].getHauteur() != plateau[i+1][j].getHauteur()) {
						hauteur = false;
					}
					else compteurHauteur++;
				}
				
				if(pleineCreuse) {
					if(plateau[i][j].getCaseVide() || plateau[i+1][j].getCaseVide() ||
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
	
	public List<Action> actionsPossibles() {
	
		List<Action> actions = new ArrayList<>();

		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau.length; j++) {
				// Si la case est vide on rempli la liste des actions selon les pièces dispo et la position du plateau 
				Piece[][] nouveauPlateau = plateau;
				if(nouveauPlateau[i][j].getCaseVide()) {
					for(Piece p : piecesDisponibles) {
						// p.setCaseVide(false);
						actions.add(new Action(p, new Position(i, j)));
					}
				}
			}
		}
		
		return actions;
	}

	public Partie successeur(Action action) {
		Partie nouvelEtat = this;
        Piece[][] nouveauPlateau = plateau;

		// action.getPiece().setCaseVide();
		nouveauPlateau[action.getI()][action.getJ()] = action.getPiece();
		nouvelEtat.setPlateau(nouveauPlateau);
		nouvelEtat.setTourJ1();
		nouvelEtat.setPiecesDisponibles(action.getPiece());

        return nouvelEtat;
	}

	public Partie defaireAction(Action action) {
		Partie nouvelEtat = this;
		Piece[][] nouveauPlateau = plateau;

		nouveauPlateau[action.getI()][action.getJ()].setCaseVide(true);
		nouvelEtat.setPlateau(plateau);
		nouvelEtat.setTourJ1();

		nouvelEtat.getPiecesDisponibles().add(action.getPiece());

		return nouvelEtat;
	}
	
	public int utilite(Piece piece) {
		//Le joueur 2 est l'ia
		if((troisPiecesMemeColonne(piece) || troisPiecesMemeDiag(piece) || troisPiecesMemeLigne(piece)) && tourJ1) {
			return -2;
		}
		if((troisPiecesMemeColonne(piece) || troisPiecesMemeDiag(piece) || troisPiecesMemeLigne(piece)) && !tourJ1) {
			return 2;
		}
		if(plateauRempli() && !aGagne()) return 0;
		if(tourJ1 && aGagne()) return -3;
		if(!tourJ1 && aGagne()) return 3;
		if(tourJ1) return -1;
		return 1;
	}
	
	public boolean troisPiecesMemeDiag(Piece piece) {
		List<Couleur> couleurs = new ArrayList<>();
		List<Forme> formes = new ArrayList<>();
		List<Hauteur> hauteurs = new ArrayList<>();
		List<PleineOuCreuse> pleinesCreuses = new ArrayList<>();
		
		for(int i=0; i<plateau.length; i++) {
			if(!plateau[i][i].getCaseVide()) {
				couleurs.add(plateau[i][i].getCouleur());
				formes.add(plateau[i][i].getForme());
				hauteurs.add(plateau[i][i].getHauteur());
				pleinesCreuses.add(plateau[i][i].getPleineOuCreuse());
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
		
		for(int i=plateau.length-1, j=0; j<plateau.length; i--, j++) {
			if(!plateau[i][j].getCaseVide()) {
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
	
	public boolean troisPiecesMemeLigne(Piece piece) {
		List<Couleur> couleurs = new ArrayList<>();
		List<Forme> formes = new ArrayList<>();
		List<Hauteur> hauteurs = new ArrayList<>();
		List<PleineOuCreuse> pleinesCreuses = new ArrayList<>();
		
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau.length; j++) {
				if(!plateau[i][j].getCaseVide()) {
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

	public boolean troisPiecesMemeColonne(Piece piece) {
		List<Couleur> couleurs = new ArrayList<>();
		List<Forme> formes = new ArrayList<>();
		List<Hauteur> hauteurs = new ArrayList<>();
		List<PleineOuCreuse> pleinesCreuses = new ArrayList<>();
		
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau.length; j++) {
				if(!plateau[j][i].getCaseVide()) {
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