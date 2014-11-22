//Yuan Yu and Dev Sethi

package battleship;

import java.util.Random;

public class Ocean {
	Ship[][] ships = new Ship[10][10];
	boolean[][] shots = new boolean[10][10];
	int shotsFired;
	int hitCount;
	int shipsSunk;
	int battleships = 1;
	int cruisers = 2;
	int destroyers = 3;
	int submarines = 4;
	int totalShips = battleships + cruisers + destroyers + submarines;

	/**
	 * Ocean constructor Initializes shots fired, hit count, and ships sunk to
	 * 0. Fills ships array i.e. "the ocean" with instances of empty sea Fills
	 * the shots array with false to indicate no shots have been fired
	 */
	public Ocean() {
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ships[i][j] = new EmptySea();
				shots[i][j] = false;
			}
		}
	}

	/**
	 * Called by 'placeAllShipsRandomly()' with a particular type of ship
	 * Randomly picks positions in the ocean and checks if it is a valid
	 * placement for the given ship type When valid location found, calls the
	 * ship's placeAt() method
	 * 
	 * @param ship
	 */
	void placeShipRandomly(Ship ship) {
		Random random = new Random();
		int row;
		int column;
		boolean horizontal;
		boolean flag = true;
		do {
			row = random.nextInt(10);
			column = random.nextInt(10);
			horizontal = random.nextBoolean();
			// System.out.println("Trying " + ship.getShipType() + " at "+ row+
			// ","+column+" "+horizontal);
			if (ship.okToPlaceShipAt(row, column, horizontal, this)) {
				ship.placeShipAt(row, column, horizontal, this);
				flag = false;
				// print();
			}
		} while (flag);

	}

	/**
	 * Places ships randomly. Can specify number of each ship by modifying the
	 * respective variables above.
	 */
	void placeAllShipsRandomly() {
		// Place Battleships
		for (int i = 0; i < battleships; i++) {
			placeShipRandomly(new Battleship());
		}
		// Place Cruisers
		for (int i = 0; i < cruisers; i++) {
			placeShipRandomly(new Cruiser());
		}
		// Place Destroyers
		for (int i = 0; i < destroyers; i++) {
			placeShipRandomly(new Destroyer());
		}
		// place Submarines
		for (int i = 0; i < submarines; i++) {
			placeShipRandomly(new Submarine());
		}
	}

	/**
	 * Determines if an element of the ships array contains a ship or EmptySea
	 * 
	 * @param row
	 * @param column
	 * @return false if EmptySea else true
	 */
	boolean isOccupied(int row, int column) {
		if (ships[row][column].getShipType().equals("EmptySea")) {
			return false;
		} else
			return true;
	}

	/**
	 * Carries out shooting at user specified location Updates shotsFired,
	 * hitCount, and sinkCount based on the contents of the location and state
	 * of any ships If shooting at EmptySea, sets that instance's single-element
	 * hit array to true to "sink" it and prompt it to display '-'
	 * 
	 * @param row
	 * @param column
	 * @return true if a hit was made (i.e. the location is occupied), false
	 *         otherwise
	 */
	boolean shootAt(int row, int column) {
		this.shotsFired++;
		shots[row][column] = true;
		if ((isOccupied(row, column))) {
			System.out.println("Hit!");
			this.hitCount++;
			if (!(ships[row][column].isSunk())) {
				if (!ships[row][column].shootAt(row, column)) {
					shipsSunk++;
					System.out.println(ships[row][column].getShipType()
							+ " sunk!");
					return true;
				}
			}
			if(ships[row][column].isSunk()){
				return false;
			}else{
				return true;
			}
			
		} else {
			ships[row][column].hit[0] = true;
		}
		return false;
	}

	/**
	 * @return number of shots fired
	 */
	int getShotsFired() {
		return this.shotsFired;
	}

	/**
	 * @return number of shots which were hits (even if ship already sunk)
	 */
	int getHitCount() {
		return this.hitCount;
	}

	/**
	 * @return number of ships sunk
	 */
	int getShipsSunk() {
		return this.shipsSunk;
	}

	/**
	 * Checks if all ships have been sunk
	 * 
	 * @return true if all ships sunk, false otherwise
	 */
	boolean isGameOver() {
		if (shipsSunk == totalShips) {
			return true;
		}
		return false;
	}

	/**
	 * Getter for ships array
	 * 
	 * @return ships array
	 */
	Ship[][] getShipArray() {
		return ships;
	}

	/**
	 * Prints the state of the ocean including shot/hit/sunk stats Checks if a
	 * location has been shot at. If so, calls that space's toString() method,
	 * else prints '.'
	 */
	void print() {
		System.out.println("\nShots fired: " + this.shotsFired
				+ "  Hit count: " + this.hitCount + "  Ships sunk: "
				+ this.shipsSunk);
		for (int k = 0; k < 45; k++) {
			System.out.print("_");
		}
		System.out.println("\n 0123456789");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 11; j++) {
				if ((j == 0)) {
					if (i > 0) {
						System.out.print("\n" + i);
					} else {
						System.out.print(i);
					}
				} else {
					if (shots[i][j - 1]) {
						System.out.print(this.ships[i][j - 1].toString());
					} else {
						System.out.print(".");
					}
				}

			}
		}
	}
}
