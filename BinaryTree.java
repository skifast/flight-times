

public class BinaryTree{
	
	FlightInfoNode root;
	FlightInfoNode OGroot;
	String name;
	
	public BinaryTree(){
		OGroot = null;
		//root is the current head of the subtree
		root = null;
	}
	
	public FlightInfoNode insert(FlightInfoNode insertThis){
		//the node is in two different places
		FlightInfoNode current = root;
		OGroot = root;
	
		//insert will return the null if it is within four of
		//seconds of an existing flight in the tree 
		root =  insert(insertThis, current);;
		
		//this tells the function calling this function that
		//it cannot be added to the current tree
		if(OGroot == null){
			return null;
		}
		//otherwise it tells the function that it can be added
		else{
			return insertThis;
		}
		

	}
	
	
	public FlightInfoNode insert(FlightInfoNode insertThis, FlightInfoNode root){
		//this condition adds the node to the tree
		if(root == null){
			root = insertThis; 
			OGroot = root;
		} 
		else if(insertThis.getTime() > root.getTime()){
			//if it is not within four seconds
			if((insertThis.getTime() - root.getTime() > 4)){
				//recursive call. if the right child is null, then the node
				//will be added there. 
				root.setRightChild(insert(insertThis, root.rightChild));
			}
			else{
				OGroot = null;
			}
			
		}
		//the equals could go either to the right or to the left
		//it doesn't make it any more organized based on runway.
		//a future exention would be to sort it based off of runway
		else if(insertThis.getTime() <= root.getTime()){
			if(root.getTime() - insertThis.getTime() > 4){
				root.setLeftChild(insert(insertThis, root.leftChild));
			}
			else{
				OGroot =  null;
			}
			
		}
		return root;
	}
	
	
	//the following methods are the different printing orders.
	//for this project, only inorder was used as a testing method.
	//however i left them all in the file because i feel like they're
	//friends and they belong together
	
	/*Note: had to look inOrder up online*/
	public void inOrder(){
		FlightInfoNode current = root;
		inOrder(current);
	}
	
	public void inOrder(FlightInfoNode groot){
		if(groot == null){
			return;
		}
		inOrder(groot.getLeftChild());
		System.out.println(groot.getTime());
		inOrder(groot.getRightChild());
	}
	
	public void preOrder(FlightInfoNode groot){
		if(groot == null){
			return;
		}
		System.out.println(groot.getTime());
		preOrder(groot.getLeftChild());
		preOrder(groot.getRightChild());
		
	}
	
	public void postOrder(FlightInfoNode groot){
		if(groot == null){
			return;
		}
		postOrder(groot.getLeftChild());
		postOrder(groot.getRightChild());
		System.out.println(groot.getTime());
		
	}
	
	

}
