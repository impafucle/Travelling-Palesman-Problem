import java.util.ArrayList;
import java.util.Iterator;

public class AStar
{	
	public AdjSetGraph graph = new AdjSetGraph();
	public ArrayList<Vertex> vertices, tour = new ArrayList<Vertex>();
	public ArrayList<Edge> specifiedEdges, tempEdges = new ArrayList<Edge>();
	public ArrayList<ArrayList<Vertex>> tours = new ArrayList<ArrayList<Vertex>>();
	public int count, cost, totalCost, caseSize, vertexCount, heuristicCost, temp, index = 0;
	public Vertex v1, v2;
	public Edge e1;
	public int[] costs, runningCosts;
	
	public AStar(AdjSetGraph graph, int caseSize, String fileName)
	{		
		costs = new int[caseSize];
		runningCosts = new int[caseSize];
		this.caseSize = caseSize;
		this.graph = graph;
		vertices = graph.getVertices();	
		GreedyBestFirst();
		for(int count = 0; count < costs.length; count ++)
		{
			if(totalCost < costs[count])
			{
				totalCost = costs[count];
				index = count;
			}
		}
		tour = (tours.get(index));
		index = tour.get(0).getid();
		costs = new int[caseSize];
		expandNode(index);
		temp = getCost(tour);
		temp ++;
		tours.add(tour);	
		temp --;
		costs[0] = temp;
		for(int d = 1; d < costs.length; d++)
		{		
			costs[d] = temp;
		}	
		new TourWriter(tours, costs, fileName);
	}
	
	public void expandNode(int count)
	{
		tour = new ArrayList<Vertex>();
		tours = new ArrayList<ArrayList<Vertex>>();
		for(int a = 0; a < (caseSize - 1); a ++)
		{			
		    cost = 1500000000;
		    heuristicCost = cost;
			if(a == 0)
			{
				tour.add(vertices.get(count - 1));
				v1 = tour.get(0);
			}			
			specifiedEdges = graph.getEdges(v1);
			Iterator<Edge> myIter = specifiedEdges.iterator();
			while(myIter.hasNext())
			{
				e1 = myIter.next();
				if(!tour.contains(e1.getV()))
				{
					if(getHeuristic(e1) < heuristicCost)
					{
						v1 = e1.getV();
						heuristicCost = temp;					
					}
				}
			}
			runningCosts[a] = cost;
			tour.add(v1);					
			totalCost = totalCost + cost;
		}
		v1 = tour.get(0);
		tour.add(v1);
		v1 = tour.get(caseSize - 1);
		v2 = tour.get(caseSize);
		specifiedEdges = graph.getEdges(v1);
		Iterator<Edge> myIter3 = specifiedEdges.iterator();
		while(myIter3.hasNext())
		{
			e1 = myIter3.next();
			if(e1.getV().getid() == v2.getid())
			{
				 cost = e1.getCost();
			}
		}
		runningCosts[caseSize - 1] = cost;
		totalCost = totalCost + cost;
		costs[0] = totalCost;		
	}
	
	public int getHeuristic(Edge e1)
	{		
		cost = e1.getCost();
		v1 = e1.getV();
		tempEdges = graph.getEdges(v1);	
		Iterator<Edge> myIter = tempEdges.iterator();
		while(myIter.hasNext())
		{
			e1 = myIter.next();
			if(e1.getV().getid() == vertices.get(0).getid())
			{
				temp  = cost + e1.getCost();
			}
		}
		return temp;
	}
	
	public void GreedyBestFirst()
	{	
		for(int a = 0; a < caseSize; a ++)
		{
			vertexCount = a;
			expandANode(a);
			tours.add(tour);
			tour = new ArrayList<Vertex>();
			totalCost = 0;
		}		
	}

	public void expandANode(int count)
	{
		for(int a = 0; a < (caseSize - 1); a ++)
		{
		    cost = 15000;
			if(a == 0)
			{
				tour.add(vertices.get(vertexCount));
				v1 = tour.get(0);
			}
			specifiedEdges = graph.getEdges(v1);
			Iterator<Edge> myIter = specifiedEdges.iterator();
			while(myIter.hasNext())
			{
				e1 = myIter.next();
				if(!tour.contains(e1.getV()))
				{
					if(e1.getCost() <= cost)
					{
						v1 = e1.getV();
						cost = e1.getCost();
					}
				}
			}
			tour.add(v1);
			if(vertexCount == (caseSize - 1))
			{
				vertexCount = 0;
			}
			else
			{
				vertexCount ++ ;
			}
			totalCost = totalCost + cost;
		}
		v1 = tour.get(0);
		tour.add(v1);
		v1 = tour.get(caseSize - 1);
		v2 = tour.get(caseSize);
		specifiedEdges = graph.getEdges(v1);
		Iterator<Edge> myIter3 = specifiedEdges.iterator();
		while(myIter3.hasNext())
		{
			e1 = myIter3.next();
			if(e1.getV().getid() == v2.getid())
			{
				 cost = e1.getCost();
			}
		}
		totalCost = totalCost + cost;
		costs[count] = totalCost;
	}
	
	public int getCost(ArrayList<Vertex> tourList)
	{
		temp = 0; 
		count = 0;
		Iterator<Vertex> myIter = tourList.iterator();
		while(myIter.hasNext())
		{		    
			Vertex v1 = myIter.next();
			specifiedEdges = graph.getEdges(v1);
			Iterator<Edge> myIter2 = specifiedEdges.iterator();
			while(myIter2.hasNext())
			{
				Edge e1 = myIter2.next();
				if((count + 1) <= caseSize)
				if(e1.getV().getid() == tourList.get(count + 1).getid())
				{
					temp = temp + e1.getCost();
				}
			}						
			count ++;			
		}		
		return temp;
	}
}