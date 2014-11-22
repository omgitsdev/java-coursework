package frogger;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This is the controller of the entire program. This class creates a frame:
 * buttons, labels and game processing
 * 
 * @author Dev, Agnes
 * 
 */
public class Frogger extends JFrame {
	Cast cast = new Cast();
	ArrayList<Sprite> list = new ArrayList<Sprite>();
	Frog frog = new Frog();
	View view = new View(cast, list);
	Timer timer;
	int frogOrientation = -1;
	int frogLane = -1;
	int frogX = -1;
	boolean deadFrog = false;
	BorderLayout layout = new BorderLayout();
	JPanel buttomPanel = new JPanel();
	JButton pause = new JButton("Pause");
	JButton resume = new JButton("Resume");
	JTextField time = new JTextField();
	JTextField level = new JTextField();
	JTextField lives = new JTextField();
	JPanel endPanel = new JPanel();

	Integer secDisplay = 0;
	Integer minDisplay = 0;
	String timeDisplay;
	int density;
	int lev = 1;
	int lifeCount = 5;

	/**
	 * Constructor. Give the game a title
	 */
	public Frogger() {
		super("Frogger");
	}

	/**
	 * Set the panels layout on the canvas
	 */
	private void layoutComponents() {
		this.setLayout(layout);
		this.add(view, BorderLayout.CENTER);
		pause.setEnabled(true);
		resume.setEnabled(false);

		buttomPanel.add(time);
		buttomPanel.add(level);
		buttomPanel.add(lives);
		buttomPanel.add(pause);
		buttomPanel.add(resume);
		level.setPreferredSize(new Dimension(75, 30));
		lives.setPreferredSize(new Dimension(75, 30));
		time.setPreferredSize(new Dimension(50, 30));
		level.setText("Level " + lev);
		lives.setText("Lives: " + lifeCount);
		this.add(buttomPanel, BorderLayout.SOUTH);
	}

	/**
	 * Perform buttons upon clicking
	 */
	private void attachListenersToComponents() {
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				timer.cancel();
				pause.setEnabled(false);
				resume.setEnabled(true);
			}
		});
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				timer = new Timer(true);
				timer.schedule(new Strobe(), 0, 40);
				timer.schedule(new Clock(), 0, 1000);
				resume.setEnabled(false);
				pause.setEnabled(true);
			}
		});
	}

	/**
	 * Initialize the game. Connect view and model
	 */
	public void init() {
		this.setFocusable(true);
		layoutComponents();
		attachListenersToComponents();
		cast.addSprite(frog);
		frog.addObserver(view);
	}

	/**
	 * Schedule the random cars movement
	 */
	private class Strobe extends TimerTask {
		@Override
		public void run() {
			for (int i = 0; i < cast.getSize(); i++) {
				cast.getList().get(i).update();
				if (cast.getList().get(i).getOrientation() == 2
						|| cast.getList().get(i).getOrientation() == 3) {
					trackFrog(i);
				} else {
					checkSplat(i);
				}

			}
			lives.setText("Lives: " + lifeCount);
			level.setText("Level: " + lev);
		}
	}

	/**
	 * tracks frog to know which lane it is
	 */
	void trackFrog(int i) {
		frogOrientation = cast.getList().get(i).getOrientation();
		frogX = cast.getList().get(i).getX();
		if (cast.getList().get(i).getY() == 130
				|| cast.getList().get(i).getY() == 100) {
			frogLane = 0;
		} else if (cast.getList().get(i).getY() == 70
				|| cast.getList().get(i).getY() == 40) {
			frogLane = 1;
		} else if (cast.getList().get(i).getY() == -20 && !deadFrog) {
			levelUp();

		} else {
			frogLane = -1;
		}
	}

	/**
	 * Inreases level and density when a frog clears the stage
	 */
	void levelUp() {
		deadFrog = true;
		lev += 1;
		cast.levelUp();
		cast.remove(frog);
		frog = new Frog();
		deadFrog = false;
		cast.addSprite(frog);
		frog.addObserver(view);

	}

	/**
	 * checks if any portion of a car overlaps with any portion of the frog
	 */
	void checkSplat(int i) {
		if (cast.getList().get(i).getOrientation() == frogLane) {
			for (int j = cast.getList().get(i).getX(); j < cast.getList()
					.get(i).getX() + 74; j++) {
				for (int k = frogX; k < frogX + 30; k++) {
					if (!deadFrog) {
						if (j == k) {
							frog.kill();
							timer.schedule(new cleanUp(), 2000);
							deadFrog = true;
						}
					}

				}
			}
		}
	}

	/**
	 * Schedule the clock counting up
	 */
	private class Clock extends TimerTask {
		@Override
		public void run() {
			if (secDisplay < 59) {
				secDisplay += 1;
			} else if (secDisplay == 59) {
				secDisplay = 0;
				minDisplay += 1;
			}
			if (secDisplay < 10) {
				timeDisplay = "0" + secDisplay;
			} else {
				timeDisplay = secDisplay.toString();
			}
			timeDisplay = minDisplay + ":" + timeDisplay;
			time.setText(timeDisplay);
		}
	}

	/**
	 * if frog dies, removes splat image and creates new frog
	 * if last life, ends game
	 *
	 */
	private class cleanUp extends TimerTask {
		public void run() {
			if (deadFrog) {
				lifeCount -= 1;
				cast.remove(frog);
				deadFrog = false;
				frogX = -1;
				frogLane = -1;
				frog = new Frog();
				cast.addSprite(frog);
				frog.addObserver(view);
				if (lifeCount == 0) {
					timer.cancel();
					lives.setText("Lives: 0");
					pause.setText("GAME");
					pause.setEnabled(false);
					resume.setText("OVER");
					resume.setEnabled(false);
				}
			}
		}
	}

	/**
	 * Execute TimerTasks (car moving, clock counting) as well as arrow keys
	 * controlling of frog jumping
	 */
	public void run() {
		timer = new Timer(true);
		timer.schedule(new Strobe(), 0, 40);
		timer.schedule(new Clock(), 0, 1000);

		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (frog.alive == true && pause.isEnabled()) {
					if (e.getKeyCode() == KeyEvent.VK_UP) {
						frog.keyUp();
						setFocusable(true);
					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						frog.keyDown();
					}
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						frog.keyLeft();
					}
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						frog.keyRight();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

	/**
	 * Main function controlling the Frogger game processing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Frogger frogger = new Frogger();
		frogger.init();
		frogger.setSize(800, 250);
		frogger.setVisible(true);
		frogger.setResizable(false);
		frogger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frogger.run();

	}

}