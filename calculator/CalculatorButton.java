//Dev Sethi and Ce Wang
//CIT 590 

package calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;


@SuppressWarnings("serial")
abstract class CalculatorButton extends JButton {
	

	public CalculatorChip chip;
	Color colorF=new Color(255,204,0);
	public Color color = new Color(127,255,76);
	boolean mouseOver;
	Font font=new Font("Helvetica", Font.BOLD, 18);

	/**
	 * Creates buttons, refined by subclasses
	 * @param chip
	 */
	public CalculatorButton(CalculatorChip chip){
		this.chip=chip;
		this.setOpaque(true);
		this.setFont(font);
		this.setVisible(true);  
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



	abstract String onClick(ActionEvent e);

	
}