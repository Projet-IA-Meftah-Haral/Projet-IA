package ia;

import java.util.ArrayList;
import java.util.Collections;
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
     * Choisit une pièce au hasard
     * @return retourne une pièce
     */
    public Piece choixPieceRandom() {
        List<Piece> pieces_dispos = partie.getPiecesDisponibles();
        Collections.shuffle(pieces_dispos); //Mélange la liste de pièces au hasard

        Piece piece_random = pieces_dispos.get(0);
        System.out.println("L'ordinateur a choisi la pièce " + piece_random + ".");

        return piece_random;
    }

    /**
     * Dépose la pièce de manière aléatoire (sauf si l'IA peut gagner)
     * @param p la pièce que l'adversaire donne
     */
    public void deposerPieceRandom(Piece p) {
        List<Action> actions = partie.actionsPossiblesPiece(p);

        for (Action action : actions) {
            if (partie.utilite(action.getPiece()) == 3) {
                int i = action.getI()+1;
                int j = action.getJ()+1;

                partie.supprimerPiece(action.getPiece());
                partie.remplirPlateau(action.getPiece(), i, j);

                System.out.println("\nL'ordinateur a déposé la pièce à la position ("+i+","+j+").");
                return;
            }
        }

        Collections.shuffle(actions); //Mélange la liste d'actions au hasard

        Action action_random = actions.get(0);
        int i = action_random.getI()+1;
        int j = action_random.getJ()+1;

        partie.supprimerPiece(action_random.getPiece());
        partie.remplirPlateau(action_random.getPiece(), i, j);
        
        System.out.println("\nL'ordinateur a déposé la pièce à la position ("+i+","+j+").");
    }
    
    /**
    * Permet à l'IA de choisir la pièce que va poser l'humain en utilisant l'algorithme Minimax
    * et l'élagage Alpha-Beta
    * @return La pièce choisie par l'IA
    */
    public Piece choixPiece() {
        // Algorithme Minimax avec l'élagage Alpha-Beta
        valeurMax(profondeur, -4, 4);
        
        Piece p = meilleureAction.getPiece();
        // System.out.println(partie.getPiecesDisponibles().size());

        List<Piece> piecesDispo = partie.getPiecesDisponibles();
        while(piecesDispo.contains(p)) {
            partie.supprimerPiece(p);
        } 
        // System.out.println(partie.getPiecesDisponibles().size());
        System.out.println("L'ordinateur a choisi la pièce " + p + ".");
        // System.out.println(partie.getPiecesDisponibles().size());
        
        return p;
    }
    
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
    
    public int valeurMax(int prof, int alpha, int beta) {
        
        if (partie.testTerminal() || prof == 0) return partie.utilite(piece);
        
        if(!partie.getTourJ1()) {
            List<Action> actions = partie.actionsPossiblesPiece(piece);
            
            for(Action a : actions) {
                
                partie.successeur(a);
                // System.out.println(partie.getPiecesDisponibles().size());
                int utilite = valeurMin(prof - 1, alpha, beta);
                partie.defaireAction(a);
                // System.out.println(partie.getPiecesDisponibles().size());
                
                if (utilite > alpha) {
                    alpha = utilite;
                    if (prof == profondeur) meilleureAction = a;
                }
                
                if (alpha >= beta) return alpha;    
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
                    // System.out.println(partie.getPiecesDisponibles().size());
                    utilite = valeurMin(prof - 1, alpha, beta);
                    partie.defaireAction(action);
                    // System.out.println(partie.getPiecesDisponibles().size());
                    
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

    public int valeurMin(int prof, int alpha, int beta) {

        if (partie.testTerminal() || prof == 0)
            return partie.utilite(piece);

        if (!partie.getTourJ1()) {
            List<Action> actions = partie.actionsPossiblesPiece(piece);

            for (Action a : actions) {
                partie.successeur(a);
                // System.out.println(partie.getPiecesDisponibles().size());
                int utilite = valeurMax(prof - 1, alpha, beta);
                partie.defaireAction(a);
                // System.out.println(partie.getPiecesDisponibles().size());

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
                    // System.out.println(partie.getPiecesDisponibles().size());
                    utilite = valeurMax(prof - 1, alpha, beta);
                    partie.defaireAction(action);
                    // System.out.println(partie.getPiecesDisponibles().size());
                    
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