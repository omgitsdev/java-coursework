//Yuan Yu and Dev Sethi

package battleship;

public class EmptySea extends Ship {

	/**
	 * Creates an EmptySea space of length 1. 
	 * This object will return - instead of x when "sunk"
	 */
	public EmptySea() {
		length = 1;
		noHits();
	}

	@Override
	boolean shootAt(int row, int column) {
		return false;
	}

	@Override
	boolean isSunk() {
		return false;
	}

	@Override
	String getShipType() {
		return new String("EmptySea");
	}

	@Override
	public String toString() {
		if (hit[0]) {
			return "-";
		}
		return ".";
	}

}
