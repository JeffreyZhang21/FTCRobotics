package pathFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import grid.Grid;
import grid.Pair;
import position.Position;
import position.PositionTypes;


public class AStar 
{
	private Grid<Position> field;
	private HashSet<Position> avoid;
	private Position start, end;
	private ArrayList<Position> path;
	private ArrayList<Integer> anglePath;
	
	public AStar(Grid<Position> field) 
	{ 
		this.field = field; 
		start = field.get(0, 0);
		end = field.get(field.getRows()-1, field.getCols()-1);
		avoid = new HashSet<Position>();
	}
	
	public AStar(Grid<Position> field , Position start, Position end)
	{
		this.field = field;
		this.start = start;
		this.end = end;
		avoid = new HashSet<Position>();
	}
	
	public boolean pathFind(boolean corners)
	{
		HashSet<Position> openSet = new HashSet<>();
		HashSet<Position> closedSet = new HashSet<>();
		HashMap<Position, Position> cameFrom = new HashMap<>();
		HashMap<Position, Integer > cameFromAngle = new HashMap<>();
	    HashMap<Position, Double[]> score = new HashMap<>();
	    
		for(int i=0; i < field.getRows(); i++) 
			for(int j=0; j < field.getCols(); j++) 
				score.put(field.get(i, j), new Double[] { Double.MAX_VALUE,Double.MAX_VALUE } );
			
		openSet.add(start);
		score.put(start, new Double[] {heuristicCost(start,end), 0.0});
		
		while(!openSet.isEmpty()) 
		{
			Position current = findMin(openSet, score);
	
			if(current.equals(end))
				return reconstructPath(current,cameFrom,cameFromAngle);
			
			openSet.remove(current);
			closedSet.add(current);
			
			ArrayList<Pair<Position,Integer>> neighbors = getNeighbors(current,corners);
			
			for(int i=0; i<neighbors.size(); i++)
			{
				Position neighbor = neighbors.get(i).getKey();
				
				if(neighbor == null || closedSet.contains(neighbor) || avoid.contains(neighbor))
					continue;
				
				double tempG = score.get(current)[1] + heuristicCost(neighbor,current);
				
				if(!openSet.contains(neighbor))
					openSet.add(neighbor);
				else if(tempG >= score.get(neighbor)[1])
					continue;
				
				cameFrom.put(neighbor, current);
				cameFromAngle.put(neighbor,neighbors.get(i).getValue());
				score.put(neighbor, new Double[] {tempG + heuristicCost(neighbor,current), tempG });
			}
		}
		return false;
	}
	
	private boolean reconstructPath(Position current, HashMap<Position, Position> cameFrom, HashMap<Position, Integer> cameFromAngle)
	{
		path = new ArrayList<>();
		anglePath = new ArrayList<>();
		path.add(current);
		while(cameFrom.containsKey(current))
		{
			path.add(cameFrom.get(current));
			anglePath.add(cameFromAngle.get(current));
			current = cameFrom.get(current);
		}
		Collections.reverse(path);
		Collections.reverse(anglePath);
		return true;
	}
		
	private Position findMin(HashSet<Position> openSet, HashMap<Position, Double[]> score)
	{
		Position current = null;
		double min = Double.MAX_VALUE;
		for(Position c : openSet)
		{
			double fScoreNow = score.get(c)[0];
			if(fScoreNow < min) 
			{
				min = fScoreNow;
				current = c;
			}
		}
		
		return current;
	}
	
	private ArrayList<Pair<Position, Integer>> getNeighbors(Position current, boolean corners)
	{
		int[] currentPos = field.positionOf(current);
		ArrayList<Pair<Position,Integer>> neighbors = field.getDirectNeighbors(currentPos[0],currentPos[1]);
		if(corners)
			neighbors.addAll(field.getCornerNeighbors(currentPos[0], currentPos[1]));
		return neighbors;
	}
	
	public double heuristicCost(Position a, Position b)
	{
		return Math.abs(a.getX()-b.getX()) + Math.abs(a.getY()-b.getY());
		//return Position.distance(a,b);
	}
	
	public int getPathLength()
	{ return path.size();}
	
	public void setObstacles(HashSet<Position> avoid )
	{ this.avoid = avoid; }
	
	public void addObstacle(Position pos)
	{  avoid.add(pos); }
	public void addAllTypeToObstacle(PositionTypes[] types)
	{
		for(int i=0; i<field.getRows(); i++)
			for(int j=0; j<field.getCols(); j++)
				for(PositionTypes k : types)
					if(field.get(i, j).contains(k))
						avoid.add(field.get(i, j));	
	}

	public ArrayList<Position> getPath()
	{ return path; }
	
	public ArrayList<Integer> getPathAngles()
	{ return anglePath; }
	
	public HashSet<Position> getObstacles()
	{ return avoid; }
	
	public Position getStart()
	{  return start;  }
	
	public Position getEnd()
	{  return end;  }

	public void setStart(Position start) 
	{ this.start = start; }
	
    public void setEnd(Position end)
    {  this.end = end; }	
}
