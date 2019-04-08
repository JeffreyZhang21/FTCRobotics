package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import grid.Field;
import position.PositionTypes;

public class VisualDisplay extends Display 
{
	private JFrame frame;
	public VisualDisplay(Field field)
	{ 
		super(field); 
		frame = new JFrame("field");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
	}
	
	
	public void display(boolean visible)
	{ frame.setVisible(visible); }

	public String display() {
		display(true);
		return null;
	}
	
	public void init() {
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(super.getField().getRows(),super.getField().getCols()));
		
		for(int i=0; i<super.getField().getRows(); i++)
			for(int j=0; j<super.getField().getCols(); j++)
			{
				JPanel temp = new JPanel();
				temp.setPreferredSize(new Dimension(10,10));
				PositionTypes t = super.getField().get(i, j).getDataPriority(PositionTypes.values());
				temp.setBackground(new Color(t.getR(),t.getG(),t.getB()));
				pane.add(temp);
			}
		frame.getContentPane().add(pane, BorderLayout.CENTER);
		frame.pack();
	}
}
