package position;

import counter.Counter;

public class Position extends Counter<PositionTypes> 
{
	private int x,y;
	
	/**
	 * creates a position given its x and y coordinate position
	 * @param x the X position
	 * @param y the Y position
	 */
	public Position(int x, int y)
	{ setPosition(x,y); }
	
	/**
	 * sets the position to the given x and y
	 * @param x the new x position
	 * @param y the new y position
	 */
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * returns the distance between Position a and Position b
	 * @param a the first Position
	 * @param b the second Position
	 * @return the distance between these two points
	 */
	public static double distance(Position a , Position b)
	{ return Math.hypot(a.x - b.x, a.y - b.y); }
	
	/**
	 * returns the x position
	 * @return the x coordinate
	 */
	public int getX()
	{ return x; }
	
	/**
	 * returns the y position
	 * @return the y coordinate 
	 */
	public int getY()
	{ return y; }
	
	/**
	 * Method that get value through counter. This method should override the one from counter
	 * @param arr an array considered the checking order
	 * @return a position 
	 */
	public PositionTypes getDataPriority(PositionTypes[] arr)
	{
		for(int i=0; i<arr.length; i++)
		{
			Integer temp = super.get(arr[i]);
			if(temp != null && temp > 0)
				return arr[i];
		}
		return PositionTypes.EMPTY;
	}

}

