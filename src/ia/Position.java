package ia;

/**
 * Permet d'appliquer des méthodes à un objet Position
 */
public class Position {
    private int i, j;

    /**
     * Construit un objet Position
     * @param x la x-ème ligne du plateau
     * @param y la y-ème colonne du plateau
     */
    public Position(int x, int y) {
        i = x;
        j = y;
    }

    /**
     * @return la i-ème ligne du plateau
     */
    public int getI() {
        return i;
    }

    /**
     * @return la j-ème colonne du plateau
     */
    public int getJ() {
        return j;
    }
}
