package frogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class creates an ArrayList and adds/calls objects from the sprite model.
 * 
 * @author Dev, Agnes
 * 
 */
public class Cast {
	ArrayList<Sprite> list = new ArrayList<Sprite>();
	ArrayList<Integer> rightCarXs = new ArrayList<Integer>();
	ArrayList<Integer> leftCarXs = new ArrayList<Integer>();
	int density = 3;
	int CarX = 0;
	Random rand, rand2;
	boolean flag = true;
	boolean right;
	int orientCheck;

	/**
	 * Constructor. Set the ArrayList with frog and cars
	 */
	public Cast() {
		for (int i = 0; i < density; i++) {
			addCar();
		}

	}

	/**
	 * Generates random variables for car location and creates car instance
	 */
	public void addCar() {
		rand2 = new Random();
		if (rand2.nextBoolean()) {
			right = true;
			orientCheck = 0;
		} else {
			right = false;
			orientCheck = 1;
		}

		do {
			flag = false;
			rand = new Random();
			CarX = rand.nextInt(900);
			for (int k = 0; k < list.size(); k++) {
				if (list.get(k).getOrientation() == orientCheck) {
					for (int j = list.get(k).getX(); j < list.get(k).getX() + 80; j++) {
						for (int l = CarX; l < CarX + 80; l++) {
							if (l == j) {
								flag = true;
							}
						}
					}
				}
			}
		} while (flag);
		list.add(new Car(CarX, right));

	}

	/**
	 * Add new sprite into the ArrayList
	 * 
	 * @param sprite
	 */

	public void addSprite(Sprite sprite) {
		list.add(sprite);
	}

	/**
	 * Remove old sprite from the ArrayList
	 * 
	 * @param sprite
	 */
	public void remove(Sprite sprite) {
		list.remove(sprite);
	}

	/**
	 * Getter of the list
	 * 
	 * @return
	 */
	public ArrayList<Sprite> getList() {
		return list;
	}

	/**
	 * Getter of the list size
	 * 
	 * @return
	 */
	public int getSize() {
		return list.size();
	}

	/**
	 * Regulate the cars density shown on the canvas
	 */
	public void levelUp() {
		addCar();

	}

}
