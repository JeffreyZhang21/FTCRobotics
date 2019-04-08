package display;

import grid.Field;

public abstract class Display 
{
	private Field field;
	public Display(Field field)
	{ this.field = field; }
	
	public abstract void init();
	
	public abstract String display();
	
	public String toString()
	{ return field.toString(); }
	
	public Field getField()
	{ return field; }
}
