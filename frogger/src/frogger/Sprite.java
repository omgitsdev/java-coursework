package frogger;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

/**
 * This is an abstract class as a model and observable in this program.
 * 
 * @author Dev, Agnes
 *
 */
public abstract class Sprite extends Observable {
	int x, y, dx, dy;
	Image image;

	/**
	 * Load images from files
	 * @param fileName
	 * @return
	 */
	protected Image loadImage(String fileName) {
		Image img = null;
		try {
			img = ImageIO.read(new File(fileName));
		} catch (IOException exc) {
			System.out.println("Can't load image.");
		}
		return img;
	}
	
	/**
	 * returns orientation of sprite:
	 * 0 = car going right, 1 = car going left
	 * 2 = frog going up or down, 3 = frog going left or right
	 */
	abstract int getOrientation();
	/**
	 * Abstract method of update
	 */
	abstract void update();
	
	/**
	 * Abstract method of draw
	 */
	abstract void draw(Graphics g);

	/**
	 * Abstract method of x position getter
	 */
	abstract int getX();

	/**
	 * Abstract method of y position getter
	 */
	abstract int getY();

	/**
	 * Abstract method of image getter
	 */
	abstract Image getImage();
}
