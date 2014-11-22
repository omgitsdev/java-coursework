//Dev Sethi and Ce Wang
//CIT 590 

package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Num extends CalculatorButton {

	String result;
	int value;
	boolean decFlag;

	/**
	 * Creates number buttons and respective functionality
	 * @param chip
	 * @param number
	 */
	public Num(CalculatorChip chip, int number) {
		super(chip);
		decFlag = false;
		color=new Color(119,239,255);
		this.value = number;
		this.setText(Integer.toString(number)); 
		this.setBackground(color);
		this.setForeground(Color.BLACK);
		this.setPreferredSize(new Dimension(87, 70));

	}

	/**
	 * Creates decimal point button and respective functionality
	 * @param chip
	 * @param number
	 */
	public Num(CalculatorChip chip, String number) {
		super(chip);
		decFlag = true;
		this.setText(number);
		this.setBackground(color);
		this.setForeground(Color.BLACK);
		this.setPreferredSize(new Dimension(87, 70));
		
	}

	@Override
	String onClick(ActionEvent e) {
		if (decFlag) {
			return chip.decimalPoint();
		} else {
			return chip.digit(value);
		}
		
	}
}
