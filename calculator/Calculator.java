//Dev Sethi and Ce Wang
//CIT 590 

package calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Dev
 *
 */
/**
 * @author Dev
 *
 */
/**
 * @author Dev
 *
 */
@SuppressWarnings("serial")
public class Calculator extends JFrame {
	CalculatorChip chip = new CalculatorChip();
	public JPanel buttonJPanel, textPanel, clearPanel, numPanel, opPanel,
			memPanel, vPanel, layoutPanel;
	public FlowLayout vGrid, opGrid, numGrid, memGrid;
	public BorderLayout buttonLayout, Blayout, clearLayout, frameLayout,
			textLayout;
	public JTextField myJTextField;
	public Clear clear;
	int gap = 9;

	public Calculator() {
		createGUI();

	}

	/**
	 * Creates panels and assigns layouts, calls functions to create buttons
	 */
	public void createGUI() {
		frameLayout = new BorderLayout();
		this.setLayout(frameLayout);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		frameLayout.setHgap(2);
		frameLayout.setVgap(2);

		textPanel = new JPanel();
		textLayout = new BorderLayout();
		textPanel.setLayout(textLayout);
		textPanel.setPreferredSize(new Dimension(400, 50));
		this.add(textPanel, BorderLayout.NORTH);
		myJTextField = new JTextField(chip.getText());
		myJTextField.setSize(300, 100);
		myJTextField.setFont(new Font("Helvetica", Font.BOLD, 20));
		clear = new Clear(chip);
		createListener(clear);
		myJTextField.setHorizontalAlignment(JTextField.RIGHT);
		textPanel.add(myJTextField, BorderLayout.CENTER);

		clearPanel = new JPanel();
		clearLayout = new BorderLayout();
		clearPanel.setLayout(clearLayout);
		clearLayout.setHgap(1);
		clearLayout.setVgap(0);
		this.add(clearPanel, BorderLayout.CENTER);
		clearPanel.add(clear, BorderLayout.EAST);

		buttonJPanel = new JPanel();
		buttonLayout = new BorderLayout();
		buttonLayout.setHgap(0);
		buttonLayout.setVgap(0);
		buttonJPanel.setPreferredSize(new Dimension(400, 405));
		buttonJPanel.setLayout(buttonLayout);
		this.add(buttonJPanel, BorderLayout.SOUTH);

		numPanel = new JPanel();
		numGrid = new FlowLayout();
		numPanel.setLayout(numGrid);
		numPanel.setPreferredSize(new Dimension(288, 300));
		numGrid.setHgap(gap);
		numGrid.setVgap(gap);
		buttonJPanel.add(numPanel, BorderLayout.WEST);

		opPanel = new JPanel();
		opGrid = new FlowLayout();
		opGrid.setHgap(gap);
		opGrid.setVgap(gap);
		opPanel.setLayout(opGrid);
		opPanel.setPreferredSize(new Dimension(108, 300));
		buttonJPanel.add(opPanel, BorderLayout.CENTER);

		vPanel = new JPanel();
		vGrid = new FlowLayout();
		vGrid.setHgap(gap);
		vGrid.setVgap(gap);
		vPanel.setLayout(vGrid);
		vPanel.setPreferredSize(new Dimension(96, 300));
		buttonJPanel.add(vPanel, BorderLayout.EAST);

		memPanel = new JPanel();
		memGrid = new FlowLayout();
		memGrid.setHgap(gap);
		memGrid.setVgap(gap);
		memPanel.setLayout(memGrid);
		memPanel.setPreferredSize(new Dimension(100, 80));
		buttonJPanel.add(memPanel, BorderLayout.NORTH);

		this.setVisible(true);
		this.setMinimumSize(new Dimension(510, 535));

		// ///////////set layout
		createMemoryButtons();
		createNumberButtons();
		createOperationButtons();
		createSignButton();

		// /////////////create button

//		class MyJTextFieldListener implements ActionListener {
//			public void actionPerformed(ActionEvent arg0) {
//				myJTextField.setText("New text for the JTextField");
//			}
//
//		}
//
//		myJTextField.addActionListener(new MyJTextFieldListener());

	}

	/**
	 * General listener used for all buttons, calls the onClick method of the specific sublcass
	 * @param b
	 */
	void createListener(CalculatorButton b) {
		b.addActionListener(new ActionListener() {
			CalculatorButton tepNum;

			@Override
			public void actionPerformed(ActionEvent e) {
				myJTextField.setText(tepNum.onClick(e));
				if (chip.clearFlag) {
					clear.setText("Clear");
				} else {
					clear.setText("All Clear");
				}

			}

			public ActionListener init(CalculatorButton b) {
				tepNum = b;
				return this;
			}

		}.init(b));
	}

	/**
	 * Creates numbers via Num sublcass of CalculatorButton
	 */
	public void createNumberButtons() {

		for (int i = 7; i < 10; i++) {
			Num num = new Num(chip, i);
			numPanel.add(num);
			createListener(num);

		}

		for (int i = 4; i < 7; i++) {
			Num num = new Num(chip, i);
			numPanel.add(num);
			createListener(num);

		}

		for (int i = 1; i < 4; i++) {
			Num num = new Num(chip, i);
			numPanel.add(num);
			createListener(num);
		}
		Num num0 = new Num(chip, 0);
		numPanel.add(num0);
		createListener(num0);
		Num numd = new Num(chip, ".");
		numPanel.add(numd);
		createListener(numd);

	}

	/**
	 * Creates operational buttons via Oper subclass of CalculatorButton
	 */
	public void createOperationButtons() {

		Oper div = new Oper(chip, "\u00F7");
		opPanel.add(div);
		createListener(div);

		Oper pect = new Oper(chip, "%");
		vPanel.add(pect);
		createListener(pect);

		Oper mul = new Oper(chip, "x");
		opPanel.add(mul);
		createListener(mul);

		Oper cov = new Oper(chip, "1/x");
		vPanel.add(cov);
		createListener(cov);

		Oper sub = new Oper(chip, "-");
		opPanel.add(sub);
		createListener(sub);

		Oper equl = new Oper(chip, "=", true);
		vPanel.add(equl);
		createListener(equl);

		Oper sum = new Oper(chip, "+");
		opPanel.add(sum);
		createListener(sum);

		Oper sqrt = new Oper(chip, "\u221A");
		memPanel.add(sqrt);
		createListener(sqrt);

	}

	/**
	 *Creates memory buttons via Mem sublcass of CalculatorButton 
	 */
	public void createMemoryButtons() {

		Mem mc = new Mem(chip, "mc");
		memPanel.add(mc);
		createListener(mc);

		Mem mr = new Mem(chip, "mr");
		memPanel.add(mr);
		createListener(mr);

		Mem mp = new Mem(chip, "m+");
		memPanel.add(mp);
		createListener(mp);

		Mem ms = new Mem(chip, "m-");
		memPanel.add(ms);
		createListener(ms);
	}

	/**
	 *Creates sign button via Oper sublcass of CalculatorButton 
	 */
	public void createSignButton() {

		Oper sign = new Oper(chip, "+/-");
		numPanel.add(sign);
		createListener(sign);
	}

	public static void main(String args[]) {
		@SuppressWarnings("unused")
		Calculator calculator = new Calculator();
	}

}