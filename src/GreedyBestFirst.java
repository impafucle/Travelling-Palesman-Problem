import java.util.ArrayList;
import java.util.Iterator;

public class GreedyBestFirst 
{
	public AdjSetGraph graph = new AdjSetGraph();
	public ArrayList<Vertex> vertices, tour = new ArrayList<Vertex>();
	public ArrayList<Edge> specifiedEdges = new ArrayList<Edge>();
	public ArrayList<ArrayList<Vertex>> tours = new ArrayList<ArrayList<Vertex>>();
	public int cost, totalCost, caseSize, vertexCount = 0;
	public Vertex v1, v2;
	public Edge e1;
	public int[] costs;

	public GreedyBestFirst(AdjSetGraph graph, int caseSize, String fileName)
	{
		costs = new int[caseSize];
		this.caseSize = caseSize;
		vertices = new ArrayList<Vertex>();
		this.graph = graph;
		vertices = graph.getVertices();
		for(int a = 0; a < caseSize; a ++)
		{
			vertexCount = a;
			expandNode(a);
			tours.add(tour);
			tour = new ArrayList<Vertex>();
			totalCost = 0;
		}           
		new TourWriter(tours, costs, fileName);
	}

	public void expandNode(int count)
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
}