//Yuan Yu and Dev Sethi

package battleship;

public class Battleship extends Ship {

	/**
	 * Constructor, battleship has length 4
	 */
	public Battleship() {
		length = 4;
		noHits();
	}

	@Override
	int getLength() {
		return length;
	}

	@Override
	String getShipType() {
		return new String("battleship");
	}

}