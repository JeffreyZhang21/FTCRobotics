package display;

import java.util.HashMap;
import grid.Field;
import position.PositionTypes;

public class TestDisplay 
{
	private Field field;
	private HashMap<PositionTypes,String> map;
	private String display = "";
	public void textDisplay(Field field)
	{
		this.field = field;
		map = new HashMap<>();
	}
	
	public void map(PositionTypes type, String display)
	{ map.put(type,display); }
	
	public void removeMap(PositionTypes type)
	{ map.remove(type); }
	
	public void render()
	{
		display = "";
		for(int i=0; i<field.getRows(); i++)
		{
			display+= "{ ";
			for(int j=0; j<field.getCols(); j++)
				if(!map.isEmpty())
					display += map.get(field.get(i, j).getDataPriority(PositionTypes.values())) + ",";
				else
					display += field.get(i, j).getDataPriority(PositionTypes.values()).toString().charAt(0) + ",";
			display += "}\n";
		}
	}
	
	public String display()
	{ return display; }
	
	public String toString()
	{ 
		render();
		return display();
	}
}
