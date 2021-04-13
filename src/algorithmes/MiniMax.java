package algorithmes;

import java.util.List;

import partie.Joueur;
import partie.Plateau;

/**
 * Algorithme Minimax avec Alpha-Beta intégré
 */
public class MiniMax 
{
    private Joueur max, min;
    private int alpha, beta, profondeur;
    private Action meilleureAction;

    public MiniMax(Joueur max, Joueur min, int p) 
    {
        this.max = max;
        this.min = min;
        alpha = 0;
        beta = 0;
        profondeur = p;
        meilleureAction = null;
    }

    public Action deciderAction(Plateau etat) 
    {
        valeurMax(etat, profondeur);
        return meilleureAction;
    }

    /**
     * 
     * @param etat
     * @param prof
     * @param max
     * @param min
     * @return
     */
    public int valeurMax(Plateau etat, int prof) 
    {
        if (JeuRepresentation.test_terminal(etat)) 
        {
            meilleureAction = JeuRepresentation.actions(etat).get(0);
            return JeuRepresentation.utilite(etat, max);
        }
        
        List<Action> actions = JeuRepresentation.actions(etat); 
        int v = -100000;

        for(Action a : actions) 
        {
            Plateau successeur = JeuRepresentation.resultat(etat, a);
            v = valeurMin(successeur, prof-1); 

            if(v > alpha) 
            {
                alpha = v;
                if(prof == profondeur) meilleureAction = a;
            }    

            if(alpha >= beta) return alpha;
        }

        return alpha;
    }

    /**
     * 
     * @param etat
     * @param profondeur
     * @param max
     * @param min
     * @return
     */
    public int valeurMin(Plateau etat, int prof) 
    {
        if (JeuRepresentation.test_terminal(etat)) {
            return JeuRepresentation.utilite(etat, min);
        }

        List<Action> actions = JeuRepresentation.actions(etat); 
        int v = -100000;

        for(Action a : actions) 
        {
            Plateau successeur = JeuRepresentation.resultat(etat, a);
            v = valeurMax(successeur, prof-1); 

            if(v <= beta) {
                beta = v;
            }

            if(beta <= alpha) return beta;  
        }    

        return beta;
    }
}