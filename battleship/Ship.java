//Yuan Yu and Dev Sethi

package battleship;

public class Ship {

	int bowRow;
	int bowColumn;
	int length;
	boolean horizontal;
	boolean[] hit = new boolean[4];

	/**
	 * placeholder to be overriden by subclasses
	 * 
	 * @return 0
	 */
	int getLength() {
		return 0;
	}

	// Getters
	/**
	 * Returns row containing ship's bow
	 * 
	 * @return bowRow
	 */
	int getBowRow() {
		return this.bowRow;
	}

	/**
	 * Returns column containing ship's bow
	 * 
	 * @return bowColumn
	 */
	int getBowColumn() {
		return this.bowColumn;
	}

	/**
	 * @return true if horizontal, false if vertical
	 */
	boolean isHorizontal() {
		return this.horizontal;
	}

	// Setters
	/**
	 * Setter for bowRow
	 * 
	 * @param row
	 */
	void setBowRow(int row) {
		this.bowRow = row;
	}

	/**
	 * Setter for bowColumn
	 * 
	 * @param column
	 */
	void setBowColumn(int column) {
		this.bowColumn = column;
	}

	/**
	 * Setter for horizontal
	 * 
	 * @param horizontal
	 */
	void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * Will be overriden by all subclasses, this is essential
	 * 
	 * @return shipt type
	 */
	String getShipType() {
		String x = new String("x");
		return x;
	}

	/**
	 * Determines legality of ship placement Checks to ensure ship is completely
	 * on the board and not adjacent to other ships
	 * 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return true if legal, false if not
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if (horizontal) { // Horizontal case
			if (column + length > 9) {
				return false;
			}
			for (int i = row - 1; i < row + 3; i = i + 2) { // Check above and
															// below
				for (int j = column - 1; j < column + length + 1; j++) {
					if ((j > -1) && (j < 10) && (i > -1) && (i < 10)) {
						if (ocean.isOccupied(i, j)) {
							return false;
						}
					}
				}
			}
			if (column - 1 > -1) { // Check left
				if (ocean.isOccupied(row, column - 1)) {
					return false;
				}
			}
			if (column + 1 < 10) { // Check right
				if (ocean.isOccupied(row, column + length)) {
					return false;
				}
			}
		} else { // Vertical case
			if (row + length > 9) {
				return false;
			}
			for (int i = row - 1; i < row + length + 1; i++) { // Check left and
																// right
				for (int j = column - 1; j < column + 3; j = j + 2) {
					if ((j > -1) && (j < 10) && (i > -1) && (i < 10)) {
						if (ocean.isOccupied(i, j)) {
							return false;
						}
					}
				}
			}
			if (row - 1 > -1) {
				if (ocean.isOccupied(row - 1, column)) { // Check above
					return false;
				}
			}
			if (row + 1 < 10) {
				if (ocean.isOccupied(row + length, column)) { // Check below
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Calls the setters to take in the input row, column, and orientation for
	 * the ship instance
	 * 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		if (horizontal) {
			for (int i = column; i < column + length; i++) {
				ocean.ships[row][i] = this;
			}
		} else {
			for (int i = row; i < row + length; i++) {
				ocean.ships[i][column] = this;
			}
		}
		// System.out.println(this.getShipType() + " placed");
	}

	/**
	 * Simulates shot at this ship, sets the appropriate element in the ship's
	 * hit array to true
	 * 
	 * @param row
	 * @param column
	 * @return true if the ship is now sunk, false otherwise
	 */
	boolean shootAt(int row, int column) {
		if (this.isHorizontal()) {
			hit[column - getBowColumn()] = true;
		} else {
			hit[row - getBowRow()] = true;
		}
		if (this.isSunk()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @return true if all elements of hit array are true, indicating sunk ship.
	 *         false otherwise.
	 */
	boolean isSunk() {
		for (int i = 0; i < length; i++) {
			if (!(hit[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Sets all relevant elements in the ship's hit array to false, indicating
	 * no hits Used in subclass constructors
	 */
	void noHits() {
		for (int i = 0; i < length; i++) {
			hit[i] = false;
		}
	}

	/**
	 * @return "x" if ship is sunk, "S" otherwise
	 */
	public String toString() {
		if (this.isSunk()) {
			return "x";
		} else {
			return "S";
		}
	}
}
