package display;

import grid.Field;
import position.PositionTypes;

public class DisplayTest 
{
	public static void main (String[] args)
	{
		Field field = new Field(200,200);
		field.init();
		
		field.get(5, 5).add(PositionTypes.BARRIER);
		field.get(6, 6).add(PositionTypes.END);
		
		VisualDisplay display = new VisualDisplay(field);
		
		display.display(true);
	}
}
