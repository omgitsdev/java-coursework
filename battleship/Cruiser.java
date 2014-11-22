//Yuan Yu and Dev Sethi

package battleship;

public class Cruiser extends Ship {

	/**
	 * Constructor, cruiser has length 3
	 */
	public Cruiser() {
		length = 3;
		noHits();
	}

	@Override
	int getLength() {
		return length;
	}

	@Override
	String getShipType() {
		return new String("cruiser");
	}

}
