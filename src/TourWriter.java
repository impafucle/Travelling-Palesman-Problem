import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TourWriter 
{
	public int totalCost, index = 0;
	public ArrayList<Vertex> tour = new ArrayList<Vertex>();
	public String fileName;
	
	public TourWriter(ArrayList<ArrayList<Vertex>> tours, int[] costs, String fileName)
	{
		this.fileName = ("tour" + fileName + ".txt");
		totalCost = costs[0];
		for(int count = 0; count < costs.length; count ++)
		{
			if(totalCost < costs[count])
			{
				totalCost = costs[count];
				index = count;
			}
		}
		tour = (tours.get(index));
		int size = tour.size();
		size --;	
		tour.remove(size);		
		try
		{			
			FileWriter fstream = new FileWriter(this.fileName);
	        BufferedWriter out = new BufferedWriter(fstream);	 
	        Iterator<Vertex> myIter = tour.iterator();
	        out.write("NAME = " + fileName + ",");
	        out.newLine();
	        out.write("TOURSIZE = " + costs.length + ",");
	        out.newLine();
	        out.write("LENGTH = " + totalCost + ",");
	        out.newLine();
	        while(myIter.hasNext())
	        {
	        	index = myIter.next().getid();
	        	String output = Integer.toString(index);
	        	out.write(output);
	        	if(myIter.hasNext())
	        	{
	        		out.write(",");
	        	}
	        }	        
	        out.close();
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		System.out.println("The tourfile has been written. Algorithm Complete");
	}
}