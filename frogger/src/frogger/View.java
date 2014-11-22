package frogger;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * This is the view and observer in this program.
 * This class paints/repaints the highway, the frog and cars on the screen.
 * 
 * @author Dev, Agnes
 * 
 */
public class View extends JPanel implements Observer {
	Background bg = new Background();
	Cast cast;
	ArrayList<Sprite> list = new ArrayList<Sprite>();

	/**
	 * Constructor. Create the view from the model sprite (cast in a list)
	 * @param cast
	 */
	public View(Cast cast, ArrayList<Sprite> list) {
		this.cast = cast;
		this.list = list;
	}

	/**
	 * It is used to call frog and/or cars in the controller
	 */
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

	/**
	 * It paints the basic game setting, inclusive of highway, frog, cars
	 */
	public void paint(Graphics g) {
		bg.draw(g);
		for (int i = 0; i < cast.getSize(); i++) {
			g.drawImage(cast.getList().get(i).getImage(), cast.getList().get(i)
					.getX(), cast.getList().get(i).getY(), this);
		}
	}
}