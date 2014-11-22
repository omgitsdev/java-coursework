package frogger;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import resources.ResourceLoader;

/**
 * This class extends from the sprite model, updates and draws the frog on the
 * screen.
 * 
 * @author Dev, Agnes
 * 
 */
public class Frog extends Sprite {

	public enum Orientation {
		UP, RIGHT, DOWN, LEFT
	}

	Orientation orientation;
	Image image;
	int step = 30;
	boolean alive;
	Car car;
	boolean isCar = false;

	/**
	 * Constructor. Initiate the frog position, orientation, image and status
	 */
	public Frog() {
		this.x = 400;
		this.y = 160;
		this.dx = 0;
		this.dy = 0;
		this.orientation = Orientation.UP;
		// this.image = loadImage("frog-up.png");
		this.alive = true;
	}

	/**
	 * Perform frog jumping when clicking the arrow key UP
	 */
	void keyUp() {
		switch (orientation) {
		case UP:
			this.y -= step;
			break;
		case LEFT:
			if (this.x > 30) {
				this.x -= step;
			}
			break;
		case RIGHT:
			if (this.x < 740) {
				this.x += step;
			}
			break;
		case DOWN:
			if (this.y != 160) {
				this.y += step;
			}
			break;
		}
		update();
	}

	/**
	 * Perform frog jumping when clicking the arrow key DOWN
	 */
	void keyDown() {
		switch (orientation) {
		case UP:
			this.orientation = Orientation.DOWN;
			break;
		case LEFT:
			this.orientation = Orientation.RIGHT;
			break;
		case RIGHT:
			this.orientation = Orientation.LEFT;
			break;
		case DOWN:
			this.orientation = Orientation.UP;
			break;
		}
		update();
	}

	/**
	 * Perform frog jumping when clicking the arrow key LEFT
	 */
	void keyLeft() {
		switch (orientation) {
		case UP:
			this.orientation = Orientation.LEFT;
			break;
		case LEFT:
			this.orientation = Orientation.DOWN;
			break;
		case RIGHT:
			this.orientation = Orientation.UP;
			break;
		case DOWN:
			this.orientation = Orientation.RIGHT;
			break;
		}
		update();
	}

	/**
	 * Perform frog jumping when clicking the arrow key RIGHT
	 */
	void keyRight() {
		switch (orientation) {
		case UP:
			this.orientation = Orientation.RIGHT;
			break;
		case LEFT:
			this.orientation = Orientation.UP;
			break;
		case RIGHT:
			this.orientation = Orientation.DOWN;
			break;
		case DOWN:
			this.orientation = Orientation.LEFT;
			break;
		}
		update();
	}

	/**
	 * Getter for frog x position
	 */
	@Override
	public int getX() {
		return x;
	}

	/**
	 * Getter for frog y position
	 */
	@Override
	public int getY() {
		return y;
	}

	/**
	 * Getter for proper frog image
	 */
	@Override
	public Image getImage() {
		return image;
	}

	/**
	 * Call the frog images facing four orientations
	 */
	@Override
	void update() {
		if (alive) {
			switch (orientation) {
			case UP:
				//this.image = loadImage("res/img/frog-up.png");
				this.image = ResourceLoader.getImage("frog-up.png");
				break;
			case LEFT:
				this.image = ResourceLoader.getImage("frog-left.png");
				break;
			case RIGHT:
				this.image = ResourceLoader.getImage("frog-right.png");
				break;
			case DOWN:
				this.image = ResourceLoader.getImage("frog-down.png");
				break;
			}
		} else {
			this.image = loadImage("img/splat.gif");
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Call the frog image on the canvas
	 */
	@Override
	void draw(Graphics g) {
		g.drawImage(this.getImage(), this.getX(), this.getY(),
				(ImageObserver) this);
	}

	@Override
	int getOrientation() {
		switch (orientation) {
		case UP:
			return 2;
		case LEFT:
			return 3;
		case RIGHT:
			return 3;
		case DOWN:
			return 3;
		default:
			return -1;
		}
	}

	/**
	 * sets alive to false when frog is killed
	 */
	void kill() {
		this.alive = false;
	}
}
