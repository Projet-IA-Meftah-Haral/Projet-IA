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
    }
    
    public Joueur getGagnant() {
    	return gagnant;
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
    	boolean aGagne = true;
    	
    	for(int i=0; i<plateau.length-1; i++) {
    		for(int j=i+1; j<plateau.length; j++) {
    			if(!plateau[i][i].caracteristiqueEnCommun(plateau[j][j])) {
    				aGagne = false;
    				break;
    			}
    		}
    		if(!aGagne) break;
    	}
    	
    	if(!aGagne) {
    		for(int i=plateau.length-1, j=0; j<plateau.length; i--, j++) {
        		for(int k=i-1, l=j+1; l<plateau.length; k--, l++) {
        			if(!plateau[i][j].caracteristiqueEnCommun(plateau[k][l])) return false;
        		}
        	}
    	}
    	
    	return true;
    }
    
    
    /**
     * Vérifie si 4 pièce ayant une caractéristique commune sont alignées en ligne. Cela revient
     * à vérifier si la partie a été remportée par l'un des deux joueurs 
     * @return true si un joueur a remporté la partie, false sinon
     */
    public boolean victoireLigne() {
   
    	for(int i=0; i<plateau.length; i++) {
    		
    		int compteur = 0;
    		
    		for(int j=0; j<plateau.length-1; j++) {
    			boolean ligneParcourue = true;
    			
    			for(int k=j+1; k<plateau.length; k++) {
    				if(!plateau[i][j].caracteristiqueEnCommun(plateau[i][k])) {
    					ligneParcourue = false;
    					break;
    				}
    			}
    			
    			if(!ligneParcourue) break;
    			else compteur++;
    			
    			if(compteur == plateau.length-1) return true;
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
    	
    	for(int j=0; j<plateau.length; j++) {
    		
    		int compteur = 0;
    		
    		for(int i=0; i<plateau.length-1; i++) {
    			boolean ligneParcourue = true;
    			
    			for(int k=i+1; k<plateau.length; k++) {
    				if(!plateau[i][j].caracteristiqueEnCommun(plateau[k][j])) {
    					ligneParcourue = false;
    					break;
    				}
    			}
    			
    			if(!ligneParcourue) break;
    			else compteur++;
    			
    			if(compteur == plateau.length-1) return true;
    		}
    	}
    	
    	return false;
    }
}