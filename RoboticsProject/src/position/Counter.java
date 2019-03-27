package position;

import java.util.HashMap;


public class Counter<E> 
{
	private HashMap<E, Integer> data;
	
	/**
	 * Constructor to create a counter. A counter is keeps a running count as you add new types in it. A type can have a negative count.
	 */
	public Counter()
	{ data = new HashMap<E,Integer>(); }
	
	/**
	 * This methods adds the type into the count. If it contains it already it count will be increased by amount. If it does not contain type
	 * the count for the given type will be set to amount.
	 * @param type the current Object of type E that will be added into the running count
	 * @param amount How much you want to add to that value. This can be negative or positive
	 */
	public void add(E type, int amount)
	{
		if(data.containsKey(type))
			data.put(type, data.get(type)+amount);
		else
			set(type,amount);
	}
	
	/**
	 * This is a convenience method. Automatically adds 1 to the count
	 * @param type the current Object of type E that will be added into the running count
	 */
	public void add(E type)
	{ add(type,1); }
	
	/**
	 * this is a convenience method. This method subtracts amount from the current amount already stored. The value may go negative
	 * @param type the current Object of type E that will be subtracted into the running count
	 * @param amount How much you want to subtract to that value. This can be negative or positive
	 */
	public void sub(E type, int amount)
	{ add(type, amount * -1); }
	
	/**
	 * This is a convenience method. This method subtracts 1 from the count
	 * @param type the current Object of type E that will be added into the running count
	 */
	public void sub(E type)
	{ sub(type,1); }
	
	/**
	 * this method retrieves the amount of occurrences type currently has. It may return null if it doesn't contain the given type
	 * @param type an object of type E that this method will retrieve how many times it has been added to this class
	 * @return null or and Integer class.
	 */
	public Integer get(E type)
	{ return data.get(type); }
	
	/**
	 * This method sets the given type to the given amount. Amount can be negative and type does not need to be already in the class
	 * @param type an Object with its count set to amount
	 * @param amount the amount it will be set to.
	 */
	public void set(E type, int amount)
	{ data.put(type,amount); }
	
	/**
	 * This method makes sure that all entries inside all between minimum and maximum. Values outside of this range are trim so they are within it.
	 * @param min the minimum value it can be
	 * @param max the maximum value it can be
	 */
	@SuppressWarnings("unchecked")
	public void range(int min, int max)
	{ 
		for(Object current : data.entrySet().toArray())
		{
			int size = data.get(current);
			if(size < min)
				set((E)current,min);
			else if(size > max)
				set((E)current,max);
		}
	}
	
	/**
	 * This method makes sure that all entries are greater than the minimum. If they are out of bounds they will be set to minimum
	 * @param min the minimum value it can be
	 */
	@SuppressWarnings("unchecked")
	public void floor(int min)
	{
		for(Object current : data.entrySet().toArray())
			if(data.get(current)< min)
				set((E)current,min);
	}
	
	/**
	 * This method makes sure that all entries are less that the maximum value. If they are out of bound ihey will be set to maximum
	 * @param max the maximum value it can be
	 */
	@SuppressWarnings("unchecked")
	public void ceiling(int max)
	{
		for(Object current : data.entrySet().toArray())
			if(data.get(current)> max)
				set((E)current,max);
	}
	
	/**
	 * checks if the counter contains the given type
	 * @param type that will check if it contains it or not
	 * @return true for yes or false for no
	 */
	public boolean contains(E type)
	{ return data.containsKey(type); }
	
	/**
	 * clears all the data from this counter
	 */
	public void clear()
	{ data.clear(); }
	
	/**
	 * removes the current type from the counter
	 * @param type the type that will be removed
	 */
	public void remove(E type)
	{ data.remove(type); }
	
	/**
	 * checks if a given type has a negative count
	 * @param type the type that will be checked
	 * @return true if it is negative and false if it does not contain type or positive
	 */
	public boolean negativeCount(E type)
	{ return data.containsKey(type) && data.get(type) < 0; }
	
	/**
	 * checks if a given type has a positive count
	 * @param type the type that will be checked
	 * @return true if it is positive and false if it does not contain type or negative
	 */
	public boolean positiveCount(E type)
	{ return !negativeCount(type); }
	
	/**
	 * Retrieves the data as a HashMap<E,Integer> and returns it. This is used to run other method not provided
	 * @return the data stored as a HashMap
	 */
	public HashMap<E, Integer> getData()
	{ return data; }
	
	/**
	 * get how many entries are in the counter
	 * @return the amount of entries in this counter
	 */
	public int getDataSize()
	{ return data.size(); }
	
	/**
	 * merges the current and a different counter into 1
	 * @param other another counter<E>
	 */
	public void merge(Counter<E> other)
	{ data.putAll(other.data); }
	
	/**
	 * returns a String with all the entries with its count
	 */
	public String toString()
	{ 
		String r= "";
		Object[] positionTypes = data.keySet().toArray();
		for(int i=0; i<positionTypes.length; i++)
			r += positionTypes[i].toString() + " " + data.get(positionTypes[i]).toString() + " ";
		return r;
	}
}
