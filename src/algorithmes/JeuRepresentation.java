package algorithmes;

import java.util.ArrayList;
import java.util.List;

import caracteristiquesPiece.Position;
import partie.Joueur;
import partie.Piece;
import partie.Plateau;

/**
 * Représentation d'un jeu 
 */
public class JeuRepresentation {
    private static Plateau etat_init = new Plateau();
    private static Joueur humain = new Joueur("HUMAIN");
    private static Joueur ia = new Joueur("IA");

    /**
     * Accesseur de l'état initiale
     * @return retourne un plateau vide initiale
     */
    public static Plateau getEtat_init() {
        return etat_init;
    }

    /**
     * Accesseur du joueur correspondant à l'HUMAIN
     * @return retourne le joueur "humain"
     */
    public static Joueur getHumain() {
        return humain;
    }

    /**
     * Accesseur du joueur correspondant à l'IA
     * @return retourne le joueur "IA"
     */
    public static Joueur getIa() {
        return ia;
    }

    /**
     * Modificateur de l'humain.
     * ATTENTION ! A N'UTILISER QU'EN DÉBUT DE PARTIE ! (lors de la demande du nom du joueur)
     * @param humain L'instance du joueur (avec son vrai nom)
     */
    public static void setHumain(Joueur humain) {
        JeuRepresentation.humain = humain;
    }

    /**
     * Défini quel joueur doit jouer lors de l'état donné 
     * @param etat état du plateau à un instant T
     * @return Si "tourHumain" vrai retourne l'humain, sinon retourne l'ia
     */
    public static Joueur joueur(Plateau etat) {
        if (etat.getTourHumain()) {
            return humain;
        } else {
            return ia;
        }
    }
    
    /**
     * Retourne les actions possibles selon l'état du plateau
     * @param etat état du plateau à un instant T
     * @return retourne une liste d'actions (liste de couple pièce/position)
     */
    public static List<Action> actions(Plateau etat) {
        List<Action> actions = new ArrayList<>();
        for (int i = 0; i < etat.getPlateau().length; i++) {
            for (int j = 0; j < etat.getPlateau().length; j++) {
                // Si la case est vide on rempli la liste des actions selon les pièces dispo et la position du plateau 
                Piece [][] plateau = etat.getPlateau();
                if (plateau[i][j].toString().equals("vide")) {
                    for (Piece pieceDispo : etat.getListPiecesDispo()) {
                        actions.add(new Action(pieceDispo, new Position(i, j)));
                    }
                }
            }
        }
        return actions;
    }

    /**
     * Modèle de transition, qui défini le résultat d'une action selon l'état du plateau
     * @param etat état du plateau à un instant T
     * @param action une action (consituté d'une pièce et de sa position où l'on souhaite la déposer)
     * @return retourne un plateau après l'action
     */
    public static Plateau resultat(Plateau etat, Action action) {
        Plateau newEtat = etat;
        Piece [][] newPlateau = etat.getPlateau();
        if (newPlateau[action.getPosition().getI()][action.getPosition().getJ()].toString().equals("vide") && newEtat.getListPiecesDispo().contains(action.getPiece())) {
            newPlateau[action.getPosition().getI()][action.getPosition().getJ()] = action.getPiece();
            newEtat.setPlateau(newPlateau);
        } else {
            System.out.println("Impossible d'effectuer cette action car la case ["+action.getPosition().getI()+"] ["+action.getPosition().getJ()+"] est déjà rempli");
            System.out.println("Ou alors la pièce n'est plus disponible");
            return etat;
        }

        return newEtat;
    }

    /**
     * test de terminaison, si "vrai" alors "Plateau etat" est un état terminal
     * @param etat état du plateau à un instant T
     * @return retourne vrai si il y a un gagnant ou que le plateau est rempli, faux sinon
     */
    public static boolean test_terminal(Plateau etat) {
        return etat.aGagne() || etat.plateauRempli();
    }

    /**
     * Fonction  d'utilité associant une valeur numérique à chaque état donné pour un joueur
     * @param etat état du plateau à un instant T
     * @param joueur un joueur
     * @return retourne l'utilité de l'état
     */
    public static int utilite(Plateau etat, Joueur joueur) {
        return 0;
    }
}
