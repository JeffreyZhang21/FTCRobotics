package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import grid.Field;
import position.PositionTypes;

public class VisualDisplay 
{
	private Field field;
	private JFrame frame;
	public VisualDisplay(Field field)
	{
		this.field = field;
		render();
	}
	
	public JFrame render()
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(field.getRows(),field.getCols()));
		
		for(int i=0; i<field.getRows(); i++)
			for(int j=0; j<field.getCols(); j++)
			{
				JPanel temp = new JPanel();
				temp.setPreferredSize(new Dimension(10,10));
				PositionTypes t = field.get(i, j).getDataPriority(PositionTypes.values());
				temp.setBackground(new Color(t.getR(),t.getG(),t.getB()));
				pane.add(temp);
			}
		frame.getContentPane().add(pane, BorderLayout.CENTER);
		frame.pack();
		this.frame = frame;
		return frame;
		
	}
	
	public void display(boolean visible)
	{
		frame.setVisible(visible);
	}
}
