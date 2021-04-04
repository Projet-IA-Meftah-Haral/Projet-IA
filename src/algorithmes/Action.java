package algorithmes;

import java.util.HashMap;

import caracteristiquesPiece.Position;
import partie.Piece;

/**
* Action d'un joueur (liste de couple pièce/position)
*/
public class Action {
    private HashMap<Piece, Position> action;

    
    public Action() {
        action = new HashMap<Piece, Position>();
    }

    public HashMap<Piece, Position> getAction() {
        return action;
    }
}
