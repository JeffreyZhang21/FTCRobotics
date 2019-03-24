package grid;

import java.util.ArrayList;

public class Grid<E> 
{
	private Object[][] arr;
	
	/**
	 * This constructor creates a grid with the specified amount of rows and columns. Grid 
	 * creates a 2d array of type E with 2 additional rows and columns. These additional rows are used to find 
	 * the neighbors of a specified tile without throwing Array out of bounds. 
	 * For example: given 2 rows and 2 columns Grid will create the following
	 *    Columns 
	 *  X | 0 | 1 | 2 | 3 
	 *  ------------------
	 *R 0 |0,0|0,1|0,2|0,3
	 *o ------------------ 
	 *w 1 |1,0|1,1|1,2|1,3
	 *s ------------------
	 *  2 |2,0|2,1|2,2|2,3
	 *  ------------------
	 *  3 |3,0|3,1|3,2|3,3
	 * @param rows the amount of accessible rows in the grid. 
	 * @param cols the amount of accessible columns in the grid. 
	 */
	public Grid(int rows, int cols)
	{
		if(rows <= 0 || cols <= 0)
			throw new IllegalArgumentException("invalid inputs");
		arr = new Object[rows + 2][cols + 2]; 
	}
	
	/**
	 * This constructor creates a Grid with the he given 2d array. The object[] should be the same as type E 
	 * to avoid cast exceptions. The given grid should not be null and should at least have length of 1,1
	 * @param given the given Object[][]
	 */
	public Grid(Object[][] given)
	{
		if(given == null || given.length == 0 || given[0].length == 0)
			throw new IllegalArgumentException();
		arr = new Object[given.length + 2][given[0].length + 2];
		for(int i = 0; i<given.length; i++)
			for(int j=0; j<given[i].length; j++)
				arr[i+1][j+1] = given[i][j];
	}
	
	/**
	 * This get method returns the object at the specified row and column position
	 * The parameter must between 0 >= i < rows and 0 >= j < column. This method will throw
	 * Illegal Argument exception if the specified condition are out of the range.
	 * @param i the row position i must be between        0 >= i < rows
	 * @param j the column position                       0 >= j < column
	 * @return the object at the specified position
	 */
	@SuppressWarnings("unchecked")
	public E get(int i, int j)
	{
		if(!validPosition(i,j))
			throw new IllegalArgumentException();
		return (E) arr[i+1][j+1];
	}
	
	/**
	 * Creates a list of objects that are the same as type. This means that all objects
	 * that are the same as type will be returned as an array list
	 * @param type The type that the method will find similar object to
	 * @return an Array List with all the objects that are similar
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<E> getSimilar(E type)
	{
		ArrayList<E> similarTypes = new ArrayList<E>();
		for(int i = 0; i < getRows(); i++)
			for(int j = 0; j < getCols(); j++)
				if(type != null && type.equals(arr[i+1][j+1]))
					similarTypes.add((E) arr[i+1][j+1]);
				else if( type == arr[i+1][j+1])
					similarTypes.add((E) arr[i+1][j+1]);
		return similarTypes;
	}
	
	/**
	 * returns the entire grids as an array 
	 * The returned array is the stored array without the outer edge
	 * @return an Object[][] with the actual determined length and width
	 */
	public Object[][] getGrid()
	{
		Object[][] temp = new Object[getRows()][getCols()];
		for(int i=0; i<getRows(); i++)
			for(int j=0; j<getCols(); j++)
				temp[i][j] = arr[i+1][j+1];
		return temp;
	}
	
	/**
	 * Set the current position at i and j to the given object. 
	 * the i + 1 and j + 1 is used to create a border around the grid. 
	 * Ignore the border and treat i and j as it actual position
	 * @param i rows position 0 <= i < rows;
	 * @param j columns position 0 <= j < columns;
	 * @param obj and object of type E that grid at position i and j will be set to. 
	 */
	public void set(int i, int j, E obj)
	{
		if(!validPosition(i,j))
			throw new IllegalArgumentException();
		arr[i+1][j+1] = obj;
	}
	
	/**
	 * sets every position in the array not counting the borders. All elements will be set to the given object
	 * @param obj the given object. This should be type E. Null is accessible
	 */
	public void setAll(E obj)
	{
		for(int i = 0; i < getRows(); i++)
			for(int j = 0; j < getCols(); j++)
				arr[i+1][j+1] = obj;
	}
	
	/**
	 * gets and array list with all the position stored as arrays. This arrays are in the position of [i,j] 
	 * @param type Object of type E or null that the method will find all positions of
	 * @return An array list with all positions similar to the Type This will return null if nothing is found
	 */
	public ArrayList<int[]> positionsOf(E type)
	{
		ArrayList<int[]> similarTypes = new ArrayList<int[]>();
		for(int i = 0; i < getRows(); i++)
			for(int j = 0; j < getCols(); j++)
				if(type != null && type.equals(arr[i+1][j+1]))
					similarTypes.add(new int[] {i,j});
				else if( type == arr[i+1][j+1])
					similarTypes.add(new int[] {i,j});
		if(similarTypes.isEmpty())
			return null;
		return similarTypes;
	}
	
	/**
	 * finds the first position of the given type and returns it as an array in the format [i,j]
	 * @param type the type the method will look for. This can be null
	 * @return an array with the the i and j position of the first instance found
	 */
	public int[] positionOf(E type)
	{
		for(int i = 0; i < getRows(); i++)
			for(int j = 0; j < getCols(); j++)
				if(type != null && type.equals(arr[i+1][j+1]))
					return new int[] {i,j};
				else if( type == arr[i+1][j+1])
					return new int[] {i,j};
		return null;
	}
	
	/**
	 * removes the object at the position i and j. This will be set to null
	 * @param i rows position 0 <= i < rows;
	 * @param j columns position 0 <= j < columns;
	 * @return the object at that position before it was removed
	 */
	@SuppressWarnings("unchecked")
	public E remove(int i, int j)
	{
		if(!validPosition(i,j))
			throw new IllegalArgumentException();
		Object t = arr[i+1][j+1];
		arr[i+1][j+1] = null;
		return (E) t;
	}
	
	/**
	 * checks if the the grid contains the current object
	 * @param obj a object with type E that the method will check if it contains or not
	 * @return true if it contains the object or false if it doesn't
	 */
	public boolean contains(E obj)
	{
		for(int i = 0; i < getRows(); i++)
			for(int j = 0; j < getCols(); j++)
				if(obj != null && obj.equals(arr[i+1][j+1]))
					return true;
				else if( obj == arr[i+1][j+1])
					return true;
		return false;
	}
	
	/**
	 * This method finds all the Direct neighbors of the given i and j position. This method handles edges because of the border
	 * edges will be set as null. The neighbors are directly next to the given position. This means that the corner will not could
	 * this is shown in the following:
	 * ___________
	 * |  | X |  |   X are the neighbors of i and j
	 * -----------
	 * |X |i,j| X|
	 * -----------
	 * |  | X |  |
	 * -----------
	 * @param i rows position 0 <= i < rows;
	 * @param j columns position 0 <= j < columns;
	 * @return an array list with all the direct neighbors. 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Pair<E,Integer>> getDirectNeighbors(int i, int j)
	{
		if(!validPosition(i,j))
			throw new IllegalArgumentException();
		ArrayList<Pair<E,Integer>> neighbors = new ArrayList<>();
		neighbors.add(new Pair<E,Integer>((E) arr[i][j+1]  ,   0));   // i-1,j   North
		neighbors.add(new Pair<E,Integer>((E) arr[i+1][j+2],  90));   // i,j+1   East
		neighbors.add(new Pair<E,Integer>((E) arr[i+2][j+1], 180));   // i+1,j   South 
		neighbors.add(new Pair<E,Integer>((E) arr[i+1][j]  , -90));   // i,j-1   West
		
		return neighbors;
	}
	
	/**
	 * This method finds all the corner neighbors of the given i and j position. This method handles edges because of the border
	 * edges will be set as null. The neighbors are the corners of the given position. 
	 * this is shown in the following:
	 * ___________
	 * |X |   | X|   X are the neighbors of i and j
	 * -----------
	 * |  |i,j|  |
	 * -----------
	 * |X |   | X|
	 * -----------
	 * @param i rows position 0 <= i < rows;
	 * @param j columns position 0 <= j < columns;
	 * @return an array list with all the direct neighbors. 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Pair<E,Integer>> getCornerNeighbors(int i, int j)
	{
		if(!validPosition(i,j))
			throw new IllegalArgumentException();
		ArrayList<Pair<E,Integer>> neighbors = new ArrayList<>();
		neighbors.add(new Pair<E,Integer>((E) arr[i][j+2]  ,  45));   // i-1 j+1 North East
		neighbors.add(new Pair<E,Integer>((E) arr[i][j]    , -45));   // i-1 j-1 North West
		neighbors.add(new Pair<E,Integer>((E) arr[i+2][j+2], 135));   // i+1 j+1 South East
		neighbors.add(new Pair<E,Integer>((E) arr[i+2][j]  ,-135));   // i+1 j-1 South West
		return neighbors;
	}
	
	/**
	 * returns the number of actual rows in the grid. This number is not the length of the started 2d array. 
	 * It calculates as 2d array length - 2
	 * When visualized the rows are vertical
	 * @return the number of rows in the grid. 
	 */
	public int getRows()
	{ return arr.length - 2; }
	
	/**
	 * returns the number of actual columns in the grid. This number is not the length of the started 2d array. 
	 * It calculates as 2d array length - 2
	 * When visualized the columns are horizontal
	 * @return the number of columns in the grid. 
	 */
	public int getCols()
	{ return arr[0].length -2; }
	
	/**
	 * This method check if the given i and j is an actual position in the grid. 
	 * @param i the i position
	 * @param j the j position
	 * @return returns true if 0 <= i < rows and 0 <=j < columns. This will return false otherwise
	 */
	public boolean validPosition(int i, int j)
	{ return i >= 0 && i < this.getRows() && j >= 0 && j < this.getCols(); }
	
	public String toString()
	{
		String displayGrid = "";
		for(int i = 0; i < getRows(); i++)
		{
			for(int j = 0; j < getCols(); j++)
				displayGrid += arr[i+1][j+1] + " ";
			displayGrid += "\n";
		}
		return displayGrid;			
	}
}