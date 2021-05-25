package ia;

import partie.Piece;

/**
 * Permet d'appliquer des méthodes à un objet Action
 */
public class Action {
    private Piece piece;
    private Position pos;

    /**
     * Construit un objet Action
     * @param p la pièce 
     * @param pos la position, la case
     */
    public Action(Piece p, Position pos) {
        piece = p;
        this.pos = pos;
    }

    /**
     * @return la pièce d'une action
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * @return la i-ème ligne du plateau
     */
    public int getI() {
        return pos.getI();
    }

    /**
     * @return la j-ème colonne du plateau
     */
    public int getJ() {
        return pos.getJ();
    }

    /**
     * @return la postion, la case d'une action
     */
    public Position getPosition() {
        return pos;
    }
}