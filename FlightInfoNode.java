import java.util.Arrays;

public class FlightInfoNode{
	/*Methods included are getter and setter methods.
	 * This class also includes the information held 
	 * by the nodes */
	int time;
	int runway;
	String airline;
	String conflictFlights;
	FlightInfoNode rightChild;
	FlightInfoNode leftChild;

	
	public FlightInfoNode(String airline, int time){
		this.airline = airline;
		this.time = time;
		
		//these will all be added later
		this.conflictFlights = "";
		this.rightChild = null;
		this.leftChild = null;
		this.runway = -1;
	}
	
	public FlightInfoNode(){
		this.airline = null;
		this.conflictFlights = null;
		this.time = 0;
		this.runway = 0;
		this.rightChild = null;
		this.leftChild = null;
	}
	
	public int getTime(){
		return time;
	}
	
	public void setTime(int givenTime){
		this.time = givenTime;
	}
	
	public int getRunway(){
		return runway;
	}
	
	public void setRunway(int givenRunway){
		this.runway = givenRunway;
	}
	
	public String getAirline(){
		return airline;
	}
	
	public void setAirline(String givenAirline){
		this.airline = givenAirline;
	}
	
	public  String getConflictFlights(){
		return conflictFlights;
	}
	
	//sometimes there is more than one conflict so this
	//string is appended to 
	public void setConflictFlights(String givenConflict){
		this.conflictFlights += givenConflict; 
	}
	
	public FlightInfoNode getRightChild(){
		return rightChild; 
	}
	
	public void setRightChild(FlightInfoNode givenRight){
		this.rightChild = givenRight;
	}
	
	public FlightInfoNode getLeftChild(){
		return leftChild;
	}
	
	public void setLeftChild(FlightInfoNode givenLeft){
		this.leftChild = givenLeft; 
	}
	
	
}
