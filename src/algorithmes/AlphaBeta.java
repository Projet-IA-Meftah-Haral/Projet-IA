package algorithmes;

import java.util.ArrayList;
import java.util.List;

import partie.Joueur;
import partie.Plateau;

public class AlphaBeta {
    private int alpha, beta;

    public AlphaBeta() {
        alpha = 0;
        beta = 0;
    }

    public Action deciderAction(Plateau etat) {
        return null;
    }

    public int valeurMax(Plateau etat, int profondeur, Joueur max, Joueur min) {
        if (JeuRepresentation.test_terminal(etat)) {
            return JeuRepresentation.utilite(etat, max);
        }

        List<Action> actions = JeuRepresentation.actions(etat); 
        int utilite = 0;

        for(Action a : actions) {
            Plateau successeur = JeuRepresentation.resultat(etat, a);
            utilite = valeurMin(successeur, profondeur-1, max, min);
        }

        return alpha;
    }

    public int valeurMin(Plateau etat, int profondeur, Joueur max, Joueur min) {
        return 0;
    }

    // public int valeurMax(Plateau etat) {
    // if(JeuRepresentation.test_terminal(etat)) {
    // return JeuRepresentation.utilite(etat, JeuRepresentation.joueur(etat));
    // }

    // int v = -100000;

    // List<Plateau> successeurs = new ArrayList<>();
    // for(Action a : JeuRepresentation.actions(etat)) {
    // successeurs.add(JeuRepresentation.resultat(etat, a));
    // }

    // Joueur joueur;
    // if(etat.getTourHumain()) joueur = JeuRepresentation.getHumain();
    // else joueur = JeuRepresentation.getIa();

    // for(Plateau s : successeurs) {
    // if(v < valeurMin(s)) {
    // v = JeuRepresentation.utilite(s, joueur);
    // }
    // if(v >= beta) return v;
    // if(alpha < v) alpha = v;
    // }

    // return v;
    // }

    // public int valeurMin(Plateau etat) {
    // if(JeuRepresentation.test_terminal(etat)) {
    // return JeuRepresentation.utilite(etat, JeuRepresentation.joueur(etat));
    // }

    // int v = -100000;

    // List<Plateau> successeurs = new ArrayList<>();
    // for(Action a : JeuRepresentation.actions(etat)) {
    // successeurs.add(JeuRepresentation.resultat(etat, a));
    // }

    // Joueur joueur;
    // if(etat.getTourHumain()) joueur = JeuRepresentation.getHumain();
    // else joueur = JeuRepresentation.getIa();

    // for(Plateau s : successeurs) {
    // if(v < valeurMax(s)) {
    // v = JeuRepresentation.utilite(s, joueur);
    // }
    // if(v >= alpha) return v;
    // if(beta < v) beta = v;
    // }

    // return v;
    // }
}