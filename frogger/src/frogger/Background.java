package frogger;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class draws the background, i.e. the highway
 * 
 * @author Dev, Agnes
 * 
 */
public class Background {
	Color borderColor = Color.WHITE;
	Color roadColor = Color.GRAY;
	Color divideColor = Color.YELLOW;
	static int frameW = 800;
	static int frameH = 190;

	/**
	 * Set the highway layout
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(borderColor);
		g.fillRect(0, 0, frameW, frameH);
		g.setColor(roadColor);
		g.fillRect(0, 31, frameW, 62);
		g.setColor(divideColor);
		g.fillRect(0, 93, frameW, 4);
		g.setColor(roadColor);
		g.fillRect(0, 97, frameW, 62);
	}
}
