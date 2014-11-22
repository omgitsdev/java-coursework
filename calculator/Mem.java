//Dev Sethi and Ce Wang
//CIT 590 

package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Mem extends CalculatorButton {
	String meno;

	/**
	 * Creates Memory buttons and respective functionality
	 * @param chip
	 * @param meno
	 */
	public Mem(CalculatorChip chip,String meno){
		super(chip);
		color=new Color(252,255,104);
		this.meno=meno;
		this.setText(meno);
		this.setBackground(color);
		this.setPreferredSize(new Dimension(90,70)); 
	}


	@Override
	String onClick(ActionEvent e) {
		String result="";
		switch(meno){
		case "mc":
			result= chip.memClear();
			break;
		case "mr":
			result= chip.memRead();
			break;
		case "m+":
			result=chip.memPlus();
			break;
		case "m-":
			result=chip.memMinus();
			break;
		}
		return result;
	}

}
