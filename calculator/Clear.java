//Dev Sethi and Ce Wang
//CIT 590 

package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Clear extends CalculatorButton {

	/**
	 * constructor for Clear button. Colors accordingly and allow for change in label
	 * @param chip
	 */
	public Clear(CalculatorChip chip) {
		super(chip);
		color = new Color(255, 234, 76);
		font = new Font("Helvetica", Font.BOLD, 14);
		this.setText("Clear");
		this.setBackground(color);
		this.setFont(font);
		this.setPreferredSize(new Dimension(95, 50));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(colorF);
				mouseOver = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(color);
				mouseOver = false;
				repaint();
			}
		});
	}

	@Override
	String onClick(ActionEvent e) {
		if (chip.clearFlag) {
			return chip.clear();
		} else {
			return chip.clearAll();
		}
	}
}
