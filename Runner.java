import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Runner{
	static ArrayList <BinaryTree> runways;
	static BinaryTree lane0; 
	static Heaps MasterHeap;
	static FileIO fileProcesser;
	static ArrayList <String> fileNames;
	
	//the time between each scheduled flight must
	//be greater than this number
	int seconds = 4;
	static String fileName = "FlightTimes4.txt";
	
	public static void main(String[] args) throws IOException{
		//everything done in main is in a method that can be 
		//called again when the user wants to reset
		start();
	}
	
	
	public static void createUserInterface() throws IOException{
		
		JFrame frame = new JFrame("Airplane Scheduler");

		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		//adds the flight information from the file specified
		//in the beginning of this file
		JButton uploadFromFile = new JButton("Upload flights from file");
		uploadFromFile.setBounds(10, 125, 200, 50);
		
		JTextField uploadFileText = new JTextField();
		uploadFileText.setBounds(10, 90, 200, 30);
		
		JLabel fileTitle = new JLabel("Name of File");
		fileTitle.setBounds(13, 70, 200, 30);
		
		//this label lines up with the previous button
		JLabel label1 = new JLabel("");
		label1.setBounds(220, 130, 300, 20);
		
		uploadFromFile.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    selectionButtonPressed();
				  }

			private void selectionButtonPressed() {
				if(!searchForFile(uploadFileText.getText())){
					if(addFromFile(uploadFileText.getText())){
						label1.setText("These flights are now in the database.");
						fileNames.add(uploadFileText.getText());
					}
					else{
						label1.setText("This file does not exist.");
					}
				}
				else{	
					label1.setText("These flights are already in the database.");
				}
			}

			public void actionPerformed1(ActionEvent e) {
				
				
			} 
				} );
		
		
		//displays the next flight in the min heapified flight
		JButton next = new JButton("Display next flight");
		next.setBounds(10, 200, 200, 50);
		
		JLabel label2a = new JLabel("");
		label2a.setBounds(220, 200, 240, 20);
		JLabel label2b = new JLabel("");
		label2b.setBounds(220, 230, 240, 20);
		JLabel label2c = new JLabel("");
		label2c.setBounds(220, 260, 240, 20);
		JLabel label2d = new JLabel("");
		label2d.setBounds(220, 290, 240, 20);
		JLabel label2e = new JLabel("");
		label2e.setBounds(220, 320, 240, 20);
		JLabel label2f = new JLabel("");
		label2f.setBounds(220, 350, 240, 20);
		JLabel label2g = new JLabel("");
		label2g.setBounds(220, 380, 240, 20);
		JLabel label2h = new JLabel("");
		label2h.setBounds(220, 400, 240, 20);
		JLabel label2i = new JLabel("");
		label2i.setBounds(220, 420, 240, 20);
		
		
		
		next.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    selectionButtonPressed();
				  }

			private void selectionButtonPressed() {
				if(MasterHeap.getArrayCutoff() == -1 || MasterHeap.givenArray.length == 0){
					label2a.setText("There are no flights left to display");
					label2b.setText("");
					label2c.setText("");
					label2d.setText("");
					label2e.setText("");
					label2f.setText("");
					label2g.setText("");
					label2h.setText("");
					label2i.setText("");
				}
				else{
					//q will be the root passed to minheapify
					int q = MasterHeap.getRoot();
					MasterHeap.minHeapify(q);
					FlightInfoNode showThis = MasterHeap.extractMin();
					label2a.setText("The next flight is arriving...");
					label2b.setText("on the airline " + showThis.getAirline());
					label2c.setText("in " + showThis.getTime() + " seconds");
					label2d.setText("on runway number " + showThis.getRunway());

					//in order to allow room for all of the conflict flights in the really
					///big files, i had to create a ton of labels
					//so that's what these are
					if(showThis.getConflictFlights() != ""){
						if(30 < showThis.getConflictFlights().length() && showThis.getConflictFlights().length() < 60){
							//create a substring of the 
							label2e.setText("conflicts: " + showThis.getConflictFlights().substring(0, 30));
							label2f.setText("bleh" + showThis.getConflictFlights().substring(30));
							label2g.setText("");
							label2h.setText("");
							label2i.setText("");
						}
						else if(60 < showThis.getConflictFlights().length() && showThis.getConflictFlights().length() < 90){
							label2e.setText("conflicts: " +  showThis.getConflictFlights().substring(0, 30));
							label2f.setText(showThis.getConflictFlights().substring(30, 60));
							label2g.setText(showThis.getConflictFlights().substring(60));
							label2h.setText("");
							label2i.setText("");
						}
						else if(90 < showThis.getConflictFlights().length() && showThis.getConflictFlights().length() < 120){
							label2e.setText("conflicts: " +  showThis.getConflictFlights().substring(0, 30));
							label2f.setText(showThis.getConflictFlights().substring(30, 60));
							label2g.setText(showThis.getConflictFlights().substring(60, 90));
							label2h.setText(showThis.getConflictFlights().substring(90));
							label2i.setText("");
						}
						else if(120 < showThis.getConflictFlights().length() && showThis.getConflictFlights().length() < 150){
							label2e.setText("conflicts: " +  showThis.getConflictFlights().substring(0, 30));
							label2f.setText(showThis.getConflictFlights().substring(30, 60));
							label2g.setText(showThis.getConflictFlights().substring(60, 90));
							label2h.setText(showThis.getConflictFlights().substring(90, 120));
							label2i.setText(showThis.getConflictFlights().substring(120));
						}
						else{
							label2e.setText("conflicts: " +showThis.getConflictFlights());
							label2f.setText("");
							label2g.setText("");
							label2h.setText("");
							label2i.setText("");
						}
					}
					else{
						label2e.setText("");
						label2f.setText("");
						label2g.setText("");
						label2h.setText("");
						label2i.setText("");
					}
					
				}
			}

			public void actionPerformed1(ActionEvent e) {
				
				
			} 
		} );
		
		
		
		JButton add = new JButton("Add Flight");
		add.setBounds(65, 370, 120, 30);
		
		JLabel airline = new JLabel("Airline");
		airline.setBounds(20, 320, 50, 20);
		
		JTextField airlineText = new JTextField();
		airlineText.setBounds(15, 340, 70, 30);
		
		JLabel time = new JLabel("Time");
		time.setBounds(100, 320, 50, 20);
		
		JTextField timeText = new JTextField();
		timeText.setBounds(95, 340, 70, 30);
		
		JLabel notification = new JLabel("");
		notification.setBounds(20, 400, 250, 30);
		JLabel notification2 = new JLabel("");
		notification2.setBounds(20, 420, 250, 30);
		JLabel notification3 = new JLabel("");
		notification3.setBounds(20, 440, 250, 30);
		
		add.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    selectionButtonPressed();
				  }

			private void selectionButtonPressed() {
				if(airlineText.getText().length() != 3){
					notification.setText("The airline must be 3 letters long.");
				}
				else{
					notification.setText("");
				}
				if(!airlineText.getText().chars().allMatch(Character::isLetter)){
					notification2.setText("The entire airline name must be letters");
				}
				else{
					notification2.setText("");
				}
				if(!testInt(timeText.getText())){
					notification3.setText("Please enter a time in numbers");
				}
				else{
					notification3.setText("");
				}
				if(airlineText.getText().length() == 3 && airlineText.getText().chars().allMatch(Character::isLetter)
						&& testInt(timeText.getText())){
					addNewFlight(new FlightInfoNode(airlineText.getText().toUpperCase(), Integer.parseInt(timeText.getText())));
					notification.setText("Your flight has been added.");
					notification2.setText(airlineText.getText().toUpperCase() + " thanks you for flying with us.");
	
				}
			}

			public void actionPerformed1(ActionEvent e) {
				
				
			} 
				} );
		
		
		JButton reset = new JButton("Reset Schedule");
		reset.setBounds(300, 450, 155, 30);
		
		
		reset.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    selectionButtonPressed();
				  }

			private void selectionButtonPressed() {
				try {
					start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				notification.setText("The schedule has been reset");
			}

			public void actionPerformed1(ActionEvent e) {
				
				
			} 
		} );
		
		
		JLabel welcome = new JLabel("Flight Scheduler");
		//cool font, man
		Font z = new Font("Curlz MT", Font.PLAIN, 38);       
		welcome.setFont(z);
		welcome.setBounds(145, 20, 250, 75);
		

		panel.setBackground(Color.MAGENTA);
		
		
		panel.add(uploadFromFile); panel.add(next); 
		panel.add(label1); panel.add(label2a); panel.add(label2b);
		panel.add(label2c); panel.add(label2d); panel.add(fileTitle);
		panel.add(airline); panel.add(add); panel.add(airlineText);
		panel.add(timeText); panel.add(time); panel.add(welcome);
		panel.add(notification); panel.add(notification2); panel.add(notification3);
		panel.add(reset); panel.add(label2e); panel.add(uploadFileText);
		panel.add(label2f); panel.add(label2g); panel.add(label2h);
		panel.add(label2i);
		
		frame.add(panel);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	
	}
	
	public static boolean addFromFile(String givenFileName){

		//read all of the nodes from the given file
		ArrayList <FlightInfoNode> allNodes = fileProcesser.read(givenFileName);
		if(allNodes == null){
			return false;
		}
		
		//takes all of the nodes read in from the file
		//and assigns them to the runways
		int q = 0;
		while(q < allNodes.size()){
			addNewFlight(allNodes.get(q));
			q+=1;
		}
		return true;
		
	}
	
	public static void addNewFlight(FlightInfoNode addThis){
		int runwayNumber = 0; 
		FlightInfoNode p = runways.get(runwayNumber).insert(addThis);
		while (p==null){
			runways.add(new BinaryTree());
			runwayNumber += 1; 
			p = runways.get(runwayNumber).insert(addThis);
		}
		addThis.setRunway(runwayNumber);
		
		
		MasterHeap.insert(addThis);
	}
	
	public static boolean testInt(String text) {
	      try {
	          Integer.parseInt(text);
	          return true;
	       } catch (NumberFormatException e) {
	          return false;
	       }
	    }

	public static void start() throws IOException{
		//new instance of the array list of binary trees
				runways = new ArrayList <BinaryTree>();
				//add the first binarytree
				runways.add(new BinaryTree());
				
				//new instance of the method that can read/write to files
				fileProcesser = new FileIO();
				
				//creats a new but empty heap
				MasterHeap = new Heaps();
				
				fileNames = new ArrayList <String>();
				
				createUserInterface();
	
	}
	
	//sees whether or not a file can be read
	public static boolean searchForFile(String givenFile){
		for(int i = 0; i< fileNames.size(); i++){
			if(fileNames.get(i).equals(givenFile)){
				return true;
			}
		}
		return false;
	}
	
	

}
