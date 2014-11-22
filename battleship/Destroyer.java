//Yuan Yu and Dev Sethi

package battleship;

public class Destroyer extends Ship {

	/**
	 * Constructor, destroyer has length 2
	 */
	public Destroyer() {
		length = 2;
		noHits();
	}

	@Override
	int getLength() {
		return length;
	}

	@Override
	String getShipType() {
		return new String("destroyer");
	}

}
