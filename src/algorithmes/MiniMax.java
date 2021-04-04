package algorithmes;

import partie.Joueur;
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
     * @return retourne une action (liste de couple pièce/position)
     */
    public Action action(Plateau etat) {
        return null;
    }

    /**
     * Modèle de transition, qui défini le résultat d'une action selon l'état du plateau
     * @param etat état du plateau à un instant T
     * @param action action d'un joueur
     * @return retourne un plateau après l'action
     */
    public Plateau resultat(Plateau etat, Action action) {
        return null;
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
