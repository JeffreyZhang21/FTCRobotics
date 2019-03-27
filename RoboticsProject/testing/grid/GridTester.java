package grid;

import java.util.Arrays;

public class GridTester 
{
	public static void main (String[] args)
	{
		Grid<String> field = new Grid<>(10,10);
		
		//field.setAll("food");
		for(int i=0; i<field.getRows(); i++)
			for(int j=0; j<field.getCols(); j++ )
				field.set(i, j, "" +  (int) (Math.random() * 10));
		System.out.println(Arrays.toString(field.getDirectNeighbors((int) (Math.random() * 10),(int) (Math.random() * 10)).toArray()));
		System.out.println(Arrays.toString(field.getCornerNeighbors((int) (Math.random() * 10),(int) (Math.random() * 10)).toArray()));
		System.out.println(field.toString());
	}
}
