package ia;

import partie.Partie;
import partie.Piece;

public class IA {
    private int profondeur;
    private Partie partie;
    private Action meilleureAction;
    private Piece piece;
    private int compteur;

    public IA(int prof, Partie p) {
        compteur = 0;
        profondeur = prof;
        partie = p;
        meilleureAction = null;
        piece = null;
    }

    public Piece minimaxAlphaBetaChoixPiece() {
        valeurMin(partie, profondeur, -4, 3);
        return meilleureAction.getPiece();
    }

    public Position minimaxAlphaBetaDeposerPiece() {
        valeurMax(partie, profondeur, -4, 3);
        return meilleureAction.getPosition();
    }

    public int valeurMax(Partie p, int prof, int alpha, int beta) {

        if (partie.testTerminal() || profondeur == 0 || compteur==100)
            return partie.utilite(piece);

        for (Action a : partie.actionsPossibles()) {
            compteur++;
            System.out.println(compteur);

            if (partie.getTourJ1()) {
                piece = a.getPiece();
            }

            int utilite = valeurMin(p.resultat(a), prof - 1, alpha, beta);

            if (utilite > alpha) {
                alpha = utilite;
                if (prof == profondeur)
                    meilleureAction = a;
            }

            if (alpha >= beta)
                return alpha;
        }

        return alpha;

    }

    public int valeurMin(Partie p, int prof, int alpha, int beta) {

        if (partie.testTerminal() || profondeur == 0 || compteur==100) 
            return partie.utilite(piece);

        for (Action a : partie.actionsPossibles()) {
            compteur++;
            System.out.println(compteur);

            if (partie.getTourJ1()) {
                piece = a.getPiece();
            }

            int utilite = valeurMax(p.resultat(a), prof - 1, alpha, beta);

            if (utilite <= beta) {
                beta = utilite;
                if (prof == profondeur)
                    meilleureAction = a;
            }

            if (beta <= alpha)
                return beta;
        }

        return beta;
    }

    public void deposerPiece(Piece p) {
        piece = p;
        Piece[][] nouveauPlateau = partie.getPlateau();
        Position pos = minimaxAlphaBetaDeposerPiece();
        int i = pos.getI();
        int j = pos.getJ();
        nouveauPlateau[i][j] = piece;
    }
}
