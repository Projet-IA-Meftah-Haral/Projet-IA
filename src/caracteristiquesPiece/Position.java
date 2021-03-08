package caracteristiquesPiece;

public class Position {
	private int x, y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		Position p = (Position) obj;

		if (this == p) {
			return true;
		}

		return ((this.x==p.x) && (this.y==p.y));
	}
}
