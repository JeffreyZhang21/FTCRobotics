package grid;

import java.io.*;
import java.util.*;
import position.*;

/**
 * Field is a class defined as grid of Position. This class allows for saving/opening specific Field and other methods. 
 * @author Jeffr
 *
 */
public class Field extends Grid<Position>
{
	/**
	 * Creates and instance of a field. A field is defined as a Grid of Positions. This Class is a helper method which allows for easy sharing of this class
	 * @param rows An integer with the amount of rows in the field
	 * @param cols An integer with the amount of columns in the field
	 */
	public Field(int rows, int cols) 
	{ super(rows, cols); }
	
	/**
	 * Creates and instance of a field. A field is defined as a Grid of Positions. This Class is a helper method which allows for easy sharing of this class
	 * @param grid is and instance of Grid<Position> the field will be set to that grid
	 */
	public Field(Position[][] grid)
	{ super(grid); }
	
	/**
	 * opens a saved version of Field with all of its data saved. The current Field is not set to the opened file. A new version is only created
	 * @param name A string represents the name of the file that should be opened. File extension should no be added as this method does it automatically
	 * @return The current File represented as a field
	 * @throws FileNotFoundException If the file is not found
	 */
	public static Field open(String name) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File(name + ".jbot"));
		Field other = new Field(scan.nextInt(),scan.nextInt());
		while(scan.hasNextLine() && scan.hasNext())
		{
			int i = scan.nextInt();
			int j = scan.nextInt();
			Position pos = new Position(i,j);
			other.set(i, j, pos);
			
			while(!scan.hasNextInt() && scan.hasNext())
				pos.set(PositionTypes.valueOf(scan.next()),scan.nextInt());
		}
		scan.close();
		return other;
	}
	
	/**
	 * saves the current field as a file. The field is saved in the following way. First the rows and columns will be saved as integer. 
	 * Then each position is saved with all of its data inside
	 * @param name A string containing the name it should be saved
	 * @throws FileNotFoundException This exception will be thrown if it can not find the File. This should not happen
	 */
	public void save(String name) throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter(new File(name += ".jbot"));
		writer.println(super.getRows() + " " + super.getCols());
		for(int i=0; i<super.getRows(); i++)
			for(int j=0; j<super.getRows(); j++)
				writer.println(i + " " + j + " " + super.get(i, j).toString());
		 writer.close();
	}
	
	/**
	 * loops through a given Collection of positions and adds the Position Type to them
	 * @param pos a Collection of Position
	 * @param type a type that each position will be added to
	 * @param amount and integer that describes how much should be added
	 */
	public void addAll(Collection<Position> pos, PositionTypes type, int amount)
	{
		for(Position p: pos)
			p.add(type,amount);
	}
	
	/**
	 * loops through a given Collection of positions and subtracts the Position Type to them
	 * @param pos a Collection of Position
	 * @param type a type that each position will be subtracted from
	 * @param how much should be removed
	 */
	public void subAll(Collection<Position> pos, PositionTypes type, int amount)
	{
		for(Position p: pos)
			p.sub(type, amount);
	}
	
	/**
	 * loops thorough the given collection and removes type from them
	 * @param pos a collection of position
	 * @param type the type that should be removed
	 */
	public void removeAllType(Collection<Position> pos, PositionTypes type)
	{
		for(Position p : pos)
			p.remove(type);
	}
	
	/**
	 * Displays the Field formated nicely
	 */
	public String toString()
	{
		String r = "";
		for(int i=0; i<super.getRows(); i++)
		{
			r+= "{ ";
			for(int j=0; j<super.getRows(); j++)
				r += super.get(i, j) + ", ";
			r += "}\n";
		}
		return r; 
	}
	
	public String textDisplay(HashMap<PositionTypes,String> displayCode)
	{
		String r = "";
		for(int i=0; i<super.getRows(); i++)
		{
			r+= "{ ";
			for(int j=0; j<super.getRows(); j++)
				if(displayCode != null)
					r += displayCode.get(super.get(i, j).getDataPriority(PositionTypes.values())) + ", ";
				else
					r += super.get(i, j).getDataPriority(PositionTypes.values()).toString().charAt(0) + ", ";
			r += "}\n";
		}
		return r; 
	}
}
