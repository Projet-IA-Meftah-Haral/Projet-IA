package ia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import partie.Partie;
import partie.Piece;

/**
 * Contient les méthodes relatives aux décisions de l'IA
 */
public class IA {
    private int profondeur;
    private Partie partie;
    private Action meilleureAction;
    private Piece piece;
    
    /**
     * Construit un objet IA
     * @param p la partie courante
     * @param prof la profondeur de l'arbre suivant l'algorithme Minimax
     */
    public IA(Partie p, int prof) {
        profondeur = prof;
        partie = p;
        meilleureAction = null;
        piece = null;
    }    

    /**
     * @return la profondeur de l'arbre
     */
    public int getProfondeur() {
        return profondeur;
    }
    
    /**
     * @return la partie courante
     */
    public Partie getPartie() {
        return partie;
    }

    /**
     * Choisit une pièce au hasard parmi les pièces disponibles
     * @return une pièce
     */
    public Piece choixPieceRandom() {
        List<Piece> piecesDispo = partie.getPiecesDisponibles();
        Collections.shuffle(piecesDispo); //Mélange la liste de pièces au hasard

        Piece pieceRandom = piecesDispo.get(0);
        partie.supprimerPiece(pieceRandom);
        System.out.println("L'ordinateur a choisi la pièce " + pieceRandom + ".");

        return pieceRandom;
    }

    /**
     * Dépose la pièce de manière aléatoire, sauf si l'IA peut gagner. Dans ce cas, elle choisit la case qui
     * la fera gagner
     * @param p la pièce que l'adversaire a choisi
     */
    public void deposerPieceRandom(Piece p) {
        List<Action> actions = partie.actionsPossiblesPiece(p);

        for (Action action : actions) {
            Piece piece = action.getPiece();

            partie.successeur(action);

            // L'IA joue la pièce qui la fera gagner
            if (partie.utilite(piece) == 3) {
                partie.annulerAction(action);
                int i = action.getI()+1;
                int j = action.getJ()+1;

                partie.supprimerPiece(piece);
                partie.remplirPlateau(piece, i, j);

                System.out.println("\nL'ordinateur a déposé la pièce à la position ("+i+","+j+").");
                return;
            }

            partie.annulerAction(action);
        }

        Collections.shuffle(actions); //Mélange la liste d'actions au hasard

        Action actionRandom = actions.get(0);
        int i = actionRandom.getI()+1;
        int j = actionRandom.getJ()+1;
        Piece piece = actionRandom.getPiece();

        partie.supprimerPiece(piece);
        partie.remplirPlateau(piece, i, j);
        
        System.out.println("\nL'ordinateur a déposé la pièce à la position ("+i+","+j+").");
    }
    
    /**
    * Permet à l'IA de choisir la pièce que va poser l'humain en utilisant l'algorithme Minimax
    * @return La pièce choisie par l'IA
    */
    public Piece choixPiece() {
        // Algorithme Minimax avec l'élagage Alpha-Beta
        valeurMax(profondeur, -4, 4);
        
        Piece p = meilleureAction.getPiece();

        List<Piece> piecesDispo = partie.getPiecesDisponibles();
        while(piecesDispo.contains(p)) {
            partie.supprimerPiece(p);
        } 

        System.out.println("L'ordinateur a choisi la pièce " + p + ".");

        return p;
    }
    
    /**
     * L'IA dépose la pièce choisie par le joueur humain sur ce qu'elle estime être la meilleure
     * case possible. Cette case est déterminée par l'algorithme Minimax
     * @param p la pièce que l'IA doit déposer
     */
    public void deposerPiece(Piece p) {
        piece = p;
        
        valeurMax(profondeur, -4, 4);
        
        Position pos = meilleureAction.getPosition();
        int i = pos.getI()+1;
        int j = pos.getJ()+1;

        List<Piece> piecesDispo = partie.getPiecesDisponibles();
        while(piecesDispo.contains(p)) {
            partie.supprimerPiece(p);
        } 

        partie.remplirPlateau(p, i, j);
        
        System.out.println();
        System.out.println("L'ordinateur a déposé la pièce à la position ("+i+","+j+").");
    }
    
    /**
     * Permet de maximiser l'utilité pour l'IA en utilisant l'algorithme Minimax
     * @param prof la profondeur de l'arbre
     * @param alpha l'utilité la plus grande
     * @param beta l'utilité la plus faible
     * @return alpha
     */
    public int valeurMax(int prof, int alpha, int beta) {
        
        if (partie.testTerminal() || prof == 0) return partie.utilite(piece);
        
        // C'est le tour de l'IA donc l'humain choisit la pièce
        if(!partie.getTourJ1()) {
            List<Action> actions = partie.actionsPossiblesPiece(piece);
            
            for(Action a : actions) {
                partie.successeur(a);
                int utilite = valeurMin(prof - 1, alpha, beta);
                partie.annulerAction(a);
                
                if (utilite > alpha) {
                    alpha = utilite;
                    if (prof == profondeur) meilleureAction = a;
                }
                
                if (alpha >= beta) return alpha;    
            }
        } 

        // C'est le tour de l'humain donc c'est l'IA qui choisit la pièce
        else {
            List<Action> actions = partie.actionsPossibles();
            
            List<Piece> piecesDefaite = new ArrayList<>();
            
            for (int i=0; i<actions.size(); i++) {
                int utilite;
                
                Action action = actions.get(i);
                piece = action.getPiece();

                // La liste piecesDefaite contient les pièces qui, posées sur une certaine case,
                // peuvent donner une utilité de -3. Dans ce cas, il ne faut pas que ces pièces
                // soient choisies par l'IA. 
                if(!piecesDefaite.contains(piece)) {
                    partie.successeur(action);
                    utilite = valeurMin(prof - 1, alpha, beta);
                    partie.annulerAction(action);
                    
                    // Si on a une utilité de -3, on met la pièce dans piecesDefaite et on redémarre 
                    // la boucle
                    if(utilite == -3) {
                        piecesDefaite.add(piece); 
                        alpha = -4;
                        i = 0;
                    }    
                    
                    if (utilite > alpha) {
                        alpha = utilite;
                        if (prof == profondeur) meilleureAction = action;
                    }
                    
                    if (alpha >= beta) return alpha;
                }   
                
                if(piecesDefaite.size() == partie.getPiecesDisponibles().size()) {
                    meilleureAction = action;
                }
            }
        }    

        return alpha;
    }

    /**
     * Permet de minimiser l'utilité pour l'IA en utilisant l'algorithme Minimax
     * @param prof la profondeur de l'arbre
     * @param alpha l'utilité la plus grande
     * @param beta l'utilité la plus faible
     * @return beta
     */
    public int valeurMin(int prof, int alpha, int beta) {

        if (partie.testTerminal() || prof == 0)
            return partie.utilite(piece);

        if (!partie.getTourJ1()) {
            List<Action> actions = partie.actionsPossiblesPiece(piece);

            for (Action a : actions) {
                partie.successeur(a);
                int utilite = valeurMax(prof - 1, alpha, beta);
                partie.annulerAction(a);

                if (utilite < beta) {
                    beta = utilite;
                    if (prof == profondeur)
                        meilleureAction = a;
                }

                if (beta <= alpha)
                    return beta;
            }
        }

        else {
            List<Action> actions = partie.actionsPossibles();
            
            List<Piece> piecesDefaite = new ArrayList<>();
            
            for (int i=0; i<actions.size(); i++) {
                int utilite;
                
                Action action = actions.get(i);
                piece = action.getPiece();

                if(!piecesDefaite.contains(piece)) {
                    partie.successeur(action);
                    utilite = valeurMax(prof - 1, alpha, beta);
                    partie.annulerAction(action);
                    
                    if(utilite == -3) {
                        piecesDefaite.add(piece); 
                        alpha = -4;
                        i = 0;
                    }    
                    
                    if (utilite < beta) {
                        beta = utilite;
                        if (prof == profondeur) meilleureAction = action;
                    }
                    
                    if (beta <= alpha) return beta;
                }   
                
                if(piecesDefaite.size() == partie.getPiecesDisponibles().size()) {
                    meilleureAction = action;
                }
            }
        }    

        return beta;
    }
}