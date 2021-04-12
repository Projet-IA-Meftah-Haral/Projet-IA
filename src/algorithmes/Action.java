package algorithmes;

import caracteristiquesPiece.Position;
import partie.Piece;

/**
* Action d'un joueur (liste de couple pièce/position)
*/
public class Action {
    private Piece piece;
    private Position position;
    
    
    public Action(Piece p, Position pos) {
        piece = p;
        position = pos;
    }
    
    public Piece getPiece() {
        return piece;
    }
    
    public Position getPosition() {
        return position;
    }
}
