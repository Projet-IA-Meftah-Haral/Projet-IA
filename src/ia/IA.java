package ia;

import java.util.ArrayList;
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
        valeurMax(profondeur, -4, 4);
        
        Piece p = meilleureAction.getPiece();
        // System.out.println(partie.getPiecesDisponibles().size());
        partie.supprimerPiece(p);
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
        
        partie.supprimerPiece(p);
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
                    utilite = valeurMax(prof - 1, alpha, beta);
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
        
        // else {
        //     List<Action> actions = partie.actionsPossibles();
            
        //     List<Piece> piecesDefaite = new ArrayList<>();
            
        //     for (Action a : actions) {
        //         int utilite;
                
        //         if(!piecesDefaite.contains(piece)) {
        //             piece = a.getPiece();
                    
        //             partie.successeur(a);
        //             // System.out.println(partie.getPiecesDisponibles().size());
        //             utilite = valeurMax(prof - 1, alpha, beta);
        //             partie.defaireAction(a);
        //             // System.out.println(partie.getPiecesDisponibles().size());
                    
        //             if(utilite == -3) {
        //                 piecesDefaite.add(piece); 
        //                 if(meilleureAction.getPiece() == piece) meilleureAction = null;
        //             }    
                    
        //             if (utilite > alpha) {
        //                 alpha = utilite;
        //                 if (prof == profondeur) meilleureAction = a;
        //             }
                    
        //             if (alpha >= beta) return beta;
        //         }   
                
        //         if(piecesDefaite.size() == partie.getPiecesDisponibles().size()) {
        //             meilleureAction = a;
        //         }
        //     }    
        // }

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

        // else {
        //     List<Action> actions = partie.actionsPossibles();
            
        //     List<Piece> piecesDefaite = new ArrayList<>();
            
        //     for (Action a : actions) {
        //         int utilite;
                
        //         if(!piecesDefaite.contains(piece)) {
        //             piece = a.getPiece();
                    
        //             partie.successeur(a);
        //             // System.out.println(partie.getPiecesDisponibles().size());
        //             utilite = valeurMax(prof - 1, alpha, beta);
        //             partie.defaireAction(a);
        //             // System.out.println(partie.getPiecesDisponibles().size());
                    
        //             if(utilite == -3) {
        //                 piecesDefaite.add(piece); 
        //                 if(meilleureAction.getPiece() == piece) meilleureAction = null;
        //             }    
                    
        //             if (utilite < beta) {
        //                 beta = utilite;
        //                 if (prof == profondeur) meilleureAction = a;
        //             }
                    
        //             if (beta <= alpha) return beta;
        //         }   
                
        //         if(piecesDefaite.size() == partie.getPiecesDisponibles().size()) {
        //             meilleureAction = a;
        //         }
        //     }
        // }

        return beta;
    }
}