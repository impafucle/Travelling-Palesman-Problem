import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class FileOperations
{    
    public String read, fileName, fileChoice, algorithmChoice;
    public int caseSize, count, count2, vertexCount, temp2;
    public File fFile;
    public AdjSetGraph graph = new AdjSetGraph();
    public ArrayList<Vertex> vertices = new ArrayList<Vertex>();  
    public Vertex v1, v2;
    public BufferedReader reader;
        
    public static void main(String[] args) throws FileNotFoundException
    {       
        new FileOperations();
    }
    
    public FileOperations() throws FileNotFoundException
    {    
    	reader = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Please enter the name of the graph file including the extension.");
    	System.out.println("E.g. file175.txt");
    	fileChoice = "Graph Files/";
    	try
    	{
    		fileChoice += reader.readLine();
    	}
    	catch(IOException e)
		{
			System.out.println("Client Input Error");
		}
    	
    	System.out.println("Now please enter either: A*, Hill Climbing or Greedy Best First");
    	try
    	{
    		algorithmChoice = reader.readLine();
    	}
    	catch(IOException e)
		{
			System.out.println("Client Input Error");
		}
    	if(!algorithmChoice.equals("A*") && !algorithmChoice.equals("Hill Climbing") && !algorithmChoice.equals("Greedy Best First"))
    	{
    		System.out.println("Your algorithm choice was invalid. Please re-run the application");
    		System.exit(-1);
    	}
        fFile = new File(fileChoice);
        Scanner scanner = new Scanner(fFile);       
        read = scanner.nextLine();
        String[] temp = read.split("[NAME =,]+");
        fileName = temp[1];
        
        read = scanner.nextLine();
        temp = read.split("[SIZE =h,]+");
        caseSize = Integer.parseInt(temp[1]);
        count = 0;
        count2 = 1;
        vertexCount = 0;   
        temp2 = vertexCount + 1;
        addVertices();     
        vertices = graph.getVertices();
        try
        {
        	while (scanner.hasNextLine())
            {
        		processLine(scanner.nextLine());
            }
        }
        finally
        {
        	scanner.close();
        }
        if(algorithmChoice.equals("A*"))
        	new AStar(graph, caseSize, fileName);
        else
        	if(algorithmChoice.equals("Hill Climbing"))
        		new HillClimbing(graph, caseSize, fileName);
        	else
        		new GreedyBestFirst(graph, caseSize, fileName);
    }
    
    public void processLine(String aLine)
    {           
        String[] tokens = aLine.split(",");
        for (int i = 0; i < tokens.length; i++)
        {        
            int weight = Integer.parseInt(tokens[i]);
            addEdges(weight);            
            count ++;
            if(temp2 == caseSize)
        	{
        		vertexCount ++;
        		temp2 = vertexCount + 1;
        	}
        }
    }

    public void addVertices()
    {
        for (count = 1; count < (caseSize + 1); count++)
        {
            graph.add(new Vertex(count));
        }
    }
    
    public void addEdges(int distance)
    {
    	v1 = vertices.get(vertexCount);
        v2 = vertices.get(temp2);
        graph.add(new Edge(v1, v2, distance));
        graph.add(new Edge(v2, v1, distance));
        temp2 ++;
    }
}