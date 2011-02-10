public class Edge
{
    public Vertex u, v;
    public int distance;

    public Edge(Vertex u, Vertex v, int distance)
    {
        this.u = u;
        this.v = v;
        this.distance = distance;
    }
    
    public Vertex getU()
    {
    	return u;
    }
    
    public Vertex getV()
    {
    	return v;
    }
    
    public int getCost()
    {
    	return distance;
    }
}