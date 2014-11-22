package frogger;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.imageio.ImageIO;

import resources.ResourceLoader;

/**
 * This class extends from the sprite model, updates and draws cars on the
 * screen.
 * 
 * @author Dev, Agnes
 * 
 */
public class Car extends Sprite {
	int colorCode;
	int directionCode;
	boolean right;
	boolean active;
	Image image;
	Cast cast;
	boolean flag = true;
	boolean isCar = true;

	/**
	 * Constructor. Randomize cars moving on the highway, different colors and
	 * directions
	 */
	public Car(int x, boolean right) {
		active = true;
		dx = 5;
		dy = 0;
		Random rand1 = new Random();
		colorCode = rand1.nextInt(6);
		this.x = x;
		if (right) {
			this.right = true;
			y = 110;
		} else {
			this.right = false;
			y = 44;
		}

		switch (colorCode) {
		case 0:
			if (this.right) {
				this.image = ResourceLoader.getImage("white-car-right.png");
			} else {
				this.image = ResourceLoader.getImage("white-car-left.png");
			}
			break;
		case 1:
			if (this.right) {
				this.image = ResourceLoader.getImage("green-car-right.png");
			} else {
				this.image = ResourceLoader.getImage("green-car-left.png");
			}
			break;
		case 2:
			if (this.right) {
				this.image = ResourceLoader.getImage("red-car-right.png");
			} else {
				this.image = ResourceLoader.getImage("red-car-left.png");
			}
			break;
		case 3:
			if (this.right) {
				this.image = ResourceLoader.getImage("blue-car-right.png");
			} else {
				this.image = ResourceLoader.getImage("blue-car-left.png");
			}
			break;
		case 4:
			if (this.right) {
				this.image = ResourceLoader.getImage("aqua-car-right.png");
			} else {
				this.image = ResourceLoader.getImage("aqua-car-left.png");
			}
			break;
		case 5:
			if (this.right) {
				this.image = ResourceLoader.getImage("yellow-car-right.png");
			} else {
				this.image = ResourceLoader.getImage("yellow-car-left.png");
			}
			break;
		}
	}

	/**
	 * Getter for car x position
	 */
	@Override
	public int getX() {
		return x;
	}

	/**
	 * Getter for car y position
	 */
	@Override
	public int getY() {
		return y;
	}

	/**
	 * Getter for proper cars image
	 */
	@Override
	public Image getImage() {
		return image;
	}

	/**
	 * Regulate the cars movement
	 */
	@Override
	void update() {
		if (right) {
			this.x += dx;
			if (this.x > 900) {
				this.x = -100;

			}
		} else {
			this.x -= dx;
			if (this.x < -100) {
				this.x = 900;
			}
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Call the cars image on the canvas
	 */
	@Override
	void draw(Graphics g) {
		g.drawImage(this.getImage(), this.getX(), this.getY(),
				(ImageObserver) this);
	}
	

	
	@Override
	int getOrientation() {
		if (right) {
			return 0;
		} else {
			return 1;
		}
	}

}
