import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;

public class AdjSetGraph
{
    public HashMap<Vertex, HashSet<Edge>> data = new HashMap<Vertex, HashSet<Edge>>();
    public HashSet<Edge> edges = new HashSet<Edge>();
    public Set<Vertex> keys = new HashSet<Vertex>();
    public ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    public ArrayList<Edge> specifiedEdges = new ArrayList<Edge>();
    public Vertex[] tempArray;

    public AdjSetGraph()
    {
    }

    public void add(Edge e)
    {
        edges = new HashSet<Edge>();
        Vertex u = e.getU();
        edges = data.get(u);
        edges.add(e);
        data.put(u, edges);
        
        edges = new HashSet<Edge>();
        Vertex v = e.getV();
        edges = data.get(v);
        edges.add(e);
        data.put(v, edges);       
    }

    public void add(Vertex v)
    {
        edges = new HashSet<Edge>();
        data.put(v, edges);
    }
    
    public ArrayList<Vertex> getVertices()
    {
        keys = new HashSet<Vertex>();
        keys = data.keySet();
        Iterator<Vertex> myIter = keys.iterator();
        while(myIter.hasNext())
        {
        	vertices.add(myIter.next());
        }      
        int size = vertices.size();
        tempArray = new Vertex[size + 1];
        for(int a = 0; a < vertices.size(); a ++)
        {
        	Vertex v1 = vertices.get(a);
        	tempArray[v1.getid()] = v1;
        }
        vertices.clear();
        for(int a = 1; a < tempArray.length; a ++)
        {
        	vertices.add(tempArray[a]);
        }
        return vertices;
    }
    
    public ArrayList<Edge> getEdges(Vertex v)
    {
        ArrayList<Edge> specifiedEdges = new ArrayList<Edge>();
    	edges = data.get(v);
    	Iterator<Edge> myIter = edges.iterator();
    	while(myIter.hasNext())
    	{
    		specifiedEdges.add(myIter.next());
    	}
    	return specifiedEdges;
    }
    
    public Set<Edge> getEdges()
    {
        edges = new HashSet<Edge>();
        keys = new HashSet<Vertex>();
        keys = data.keySet();
        Iterator<Vertex> myIter = keys.iterator();
        while (myIter.hasNext())
        {
            HashSet<Edge> temp = new HashSet<Edge>();
            temp = data.get(myIter.next());
            Iterator<Edge> myIter2 = temp.iterator();
            while (myIter2.hasNext())
            {
                   edges.add(myIter2.next());
            }
        }
        return edges;
    }
}