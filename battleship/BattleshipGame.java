//Yuan Yu and Dev Sethi

package battleship;

import java.util.Scanner;

public class BattleshipGame {

	/**
	 * Main function: creates ocean, places all ships randomly, facilitates user
	 * input and displays state of game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int row;
		int column;
		String again;

		do {
			Ocean ocean = new Ocean();
			ocean.placeAllShipsRandomly();
			do {
				ocean.print();
				System.out.println("\nWhere to fire?");
				do {
					System.out.print("Enter row (0-9): ");
					row = scanner.nextInt();
				} while (row < 0 || row > 9);
				do {
					System.out.print("Enter column (0-9): ");
					column = scanner.nextInt();
				} while (column < 0 || column > 9);
				ocean.shootAt(row, column);
			} while (!(ocean.isGameOver()));
			System.out.println("Game Over");
			do {
				System.out.println("Play again (y/n)? ");
				again = scanner.next();
			} while (!((again.toLowerCase().equals("y")) || (again
					.toLowerCase().equals("n"))));

		} while (again.toLowerCase().equals("y"));
		System.out.println("Goodbye");
	}
}