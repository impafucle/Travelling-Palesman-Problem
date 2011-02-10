import java.util.ArrayList;
import java.util.Iterator;

public class HillClimbing
{	
	public AdjSetGraph graph = new AdjSetGraph();
	public ArrayList<Vertex> vertices, tour, hillTour = new ArrayList<Vertex>();
	public ArrayList<Edge> specifiedEdges = new ArrayList<Edge>();
	public ArrayList<ArrayList<Vertex>> tours = new ArrayList<ArrayList<Vertex>>();
	public int cost, totalCost, caseSize, vertexCount, index, temp, count = 0;
	public Vertex v1, v2;
	public Edge e1;
	public int[] costs, runningCosts;
	
	public HillClimbing(AdjSetGraph graph, int caseSize, String fileName)
	{		
		costs = new int[caseSize];
		runningCosts = new int[caseSize];
		this.caseSize = caseSize;
		this.graph = graph;
		vertices = graph.getVertices();		
		expandNode(caseSize / 2);
		hillTour = tour;
		hillClimb();		
		tours.add(tour);
		new TourWriter(tours, costs, fileName);
	}
	
	public void expandNode(int count)
	{
		tour = new ArrayList<Vertex>();
		tours = new ArrayList<ArrayList<Vertex>>();
		for(int a = 0; a < (caseSize - 1); a ++)
		{
		    cost = 15000;
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
					if(e1.getCost() <= cost)
					{
						v1 = e1.getV();
						cost = e1.getCost();
					}
				}
			}
			runningCosts[a] = cost;
			tour.add(v1);
			if(vertexCount == (caseSize - 1))
				vertexCount = 0;
			else
				vertexCount ++ ;			
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
	
	public void hillClimb()
	{		
		Vertex v1 = null;		
		for(int a = 1; a < runningCosts.length - 1; a++)
		{					
			v1 = hillTour.get(a);								
			hillTour.set(a, hillTour.get(a + 1));
			hillTour.set((a + 1), v1);		
			if(getCost(hillTour) < getCost(tour))
			{
				tour = hillTour;
			}
			else
				hillTour = tour;
		}
		totalCost = 0;
		for(int a = 0; a < caseSize; a++)
		{
			specifiedEdges = graph.getEdges(tour.get(a));
			Iterator<Edge> myIter = specifiedEdges.iterator();
			while(myIter.hasNext())
			{
				e1 = myIter.next();
				if((e1.getU().getid() == tour.get(a).getid()) && (e1.getV().getid() == tour.get(a + 1).getid()))
				{				
					cost = e1.getCost();
				}
			}		
			totalCost = totalCost + cost;
		}
		costs[0] = totalCost;
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