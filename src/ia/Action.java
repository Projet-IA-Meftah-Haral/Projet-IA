package ia;

import partie.Piece;

public class Action {
    private Piece piece;
    private Position pos;

    public Action(Piece p, Position pos) {
        piece = p;
        this.pos = pos;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getI() {
        return pos.getI();
    }

    public int getJ() {
        return pos.getJ();
    }

    public Position getPosition() {
        return pos;
    }
}
