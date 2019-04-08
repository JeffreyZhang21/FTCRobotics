package pathFind;

import java.util.HashMap;

import grid.Field;
import pathFind.AStar;
import position.Position;
import position.PositionTypes;
import processing.core.PApplet;

public class AstarTest extends PApplet 
{
	public static void main(String[] args)
	{ 
		PApplet.main("pathFind.AstarTest");
	}
	private Field field;
	private int positionSize = 10;
	
	public void settings()
	{
		field = new Field(30,30);
		size(field.getCols()*positionSize,field.getRows()*positionSize);
	}
	
	public void setup()
	{
		for(int i=0; i<field.getRows(); i++)
			for(int j=0; j<field.getCols(); j++)
				field.set(i, j,new Position(i*positionSize,j*positionSize));
		
		Position start = field.get(0, 0);
		Position end   = field.get(29, 29);
		
		start.add(PositionTypes.START);
		end.add(PositionTypes.END);
		
		AStar a = new AStar(field, start, end);
		
		for(int i=0; i<50; i++)
		{
			Position t = field.get((int)(Math.random() * field.getRows()), (int)(Math.random() * field.getCols()));
			a.addObstacle(t);    
		}
		
		a.pathFind(true);
		
		if(a.getPath() != null)
			(field).addAll(a.getPath(),PositionTypes.PATH,1);
		
		field.addAll(a.getObstacles(), PositionTypes.BARRIER,1);
		
		
		System.out.println(a.getObstacles().size());
		System.out.println(a.getPath());
		System.out.println(a.getPathAngles());
		
		HashMap<PositionTypes,String> displayCode = new HashMap<>();
		displayCode.put(PositionTypes.EMPTY, " ");
		displayCode.put(PositionTypes.BARRIER, "X");
		displayCode.put(PositionTypes.PATH, "+");
		displayCode.put(PositionTypes.START,"S");
		displayCode.put(PositionTypes.END, "E" );
		displayCode.put(PositionTypes.HAZARD, "x");
		
		
		//System.out.println(field.textDisplay(displayCode));
		
		
		display();
	}
	
	public void draw()
	{
		
	}
	
	public void display()
	{
		//noStroke();
		for(int i=0; i<field.getRows(); i++)
		{
			for(int j=0; j<field.getCols(); j++)
			{
				Position current = field.get(i, j);
				PositionTypes color = current.getDataPriority(PositionTypes.values());
				fill(color.getR(),color.getG(),color.getB());
				rect(current.getY(),current.getX(),positionSize,positionSize);
			}
		}
	}
	
	
}
