package display;

import java.util.HashMap;
import grid.Field;
import position.PositionTypes;

public class TextDisplay extends Display
{
	private HashMap<PositionTypes,String> map;
	private String display = "";

	public TextDisplay(Field field) 
	{
		super(field);
		map = new HashMap<>();
		init();
	}
	
	public void map(PositionTypes type, String display)
	{ map.put(type,display); }
	
	public void removeMap(PositionTypes type)
	{ map.remove(type); }
	
	public String display()
	{ return display; }

	@Override
	public void init() {
		display = "";
		for(int i=0; i<super.getField().getRows(); i++)
		{
			display+= "{ ";
			for(int j=0; j<super.getField().getCols(); j++)
				if(!map.isEmpty())
					display += map.get(super.getField().get(i, j).getDataPriority(PositionTypes.values())) + ",";
				else
					display += super.getField().get(i, j).getDataPriority(PositionTypes.values()).toString().charAt(0) + ",";
			display += "}\n";
		}
	}
}
