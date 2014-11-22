//Yuan Yu and Dev Sethi

package battleship;

public class Submarine extends Ship {

	/**
	 * Constructor, submarine has length 1
	 */
	public Submarine() {
		length = 1;
		noHits();
	}

	@Override
	int getLength() {
		return length;
	}

	@Override
	String getShipType() {
		return new String("submarine");
	}

}
