package ia;

import java.util.List;

import partie.Partie;
import partie.Piece;

public class IA {
    private int profondeur;
    private Partie partie;
    private Action meilleureAction;
    private Piece piece;

    public IA(Partie p, int prof) {
        profondeur = prof;
        partie = p;
        meilleureAction = null;
        piece = null;
    }    

    public Partie getPartie() {
        return partie;
    }

    /**
     * Permet à l'IA de choisir la pièce que va poser l'humain en utilisant l'algorithme Minimax
     * et l'élagage Alpha-Beta
     * @return La pièce choisie par l'IA
     */
    public Piece choixPiece() {
        // Algorithme Minimax avec l'élagage Alpha-Beta
        valeurMin(profondeur, -4, 4);

        Piece p = meilleureAction.getPiece();
        partie.supprimerPiece(p);
        System.out.println("L'ordinateur a choisi la pièce " + p + ".");

        return p;
    }

    public void deposerPiece(Piece p) {
        piece = p;

        valeurMax(profondeur, -4, 4);

        Position pos = meilleureAction.getPosition();
        int i = pos.getI()+1;
        int j = pos.getJ()+1;

        partie.remplirPlateau(p, i, j);

        System.out.println();
        System.out.println("L'ordinateur a déposé la pièce à la position ("+i+","+j+").");
    }

    public int valeurMax(int prof, int alpha, int beta) {

        if (partie.testTerminal() || prof == 0) return partie.utilite(piece);

        if(!partie.getTourJ1()) {
            List<Action> actions = partie.actionsPossiblesPiece(piece);

            for(Action a : actions) {
                partie.successeur(a);
                int utilite = valeurMin(prof - 1, alpha, beta);
                partie.defaireAction(a);
    
                if (utilite > alpha) {
                    alpha = utilite;
                    if (prof == profondeur) meilleureAction = a;
                }
    
                if (alpha >= beta) return alpha;    
            }
        } 
        
        else {
            List<Action> actions = partie.actionsPossibles();

            for (Action a : actions) {
                piece = a.getPiece();
    
                partie.successeur(a);
                int utilite = valeurMin(prof - 1, alpha, beta);
                partie.defaireAction(a);
    
                if (utilite > alpha) {
                    alpha = utilite;
                    if (prof == profondeur) meilleureAction = a;
                }
    
                if (alpha >= beta) return alpha;
            }
        }

        return alpha;
    }

    public int valeurMin(int prof, int alpha, int beta) {

        if (partie.testTerminal() || prof == 0) return partie.utilite(piece);

        if (!partie.getTourJ1()) {
            List<Action> actions = partie.actionsPossiblesPiece(piece);

            for(Action a : actions) {
                partie.successeur(a);
                int utilite = valeurMax(prof - 1, alpha, beta);
                partie.defaireAction(a);

                if (utilite < beta) {
                    beta = utilite;
                    if (prof == profondeur) meilleureAction = a;
                }

                if (beta <= alpha) return beta;
            }    
        } 
        
        else {
            List<Action> actions = partie.actionsPossibles();

            for(Action a : actions) {
                piece = a.getPiece();

                partie.successeur(a);
                int utilite = valeurMax(prof - 1, alpha, beta);
                partie.defaireAction(a);

                if (utilite < beta) {
                    beta = utilite;
                    if (prof == profondeur) meilleureAction = a;
                }

                if (beta <= alpha) return beta;
            }    
        }

        return beta;
    }
}