//Dev Sethi and Ce Wang
//CIT 590 

package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Oper extends CalculatorButton {
	String operator;

	/**
	 * Creates operation buttons and respective functionality
	 * @param chip
	 * @param operator
	 */
	public Oper(CalculatorChip chip,String operator){
		super(chip);
		color=new Color(127,255,76);
		this.operator=operator;
		this.setText(operator);
		this.setBackground(color);
		this.setPreferredSize(new Dimension(85, 70));
	}
	
	/**
	 * Constructor specifically for larger equals button
	 * @param chip
	 * @param operator
	 * @param flag
	 */
	public Oper(CalculatorChip chip,String operator,boolean flag){
		super(chip);
		this.setText(operator);
		this.operator=operator;
		this.setBackground(color);
		this.setPreferredSize(new Dimension(85, 145));
	}

	@Override
	String onClick(ActionEvent e) {
		String result = "";
		switch(operator){
		case "+": result= chip.add();break;
		case "=":result= chip.equals();break;
		case "-": result= chip.subtract();break;
		case "x":result= chip.multiply();break;
		case "\u00F7": result= chip.divide();break;
		case "%":result= chip.percent();break;
		case "+/-":result = chip.changeSign();break;
		case "\u221A":result = chip.sqrt();break;
		case "1/x":result=chip.invert();break;
		}
		return result;
	}

	
}
