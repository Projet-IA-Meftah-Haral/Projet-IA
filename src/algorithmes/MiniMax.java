package algorithmes;

import caracteristiquesPiece.Position;
import partie.Joueur;
import partie.Piece;
import partie.Plateau;

/**
 * Algorithme qui choisi le coup qui mène vers l'état qui a la meilleure valeur minimax
 */
public class MiniMax {
    private Plateau etat_init;
    private Joueur ia; //Correspond au joueur "MAX"
    
    public MiniMax(Plateau etat_init) {
        this.etat_init = etat_init;
        ia = new Joueur("IA");
    }

    /**
     * Défini quel joueur doit jouer lors de l'état donné 
     * @param etat état du plateau à un instant T
     * @return retourne le joueur qui joue
     */
    public Joueur joueur(Plateau etat) {
        return null;
    }
    
    /**
     * Retourne les actions possibles selon l'état du plateau
     * @param etat état du plateau à un instant T
     * @return retourne une liste d'actions (liste de couple pièce/position)
     */
    public Action action(Plateau etat) {
        Action actions = new Action();
        for (int i = 0; i < etat.getPlateau().length; i++) {
            for (int j = 0; j < etat.getPlateau().length; j++) {
                // Si la case est vide on rempli la liste des actions selon les pièces dispo et la position du plateau 
                Piece [][] plateau = etat.getPlateau();
                if (plateau[i][j].toString().equals("vide")) {
                    for (Piece pieceDispo : etat.getListPiecesDispo()) {
                        actions.getAction().put(pieceDispo, new Position(i, j));
                    }
                }
            }
        }
        return actions;
    }

    /**
     * Modèle de transition, qui défini le résultat d'une action selon l'état du plateau
     * @param etat état du plateau à un instant T
     * @param action action d'un joueur
     * @return retourne un plateau après l'action
     */
    public Plateau resultat(Plateau etat, Piece piece, Position position) {
        Plateau newEtat = etat;
        Piece [][] newPlateau = etat.getPlateau();
        if (newPlateau[position.getI()][position.getJ()].toString().equals("vide")) {
            newPlateau[position.getI()][position.getJ()] = piece;
            newEtat.setPlateau(newPlateau);
        } else {
            System.out.println("Impossible de changer la valeur car la case ["+position.getI()+"] ["+position.getJ()+"] est déjà rempli");
        }

        return newEtat;
    }

    /**
     * test de terminaison, si "vrai" alors "Plateau etat" est un état terminal
     * @param etat état du plateau à un instant T
     * @return retourne vrai si il y a un gagnant ou que le plateau est rempli, faux sinon
     */
    public boolean test_terminal(Plateau etat) {
        return etat.aGagne() || etat.plateauRempli();
    }

    /**
     * Fonction  d'utilité associant une valeur numérique à chaque état donné pour un joueur
     * @param etat état du plateau à un instant T
     * @param joueur un joueur
     * @return retourne l'utilité de l'état
     */
    public int utilite(Plateau etat, Joueur joueur) {
        return 0;
    }
}
