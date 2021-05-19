package ia;

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

    /**
     * Permet à l'IA de choisir la pièce que va poser l'humain en utilisant l'algorithme Minimax
     * et l'élagage Alpha-Beta
     * @return La pièce choisie par l'IA
     */
    public Piece choixPiece() {
        // Algorithme Minimax avec l'élagage Alpha-Beta
        valeurMin(profondeur, -4, 3);

        Piece p = meilleureAction.getPiece();
        p.setCaseVide(false);
        // p.setCaseVide(false);
        partie.setPiecesDisponibles(p);
        System.out.println("L'ordinateur a choisi la pièce " + p + ".");
        // partie.affichagePlateau();

        return p;
    }

    public void deposerPiece(Piece p) {
        valeurMax(profondeur, -4, 3);

        // piece = p;
        // piece.setCaseVide(false);
        
        Position pos = meilleureAction.getPosition();
        int i = pos.getI();
        int j = pos.getJ();
        partie.remplirPlateau(p, i+1, j+1);

        System.out.println();
        System.out.println("L'ordinateur a déposé la pièce à la position ("+(i+1)+","+(j+1)+").");
    }

    // public Action minimaxAlphaBeta() {
    //     valeurMax(profondeur, -4, 3);
    //     meilleureAction.getPiece().setCaseVide(false);
    //     return meilleureAction;
    // }

    public int valeurMax(int prof, int alpha, int beta) {

        if (partie.testTerminal() || prof == 0) return partie.utilite(piece);

        for (Action a : partie.actionsPossibles()) {
            // a.getPiece().setCaseVide(false);
            // System.out.println(a.getPiece() + " " + a.getI() + a.getJ()); 

            if (partie.getTourJ1()) {
                piece = a.getPiece();
                // piece.setCaseVide(false);
            }    

            partie = partie.successeur(a);
            int utilite = valeurMax(prof - 1, alpha, beta);
            partie.defaireAction(a);

            if (utilite > alpha) {
                alpha = utilite;
                if (prof == profondeur) meilleureAction = a;
            }

            if (alpha >= beta) return alpha;
        }

        return alpha;
    }

    public int valeurMin(int prof, int alpha, int beta) {

        if (partie.testTerminal() || prof == 0) return partie.utilite(piece);

        for (Action a : partie.actionsPossibles()) {
            // a.getPiece().setCaseVide(false);
            // System.out.println(a.getPiece() + " " + a.getI() + a.getJ()); 

            if (partie.getTourJ1()) {
                piece = a.getPiece();
                // piece.setCaseVide(false);
            }    

            partie = partie.successeur(a);
            int utilite = valeurMax(prof - 1, alpha, beta);
            partie.defaireAction(a);

            if (utilite < beta) {
                beta = utilite;
                if (prof == profondeur) meilleureAction = a;
            }

            if (beta <= alpha) return beta;
        }

        return beta;
    }
}
