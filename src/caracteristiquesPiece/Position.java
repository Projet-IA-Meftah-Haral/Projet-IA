package caracteristiquesPiece;

public class Position {
	private int i, j;
	
	public Position(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}

	@Override
	public boolean equals(Object obj) {
		Position p = (Position) obj;

		if (this == p) {
			return true;
		}

		return ((this.i==p.i) && (this.j==p.j));
	}
}
