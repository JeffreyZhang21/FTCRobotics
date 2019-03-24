package position;

public enum PositionTypes 
{
	BARRIER( 0 , 0 , 0 ),
	HAZARD (255,255, 0 ),
	START  ( 0 ,255, 0 ),
	END    ( 0 ,100, 0 ),
	PATH   (255, 0 ,255),
	EMPTY  (255,255,255);
	
	
	private int r, g, b;
	PositionTypes(int r, int g, int b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public int getR()
	{ return r; }
	
	public int getG()
	{ return g; }
	
	public int getB()
	{ return b; }
}
