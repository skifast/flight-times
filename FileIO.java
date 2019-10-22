import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class FileIO {
	//I didn't write a write to file function, but i feel like being able to
	//save the flight times could be an extension 
	
	//reads through the file and returns an array list of all of
	//the flight info nodes
	public ArrayList<FlightInfoNode> read(String fileName){
		
		BufferedReader br = null;
		
		//new array list
		ArrayList<FlightInfoNode> allFlights = new ArrayList<FlightInfoNode>();

		try{
			File file = new File(fileName);
			
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String line;
			
			while((line= br.readLine()) != null){
				//the files provided divide up the information with
				//semi colons
				String[] currentLine = line.split(";");
				//create a node from the line information
				FlightInfoNode x = new FlightInfoNode(currentLine[0], Integer.parseInt(currentLine[1]));
				//add the node to the array list
				allFlights.add(x);
			}

		} catch (IOException ioe){
			//the method calling this method has a condition
			//where if it recieves a null value, it knows that
			//the file given does not exist
			return null;
		}
		finally
		{
			try{
				if(br!=null)
					br.close();
			}catch(Exception ex){
				System.out.println("Error in closing the BufferedReader text");
			}
		}
		return allFlights;

	}
	

	
}